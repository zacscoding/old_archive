package com.faceontalk.feed.persistence;

import java.util.Map;

import com.faceontalk.feed.domain.FeedVO;
import com.faceontalk.feed.domain.HashTagVO;

public interface FeedDAO {
	//feed
//	public List<FeedVO> listFollower(Criteria cri) throws Exception;
//	public List<FeedVO> listSearch(SearchCriteria cri) throws Exception;
	public void register(FeedVO vo) throws Exception;	
	public FeedVO selectByFeedNo(Integer feed_no) throws Exception;
	public void remove(Integer feed_no) throws Exception;
	public void update(FeedVO vo) throws Exception;
	public FeedVO getLastInsertedFeed() throws Exception;
	public int getLastInsertedFeedNum() throws Exception;
	
	//tag
	public void registerTag(String tag_name) throws Exception;	
	public HashTagVO selectTagByName(String tag_name) throws Exception;
	public HashTagVO getLastInsertedTag() throws Exception;
	public Map<String,HashTagVO> selectTagsByFeedNum(Integer feed_no) throws Exception;
	
	//relation
	public void registerRelation(Integer feed_no,Integer tag_id) throws Exception;
	public void removeRelation(Integer feed_no,Integer tag_id) throws Exception;
	
}
