package com.faceontalk.feed.persistence;

import com.faceontalk.feed.domain.FeedVO;
import com.faceontalk.feed.domain.HashTagVO;

public interface FeedDAO {
	//feed
//	public List<FeedVO> listFollower(Criteria cri) throws Exception;
//	public List<FeedVO> listSearch(SearchCriteria cri) throws Exception;
	public void register(FeedVO vo) throws Exception;	
	public void remove(Integer feed_no) throws Exception;	
	public FeedVO getLastInserted() throws Exception;
	
	//tag
	public void registerTag(String tag_name) throws Exception;	
	public HashTagVO selectTagByName(String tag_name) throws Exception;
	
	//relation
	public void registerRelation(Integer feed_no,Integer tag_id) throws Exception;
	
}
