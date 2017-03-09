package com.faceontalk.service.feed;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faceontalk.domain.feed.ReplyVO;
import com.faceontalk.persistence.feed.FeedDAO;
import com.faceontalk.persistence.feed.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Inject
	private ReplyDAO replyDAO;
	
	@Inject
	private FeedDAO feedDAO;

	
	@Override
	public List<ReplyVO> listAll(Integer feed_no_fk) throws Exception {		
		return replyDAO.list(feed_no_fk,null,null);
	}
	public List<ReplyVO> listLimit(Integer feed_no_fk,Integer pageStart,Integer perPageNum) throws Exception {
		return replyDAO.list(feed_no_fk, pageStart, perPageNum);
	}
	
	@Transactional
	@Override	
	public void register(ReplyVO vo) throws Exception {
		replyDAO.register(vo);
		feedDAO.updateReplyCount(vo.getFeed_no_fk(),1);
	}

	@Override
	public void modify(ReplyVO vo) throws Exception {
		replyDAO.modify(vo);
	}

	@Transactional
	@Override
	public void remove(Integer feed_no, Integer rno) throws Exception {
		replyDAO.remove(rno);
		feedDAO.updateReplyCount(feed_no,-1);
	}	
	
	public void removeAll(Integer feed_no) throws Exception {
		replyDAO.removeAll(feed_no);
	}
	
}
