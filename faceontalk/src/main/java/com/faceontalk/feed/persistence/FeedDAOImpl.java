package com.faceontalk.feed.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.faceontalk.feed.domain.FeedVO;
import com.faceontalk.feed.domain.HashTagVO;

@Repository
public class FeedDAOImpl implements FeedDAO {
	@Inject
	private SqlSession session;
	private static String namespace = "com.faceontalk.mapper.FeedMapper";
	
	@Override
	public void register(FeedVO vo) throws Exception {
		session.insert(namespace+".register",vo);		
	}

	@Override
	public FeedVO getLastInserted() throws Exception {		
		return session.selectOne(namespace+".lastInserted");
	}

	@Override
	public void remove(Integer feed_no) throws Exception {
		session.delete(namespace+".remove",feed_no);
	}

	@Override
	public void registerTag(String tag_name) throws Exception {
		session.insert(namespace+".registerTag", tag_name);
		
	}
	
	@Override
	public HashTagVO selectTagByName(String tag_name) throws Exception {
		return session.selectOne(namespace+".selectTagByName",tag_name);
	}

	@Override
	public void registerRelation(Integer feed_no, Integer tag_id) throws Exception {
		Map<String,Integer> paramMap = new HashMap<>();
		paramMap.put("feed_no",feed_no);
		paramMap.put("tag_id",tag_id);
		session.insert(namespace+".registerRelation", paramMap);
	}
	
	
//	////////////////////
//	// get lists
//	////////////////////	
//	//search
//	@Override
//	public List<FeedVO> listSearch(SearchCriteria cri) throws Exception {
//		return session.selectList(namespace+".listSearch",cri);
//	}
//	
//	//followers
//	@Override
//	public List<FeedVO> listFollower(Criteria cri) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	////////////////////
	// get lists
	////////////////////	

	
	
	
	
}













