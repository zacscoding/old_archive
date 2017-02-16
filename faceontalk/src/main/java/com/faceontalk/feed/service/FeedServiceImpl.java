package com.faceontalk.feed.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.faceontalk.domain.SearchCriteria;
import com.faceontalk.feed.domain.FeedVO;
import com.faceontalk.feed.domain.HashTagVO;
import com.faceontalk.feed.persistence.FeedDAO;

@Service
public class FeedServiceImpl implements FeedService {	
	@Inject
	private FeedDAO feedDAO;		
	
	/////////////////////////////
	//feed
	
	@Override	
	public List<FeedVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		//not yet implement
		 return null;
	}

	@Override
	public void register(FeedVO vo) throws Exception {
		feedDAO.register(vo);
	}
	
	@Override
	public FeedVO getLastInsertedFeed() throws Exception {		
		return feedDAO.getLastInsertedFeed();
	}
	

	@Override
	public FeedVO selectFeedByNum(Integer feed_no) throws Exception {
		return feedDAO.selectByFeedNo(feed_no);
	}
	
	@Override
	public void modify(FeedVO vo) throws Exception {
		feedDAO.update(vo);
	}
	
	/////////////////////////////
	//tag	
	@Override
	public void registerTags(String tag_name) throws Exception {
		feedDAO.registerTag(tag_name);		
	}

	@Override
	public HashTagVO selectTagByName(String tag_name) throws Exception {
		return feedDAO.selectTagByName(tag_name);
	}

	@Override
	public HashTagVO getLastInsertedTag() throws Exception {		
		return feedDAO.getLastInsertedTag();
	}
	
	/////////////////////////////
	//relation with feed and tag	
	@Override
	public void registerRelation(Integer feed_no, Integer tag_id) throws Exception {
		feedDAO.registerRelation(feed_no, tag_id);
	}

	@Override
	public Map<String,HashTagVO> getTagsMap(Integer feed_no) throws Exception {
		return feedDAO.selectTagsByFeedNum(feed_no);
	}

	@Override
	public void removeRelation(Integer feed_no, Integer tag_id) throws Exception {
		feedDAO.removeRelation(feed_no, tag_id);
	}


}
