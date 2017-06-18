package org.board.persistence;

import java.util.Date;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.board.domain.MemberVO;
import org.board.dto.SearchPairDTO;

/**
 * 회원 멤버 관련 Mapper 인터페이스
 * 
 * @author zaccoding
 * @date 2017. 4. 16.
 */
public interface MemberMapper {
	
	/**
	 * id로 MemberVO 인스턴스  얻는 메소드
	 * 
	 * @date	: 2017. 4. 16.
	 * @param userId 
	 * @return 저장 된 MemberVO 인스턴스
	 * @throws Exception
	 */
	@Select("select * from tbl_member where user_id = #{userId}")
	public MemberVO selectById(String userId) throws Exception;
	
	/**
	 * email로 MemberVO 인스턴스 얻는 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 4. 22.
	 * @param email
	 * @return 저장 된 MemberVO 인스턴스
	 * @throws Exception
	 */
	@Select("select * from tbl_member where email = #{email}")
	public MemberVO selectByEmail(String email) throws Exception;
	
	
	/**
	 * 멤버 등록
	 * @date	: 2017. 4. 16.
	 * @param vo
	 * @throws Exception
	 */
	@Insert("insert into tbl_member (user_id, password, email) values(#{userId},#{password},#{email})")
	public void regist(MemberVO vo) throws Exception;
	
	/**
	 * 회원 정보 변경
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 4. 23.
	 * @param vo
	 * @throws Exception
	 */
	@Update("update tbl_member set email = #{email} where user_no = #{userNo}")
	public void modify(MemberVO vo) throws Exception;
	
	/**
	 * 비밀 번호 변경
	 * @author 	zaccoding
	 * @date 	2017. 4. 23.
	 * @param vo
	 * @throws Exception
	 */
	@Update("update tbl_member set password = #{password} where user_no = #{userNo}")
	public void modifyPassword(MemberVO vo) throws Exception;
	
	
	/**
	 * user 시퀀스로 삭제하기
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 4. 22.
	 * @param userNo User PK
	 * @throws Exception
	 */
	@Delete("delete from tbl_member where user_no = #{userNo}")
	public void remove(Integer userNo) throws Exception;
	
	/**
	 * 저장 된 모드 멤버 삭제하기
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 4. 22.
	 * @throws Exception
	 */
	@Delete("delete from tbl_member where user_no > 0")
	public void removeAll()throws Exception;
	
	
	/**
	 * 멤버 테이블 user_no 컬럼 1로 초기화 하기
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 1.
	 * @throws Exception
	 */
	@Update("alter table tbl_member auto_increment=1")
	public void initAutoInc() throws Exception;
	
	/**
	 * 세션키로 MemberVO 인스턴스 찾는 메소드
	 *  
	 * @author 	zaccoding
	 * @date 	2017. 5. 1.
	 * @param sessionId 자동 로그인 쿠키를 사용했을 때의 세션 아이디
	 * @return 세션아이디가 존재하고, 기간이 유효하면 vo 인스턴스 , 아니면 null
	 * @throws Exception
	 */
	@Select("select * from tbl_member where session_key = #{sessionKey} and session_limit > now()")
	public MemberVO getUserBySessionKey(String sessionKey) throws Exception;
	
	/**
	 * 자동 로그인 처리 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 1.
	 * @param userNo 유저 시퀀스
	 * @param sessionKey 자동 로그인 요청 시의 세션 아이디
	 * @param expiredDate 유효 시간
	 * @throws Exception
	 */
	@Update("update tbl_member set session_key = #{sessionKey}, session_limit = #{expiredDate} where user_no = #{userNo}")
	public void keepLogin(@Param("userNo") Integer userNo, @Param("sessionKey") String sessionKey, 
						  @Param("expiredDate") Date expiredDate) throws Exception;	
	
	/**
	 * 존재 여부 체크 메소드
	 * 
	 * @param dto
	 * <ul>
	 * 	<li> searchType : email or user_id</li>
	 *  <li> keyword : 중복여부 </li>
	 * </ul>
	 * @return 검색 레코드 수
	 * @throws Exception
	 */
	//@Select("select count(user_no) from tbl_member where ${searchType} = #{keyword}")
	@SelectProvider(type=DynamicQueryProvider.class, method="checkMember")
	public int existMember(SearchPairDTO dto) throws Exception;
	

}
