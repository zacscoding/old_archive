package com.faceontalk.service.feed;

import java.util.List;
import java.util.Map;

import com.faceontalk.domain.Criteria;
import com.faceontalk.domain.SearchCriteria;
import com.faceontalk.domain.feed.FeedVO;
import com.faceontalk.domain.feed.HashTagVO;

public interface FeedService {
	
	/*		feed		*/
	//C,U,D
	public void register(FeedVO vo) throws Exception;
	public void modify(FeedVO vo) throws Exception;
	public void remove(Integer feed_no) throws Exception;
	public FeedVO getLastInsertedFeed() throws Exception;
	public int getLastInsertedFeedNum() throws Exception;
	public void modifyReplyCount(Integer feed_no,boolean isIncrease) throws Exception;
	
	//R
	public FeedVO selectFeedByNum(Integer feed_no) throws Exception;	
	public List<FeedVO> listFollowersFeeds(Criteria cri,Integer user_no) throws Exception;
	public int listFollowersFeedCount(Integer user_no) throws Exception;	
	public List<FeedVO> listFeedsByTag(Criteria cri,Integer tag_id) throws Exception;
	public int listCountsByTagCount(Integer tag_id) throws Exception;
	public List<FeedVO> listUsersFeedPics(Integer user_no) throws Exception;
	public List<FeedVO> listAllFeeds(Criteria cri) throws Exception;
	public int listAllFeedCount() throws Exception;
	
	/*		tag		*/	
	public void registerTags(String tag) throws Exception;
	public HashTagVO selectTagByName(String tag_name) throws Exception;
	public HashTagVO getLastInsertedTag() throws Exception;
	public Map<String,HashTagVO> getTagsMap(Integer feed_no) throws Exception;
	
	/*		feed and tag relation		*/
	public void registerRelation(Integer feed_no,Integer tag_id) throws Exception;
	public void removeRelation(Integer feed_no,Integer tag_id) throws Exception;

}
