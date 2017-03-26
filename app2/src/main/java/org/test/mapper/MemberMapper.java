package org.test.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.test.domain.MemberVO;

public interface MemberMapper {
	/**
	 * 1)애노테이션 기반
	 */
	/*		멤버 등록		*/
	@Insert("insert into tbl_member (userid,userpw,username,email)"
			+"values (#{userid}, #{userpw} , #{username} , #{email}) ")
	public void create(MemberVO vo) throws Exception;
	
	
	/*		멤버 검색		*/
	@Select("select * from tbl_member where user_id = #{user_id} ")
	public MemberVO read(String userid)throws Exception;
	
	/*		멤버 수정		*/
	@Update("update tbl_member set "
			+"userpw = #{userpw}, username=#{username}, email=#{email}"
			+"where userid = #{userid} ")
	public void update(MemberVO vo) throws Exception;
	
	/*		멤버 삭제		*/
	@Delete("delete from tbl_memkber where userid = #{user_id}")
	public void delete(String userid)throws Exception;

	
	/**
	 * 2)XML 매퍼
	 */
	public MemberVO login(@Param("userid") String userid,
			@Param("userpw") String userpw) throws Exception;
	
	
	
	

}
