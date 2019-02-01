package com.faceontalk.persistence.feed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.faceontalk.domain.feed.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject
	private SqlSession session;
	private static String namespace = "com.faceontalk.mapper.ReplyMapper";
	
	
	@Override
	public List<ReplyVO> list(Integer feed_no_fk,Integer pageStart,Integer perPageNum) throws Exception {
		Map<String,Integer> paramMap = new HashMap<>();
		paramMap.put("feed_no_fk",feed_no_fk);
		paramMap.put("pageStart",pageStart);
		paramMap.put("perPageNum",perPageNum);
		return session.selectList(namespace+".list",paramMap);		
	}
	
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

	@Override
	public void removeAll(Integer feed_no) throws Exception {
		session.delete(namespace+".removeAll", feed_no);
	}

	@Override
	public int getFeedNumber(Integer rno) throws Exception {
		return session.selectOne(namespace+".getFeedNumber",rno);
	}	

}
