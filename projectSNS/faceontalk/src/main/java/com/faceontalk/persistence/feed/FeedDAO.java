package com.faceontalk.persistence.feed;

import java.util.List;
import java.util.Map;

import com.faceontalk.domain.Criteria;
import com.faceontalk.domain.feed.FeedVO;
import com.faceontalk.domain.feed.HashTagVO;

public interface FeedDAO {
	//feed
	public List<FeedVO> listFollowersFeeds(Criteria cri,Integer user_no) throws Exception;
	public int listFollowersFeedCount(Integer user_no) throws Exception;
	public List<FeedVO> listFeedsByTag(Criteria cri,Integer tag_id) throws Exception;
	public int listCountsByTagCount(Integer tag_id) throws Exception;
	public List<FeedVO> listUsersFeedPics(Integer user_no) throws Exception;
	public List<FeedVO> listAllFeeds(Criteria cri) throws Exception;
	public int listAllFeedCount() throws Exception;
		
	public void register(FeedVO vo) throws Exception;	
	public FeedVO selectByFeedNo(Integer feed_no) throws Exception;
	public void remove(Integer feed_no) throws Exception;
	public void update(FeedVO vo) throws Exception;
	public FeedVO getLastInsertedFeed() throws Exception;
	public int getLastInsertedFeedNum() throws Exception;
	public void modifyReplyCount(Integer feed_no,boolean isIncrease) throws Exception;
	public void updateReplyCount(Integer feed_no,Integer delta) throws Exception;
	
	//tag
	public void registerTag(String tag_name) throws Exception;	
	public HashTagVO selectTagByName(String tag_name) throws Exception;
	public HashTagVO getLastInsertedTag() throws Exception;
	public Map<String,HashTagVO> selectTagsByFeedNum(Integer feed_no) throws Exception;
	
	//relation
	public void registerRelation(Integer feed_no,Integer tag_id) throws Exception;
	public void removeRelation(Integer feed_no,Integer tag_id) throws Exception;
	
}
