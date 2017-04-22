package org.board.persistence;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.board.domain.MemberVO;
import org.board.dto.SearchPairDTO;

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
	@ResultMap("resultMap.memberResult")
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
	@ResultMap("resultMap.memberResult")
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
	
	@Update("alter table tbl_member auto_increment=1")
	public void initAutoInc() throws Exception;
	
	
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
