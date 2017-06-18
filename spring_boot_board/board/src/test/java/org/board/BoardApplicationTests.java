package org.board;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardApplicationTests {
	
	@Autowired
	private DataSource ds;
	
	@Autowired
	private SqlSessionFactory sqlSession;
	
	@Test
	public void contextLoads() {
	}
	
	/*	DataSource 빈설정 , 컨넥션 테스트		*/
	@Test
	public void testConnection() {
		System.out.println(ds);
		try(Connection conn = ds.getConnection()) {
			System.out.println(conn);			
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	/*	SqlSessionFactory 테스트	*/
	//스프링 부트는 실행 시 DataSource 객체를 메소드의 실행 시 주빙해서 결과를 만들고
	// 그 결과를 스프링 내 빈으로 사용
	@Test
	public void testSqlSession() throws Exception {
		System.out.println(sqlSession);
	}
	

}
