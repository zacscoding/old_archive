package com.faceontalk.persistence.feed;

import java.util.List;

import com.faceontalk.domain.feed.ReplyVO;

public interface ReplyDAO {
	
	//list
	public List<ReplyVO> list(Integer feed_no_fk) throws Exception;
	
	//register
	public void register(ReplyVO vo) throws Exception;
	
	//modify
	public void modify(ReplyVO vo) throws Exception;
	
	//remove
	public void remove(Integer rno) throws Exception;
	
}
