package com.faceontalk.feed.service;

import java.util.List;

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
	
	@Override	
	public List<FeedVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		 return null;
	}

	@Override
	public void register(FeedVO vo) throws Exception {
		feedDAO.register(vo);
	}
	
	@Override
	public FeedVO getLastInserted() throws Exception {		
		return feedDAO.getLastInserted();
	}

	
	
	@Override
	public void registerTags(String tag_name) throws Exception {
		feedDAO.registerTag(tag_name);		
	}

	@Override
	public HashTagVO selectTagByName(String tag_name) throws Exception {
		return feedDAO.selectTagByName(tag_name);
	}

	@Override
	public void registerRelation(Integer feed_no, Integer tag_id) throws Exception {
		feedDAO.registerRelation(feed_no, tag_id);
	}	
	
}
