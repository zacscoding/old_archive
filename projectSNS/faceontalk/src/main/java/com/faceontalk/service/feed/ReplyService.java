package com.faceontalk.service.feed;

import java.util.List;

import com.faceontalk.domain.feed.ReplyVO;

public interface ReplyService {
	
	//list
	public List<ReplyVO> listAll(Integer feed_no_fk) throws Exception;
	public List<ReplyVO> listLimit(Integer feed_no_fk,Integer pageStart,Integer perPageNum) throws Exception;
	//regist
	public void register(ReplyVO vo) throws Exception;
	//modify
	public void modify(ReplyVO vo) throws Exception;
	//remove
	public void remove(Integer rno) throws Exception;
}
