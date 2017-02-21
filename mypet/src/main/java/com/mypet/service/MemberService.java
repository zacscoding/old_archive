package com.mypet.service;

import java.util.List;

import com.mypet.domain.MemberVO;
import com.mypet.domain.SearchCriteria;

public interface MemberService {
	
	//memebr list
	public List<MemberVO> listSearchCriteria(SearchCriteria cri) throws Exception;
	//member list count
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	//select by num
	public MemberVO selectByNum(Integer user_no) throws Exception;
	//select by id
	public MemberVO selectById(String user_id) throws Exception;
	//modify
	public void modifyUser(MemberVO vo) throws Exception;
	
	
	
	
	
	
}
