package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ModifyArticleService {
	private ArticleDao articleDao=new ArticleDao();
	private ArticleContentDao contentDao=new ArticleContentDao();
	
	public void modify(ModifyRequest modReq){
		Connection conn=null;
		try{
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article=articleDao.selectById(conn, modReq.getArticleNumber()); //게시글 번호에 해당하는 Article 인스턴스 구한다.
			if(article==null) //해당 번호를 가진 게시글이 존재하지않으면 예외 발생
				throw new ArticleNotFoundException();
			
			if(!canModify(modReq.getUserId(),article)) //수정하려는 사용자가 수정할 수 있는 지 확인 & 없으면 예외 발생
				throw new PermissionDeniedException();
			
			articleDao.update(conn, modReq.getArticleNumber(), modReq.getTitle()); //제목 수정
			contentDao.update(conn, modReq.getArticleNumber(), modReq.getContent()); //내용 수정
			
			conn.commit();			
		}catch(SQLException e){
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(PermissionDeniedException e){
			JdbcUtil.rollback(conn);
			throw e;
		}finally{
			JdbcUtil.close(conn);
		}		
	}
	
	//게시글 수정할 수 있는지 검사하는 기능
	private boolean canModify(String modfyingUserId, Article article){
		return article.getWriter().getId().equals(modfyingUserId);
	}
}
