package com.faceontalk.feed.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.faceontalk.feed.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject
	private SqlSession session;
	private static String namespace = "com.faceontalk.mapper.ReplyMapper";
	
	@Override
	public void register(ReplyVO vo) throws Exception {
		session.insert(namespace+".register",vo);
	}

	@Override
	public void modify(ReplyVO vo) throws Exception {
		session.update(namespace+".modify", vo);
	}

	@Override
	public void remove(Integer rno) throws Exception {
		session.delete(namespace+".remove", rno);
	}

}
