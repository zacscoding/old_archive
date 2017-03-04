package com.faceontalk.service.feed;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.faceontalk.domain.feed.ReplyVO;
import com.faceontalk.persistence.feed.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Inject
	private ReplyDAO replyDAO;

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
