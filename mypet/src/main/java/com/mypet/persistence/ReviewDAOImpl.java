package com.mypet.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mypet.domain.Criteria;
import com.mypet.domain.ReviewVO;


@Repository
public class ReviewDAOImpl implements ReviewDAO {
	private static final String namespace = "com.mypet.mapper.ReviewMapper";
	@Inject
	SqlSession session;
	
	@Override
	public void registerReview(ReviewVO vo) throws Exception {
		session.insert(namespace+".registerReview", vo);
	}

	@Override
	public ReviewVO readReview(Integer review_no) throws Exception {
		return session.selectOne(namespace+".readReview",review_no);
	}

	@Override
	public void modifyReview(ReviewVO vo) throws Exception {
		session.update(namespace+".modifyReview",vo);
	}

	@Override
	public void deleteReview(Integer review_no) throws Exception {
		session.delete(namespace+".deleteReview",review_no);
	}

	@Override
	public List<ReviewVO> listAllReview() throws Exception {
		return session.selectList(namespace+".listAllReview");
	}
	
	//페이징처리
	@Override
	public List<ReviewVO> listPageReview(int page) throws Exception {
		if (page <= 0 ) {
			page =1;
		}
		page = (page -1)*10;
		return session.selectList(namespace+".listPageReview",page);
	}

	@Override
	public List<ReviewVO> listCriteriaReview(Criteria cri) throws Exception {
		return session.selectList(namespace+".listCriteriaReview",cri);
	}

	@Override
	public int countPagingReview(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".countPagingReview", cri);
	}
	
	
	
	/**	리뷰 페이징 처리 리스트	*/	
	@Override
	public int count(Integer product_no_fk) throws Exception {
		return session.selectOne(namespace+".count",product_no_fk);
	}
	
	@Override
	public List<ReviewVO> listReviewPage(Integer product_no_fk, Criteria cri) throws Exception {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("product_no_fk",product_no_fk);
		paramMap.put("cri",cri);
		return session.selectList(namespace+".listReview",paramMap);
	}
	

}






