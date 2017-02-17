package com.faceontalk.feed.service;

import com.faceontalk.feed.domain.ReplyVO;

public interface ReplyService {
	
	//regist
	public void register(ReplyVO vo) throws Exception;
	//modify
	public void modify(ReplyVO vo) throws Exception;
	//remove
	public void remove(Integer rno) throws Exception;
}
