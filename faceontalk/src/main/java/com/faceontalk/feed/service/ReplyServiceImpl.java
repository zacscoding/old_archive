package com.faceontalk.feed.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.faceontalk.feed.domain.ReplyVO;
import com.faceontalk.feed.persistence.ReplyDAO;

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
