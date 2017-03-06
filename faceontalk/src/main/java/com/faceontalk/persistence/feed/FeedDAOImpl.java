package com.faceontalk.persistence.feed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.faceontalk.domain.Criteria;
import com.faceontalk.domain.feed.FeedVO;
import com.faceontalk.domain.feed.HashTagVO;

@Repository
public class FeedDAOImpl implements FeedDAO {
	@Inject
	private SqlSession session;
	private static String namespace = "com.faceontalk.mapper.FeedMapper";	
	
	/////////////////////
	//feed	
	
	/*		regist feed		*/
	@Override
	public void register(FeedVO vo) throws Exception {
		session.insert(namespace+".register",vo);
	}
	
	/*		select one by feed_no		*/
	@Override
	public FeedVO selectByFeedNo(Integer feed_no) throws Exception {
		return session.selectOne(namespace+".selectByFeedNum", feed_no);		
	}
	
	/*		select one by last inserted		*/
	@Override
	public FeedVO getLastInsertedFeed() throws Exception {		
		return session.selectOne(namespace+".getLastInsertedFeed");
	}
	
	/*		update feed		*/
	@Override
	public void update(FeedVO vo) throws Exception {
		session.update(namespace+".update", vo);
	}

	/*		remove feed		*/
	@Override
	public void remove(Integer feed_no) throws Exception {
		session.delete(namespace+".remove",feed_no);
	}
	
	/*		select feed_no with last inserted		*/
	@Override
	public int getLastInsertedFeedNum() throws Exception {
		return session.selectOne(namespace+".getLastInsertedFeedNum");
	}
	

	/*		modify reply count		*/
	@Override
	public void modifyReplyCount(Integer feed_no, boolean isIncrease) throws Exception {
		
		Integer delta = (isIncrease) ? 1 : -1;
		
		Map<String,Object> paramMap = new HashMap<>();
		
		paramMap.put("feed_no",feed_no);
		paramMap.put("delta",delta);
		
		session.update(namespace+".modifyReplyCount", paramMap);		
	}

	
	
	////////////////////
	// get lists
	////////////////////
	//followers
	@Override
	public List<FeedVO> listFollowersFeeds(Criteria cri, Integer user_no) throws Exception {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("cri",cri);
		paramMap.put("user_no",user_no);
		return session.selectList(namespace+".listFollowersFeeds",paramMap);				
	}
	//followers feed count
	@Override
	public int listFollowersFeedCount(Integer user_no) throws Exception {		
		return session.selectOne(namespace+".listFollowersFeedCount",user_no);
	}

	
	
	
	
	
//	//search
//	@Override
//	public List<FeedVO> listSearch(SearchCriteria cri) throws Exception {
//		return session.selectList(namespace+".listSearch",cri);
//	}
//	



	/////////////////////
	//tag
	@Override
	public void registerTag(String tag_name) throws Exception {
		session.insert(namespace+".registerTag", tag_name);		
	}	
	@Override
	public HashTagVO selectTagByName(String tag_name) throws Exception {
		return session.selectOne(namespace+".selectTagByName",tag_name);
	}	
	@Override
	public HashTagVO getLastInsertedTag() throws Exception {
		return session.selectOne(namespace+".getLastInsertedTag");
	}
	
	@Override
	public Map<String, HashTagVO> selectTagsByFeedNum(Integer feed_no) throws Exception {
		return session.selectMap(namespace+".selectTagsByFeedNum",feed_no,"tag_name");
	}

		
	/////////////////////
	//relation with feed and tag
	@Override
	public void registerRelation(Integer feed_no, Integer tag_id) throws Exception {
		Map<String,Integer> paramMap = new HashMap<>();
		paramMap.put("feed_no",feed_no);
		paramMap.put("tag_id",tag_id);
		session.insert(namespace+".registerRelation", paramMap);
	}

	@Override
	public void removeRelation(Integer feed_no, Integer tag_id) throws Exception {
//		if(tag_id == null) {
//			session.delete(namespace+".removeRelation",feed_no);
//		} else {
			Map<String,Integer> paramMap = new HashMap<>();
			paramMap.put("feed_no",feed_no);
			paramMap.put("tag_id",tag_id);
			session.delete(namespace+".removeRelation",paramMap);			
//		}
	}
	
	
}













