package com.faceontalk.feed.service;

import java.util.List;
import java.util.Map;

import com.faceontalk.domain.SearchCriteria;
import com.faceontalk.feed.domain.FeedVO;
import com.faceontalk.feed.domain.HashTagVO;

public interface FeedService {	
	//feed
	public void register(FeedVO vo) throws Exception;
	public void modify(FeedVO vo) throws Exception;
	public List<FeedVO> listSearchCriteria(SearchCriteria cri) throws Exception;
	public FeedVO getLastInsertedFeed() throws Exception;
	public int getLastInsertedFeedNum() throws Exception;
	public FeedVO selectFeedByNum(Integer feed_no) throws Exception;
	
	
	//tag	
	public void registerTags(String tag) throws Exception;
	public HashTagVO selectTagByName(String tag_name) throws Exception;
	public HashTagVO getLastInsertedTag() throws Exception;
	public Map<String,HashTagVO> getTagsMap(Integer feed_no) throws Exception;
	
	//relation
	public void registerRelation(Integer feed_no,Integer tag_id) throws Exception;
	public void removeRelation(Integer feed_no,Integer tag_id) throws Exception;

}
