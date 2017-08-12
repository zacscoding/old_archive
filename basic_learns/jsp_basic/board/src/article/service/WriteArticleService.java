package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class WriteArticleService {
	private ArticleDao articleDao=new ArticleDao();
	private ArticleContentDao contentDao=new ArticleContentDao();
	
	//WriteRequest의 인스턴스를 이용해서 게시글을 등록하고 결과로 게시글 번호를 리턴
	public Integer write(WriteRequest req){
		Connection conn=null;
		try{
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false); //트랜잭션 시작
			
			Article article=toArticle(req); //WriteRequest 인스턴스로 Article 객체 생성
			Article savedArticle=articleDao.insert(conn, article); //ArticleDao의 insert() 실행 & 결과를 savedArticle에 저장.
																	//article테이블에 추가한 데이터의 주요 키값을 number 값으로 갖음.
			
			if(savedArticle==null){ //article 테이블에 삽입한 레코드가 없으면
				throw new RuntimeException("fail to insert article"); //익셉션 발생
			}
			
			ArticleContent content=new ArticleContent(savedArticle.getNumber(),req.getContent()); //ArticaleContent 인스턴스 생성
																								//savedArticle의 게시글 번호 이용
			ArticleContent savedContent=contentDao.insert(conn, content); //ArticleContentDao를 이용해서 insert()메소드 실행 & article_content에 데이터 삽입
			if(savedContent==null){ //ArticleDao의 insert() 실행 결과가 null이면
				throw new RuntimeException("fail to insert article_content"); //익셉션 발생				
			}
			
			conn.commit(); //트랜잭션 커밋(article,article_content 테이블에 성공적으로 데이터 삽입되면 커밋됨)
			
			return savedArticle.getNumber(); //새로 추가한 게시글 번호 리턴			
		}catch(SQLException e){ // 예외 발생하면
			JdbcUtil.rollback(conn); //롤백
			throw new RuntimeException(e);		
		}finally{
			JdbcUtil.close(conn);
		}		
	}
	
	private Article toArticle(WriteRequest req){
		Date now=new Date();
		return new Article(null,req.getWriter(),req.getTitle(),now,now,0);	//게시글을 저장해야 id를 알 수 있으므로 null을 전달	
	}
}
