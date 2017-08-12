package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class LoginService {
	private MemberDao memberDao=new MemberDao();
	
	public User login(String id,String password){
		try(Connection conn=ConnectionProvider.getConnection()){
			Member member=memberDao.selectById(conn, id);
			if(member==null || !member.matchPassword(password)) //회원 id가 존재하지 않는 경우 || 비밀번호가 틀린 경우
				throw new LoginFailException();
			return new User(member.getId(),member.getName());			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	//회원 가입을 위한 JoinService 클래스의 join()메소드는 JoinRequest 클래스를 이용
	//BUT LoginService 클래스의 login()메소드는 필요 데이터==아이디,암호 => 별도의 클래스 만들지X
}
