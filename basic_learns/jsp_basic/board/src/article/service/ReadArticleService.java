package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.connection.ConnectionProvider;

public class ReadArticleService {
	private ArticleDao articleDao=new ArticleDao();
	private ArticleContentDao contentDao=new ArticleContentDao();
	
	public ArticleData getArticle(int articleNum, boolean increasedReadCount){
		try(Connection conn=ConnectionProvider.getConnection()){
			Article article=articleDao.selectById(conn, articleNum); //article 테이블에서 지정한 번호의 Article 인스턴스를 구함
			if(article==null) //게시글 데이터가 존재하지 않으면 익셉션 발생
				throw new ArticleNotFoundException();
			
			
			ArticleContent content=contentDao.selectById(conn, articleNum); //article_content 테이블에서 지정한 번호의 ArticleContent 인스턴스를 구함
			if(content==null) //게시글 내용 데이터가 존재하지 않으면 익셉션 발생
				throw new ArticleContentNotFoundException();		
			
			if(increasedReadCount) //increasedReadCount가 true면 조회수 증가(게시글 수정 기능에서도 getArticle()메소드를 사용하기 때문에)
				articleDao.increaseReadCount(conn, articleNum);
			
			return new ArticleData(article,content); //ArticleData 인스턴스 반환			
		}catch(SQLException e){			
			throw new RuntimeException(e);
		}
	}
}
