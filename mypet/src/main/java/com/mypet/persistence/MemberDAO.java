package com.mypet.persistence;

import java.util.Date;
import java.util.List;

import com.mypet.domain.MemberVO;
import com.mypet.domain.SearchCriteria;
import com.mypet.dto.EmailAuthDTO;

public interface MemberDAO {
	//id로 검색
	public MemberVO selectById(String user_id) throws Exception;
	//sequence 로 검색
	public MemberVO selectByNum(Integer user_no) throws Exception;
	//수정
	public void modify(MemberVO vo) throws Exception;
	//삽입된멤버가져오기
	public MemberVO getLastInsertedMember() throws Exception;
		
	//가입
	public void registerMember(MemberVO vo) throws Exception;
	//멤버 리스트
	public List<MemberVO> listSearchCriteria(SearchCriteria cri) throws Exception;
	//멤버 카운트
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	//이메일 인증 등록
	public void registerAuthToken(String user_id,String auth_token,Date auth_limit) throws Exception;
	public EmailAuthDTO getEmailAuth(EmailAuthDTO dto) throws Exception;
	public void registerMemberAuth(String user_id) throws Exception;
	
	
}
