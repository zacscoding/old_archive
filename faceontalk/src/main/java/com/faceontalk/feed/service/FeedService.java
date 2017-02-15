package com.faceontalk.feed.service;

import java.util.List;

import com.faceontalk.domain.SearchCriteria;
import com.faceontalk.feed.domain.FeedVO;
import com.faceontalk.feed.domain.HashTagVO;

public interface FeedService {	
	//feed
	public List<FeedVO> listSearchCriteria(SearchCriteria cri) throws Exception;	
	public void register(FeedVO vo) throws Exception;
	public FeedVO getLastInserted() throws Exception;
	
	
	//tag	
	public void registerTags(String tag) throws Exception;
	public HashTagVO selectTagByName(String tag_name) throws Exception;
	
	//relation
	public void registerRelation(Integer feed_no,Integer tag_id) throws Exception;

}
