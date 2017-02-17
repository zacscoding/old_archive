package com.faceontalk.feed.persistence;

import com.faceontalk.feed.domain.ReplyVO;

public interface ReplyDAO {
	
	//register
	public void register(ReplyVO vo) throws Exception;
	
	//modify
	public void modify(ReplyVO vo) throws Exception;
	
	//remove
	public void remove(Integer rno) throws Exception;
	
}
