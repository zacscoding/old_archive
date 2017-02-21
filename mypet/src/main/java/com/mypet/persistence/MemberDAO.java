package com.mypet.persistence;

import java.util.List;

import com.mypet.domain.MemberVO;
import com.mypet.domain.SearchCriteria;

public interface MemberDAO {
	//id로 검색
	public MemberVO selectById(String user_id) throws Exception;
	//sequence 로 검색
	public MemberVO selectByNum(Integer user_no) throws Exception;
	//수정
	public void modify(MemberVO vo) throws Exception;
	
	
	
	//권한 로그
	//public List<UserRole> selectPermissionById(Integer user_no) throws Exception;
	//가입
	public void registerMember(MemberVO vo) throws Exception;
	//멤버 리스트
	public List<MemberVO> listSearchCriteria(SearchCriteria cri) throws Exception;
	//멤버 카운트
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	
	

}
