package org.board.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.board.domain.MemberVO;
import org.board.dto.SearchPairDTO;

public interface MemberMapper {
	
	/**
	 * id로 MemberVO 인스턴스 얻는 메소드
	 * 
	 * @date	: 2017. 4. 16.
	 * @param userId 
	 * @return 저장 된 VO 인스턴스
	 * @throws Exception
	 */
	@Select("select * from tbl_member where user_id = #{userId}")
	@ResultMap("resultMap.memberResult")
	public MemberVO selectById(String userId) throws Exception;
	
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
