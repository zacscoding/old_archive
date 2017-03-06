package com.faceontalk.service.feed;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

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
	public List<ReplyVO> list(Integer feed_no_fk) throws Exception {		
		return replyDAO.list(feed_no_fk);
	}
	
	@Override
	public void register(ReplyVO vo) throws Exception {
		replyDAO.register(vo);
	}

	@Override
	public void modify(ReplyVO vo) throws Exception {
		replyDAO.modify(vo);
	}

	@Override
	public void remove(Integer rno) throws Exception {
		replyDAO.remove(rno);
	}	
}
