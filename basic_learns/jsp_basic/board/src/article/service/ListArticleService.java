package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.dao.ArticleDao;
import article.model.Article;
import jdbc.connection.ConnectionProvider;

public class ListArticleService {
	private ArticleDao articleDao=new ArticleDao();
	private int size=10;
		
	public ArticlePage getArticlePage(int pageNum){
		try(Connection conn=ConnectionProvider.getConnection()){
			//전체 게시글 구하기
			int total=articleDao.selectCount(conn);
			//pageNum에 해당하는 게시글 목록을 구함.
			List<Article> content=articleDao.select(conn, (pageNum-1)*size , size); //시작행은 0번 기준으로, 3페이지 요청하면 (3-1)*10==20이 시작 행번호
			return new ArticlePage(total,pageNum,size,content);			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
