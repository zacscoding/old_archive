package com.faceontalk.persistence.feed;

import java.util.List;

import com.faceontalk.domain.feed.ReplyVO;

public interface ReplyDAO {
	
	//list
	public List<ReplyVO> list(Integer feed_no_fk,Integer pageStart,Integer perPageNum) throws Exception;
	
	//register
	public void register(ReplyVO vo) throws Exception;
	
	//modify
	public void modify(ReplyVO vo) throws Exception;
	
	//remove
	public void remove(Integer rno) throws Exception;
	
	//remove all
	public void removeAll(Integer feed_no) throws Exception;
	
	//get feed_no
	public int getFeedNumber(Integer rno)throws Exception;
	
}
