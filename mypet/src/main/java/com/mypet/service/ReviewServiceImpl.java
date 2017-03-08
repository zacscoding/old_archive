package com.mypet.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mypet.domain.Criteria;
import com.mypet.domain.ReviewVO;
import com.mypet.persistence.ReviewDAO;


@Service
public class ReviewServiceImpl implements ReviewService {

	@Inject
	ReviewDAO dao;
	
	//입력
	@Override
	public void registerReview(ReviewVO vo) throws Exception {
		dao.registerReview(vo);

	}
	
	//클릭시 리뷰내용보기
	@Override
	public ReviewVO readReview(Integer review_no) throws Exception {
		return dao.readReview(review_no);
	}
	
	//수정
	@Override
	public void modifyReview(ReviewVO vo) throws Exception {
		dao.modifyReview(vo);

	}
	
	//삭제
	@Override
	public void deleteReview(Integer review_no) throws Exception {
		dao.deleteReview(review_no);
	}

	//리스트
	@Override
	public List<ReviewVO> listAllReview() throws Exception {
		return dao.listAllReview();
	}

	//페이징처리
	@Override
	public List<ReviewVO> listCriteriaReview(Criteria cri) throws Exception {
		return dao.listCriteriaReview(cri);
	}
	@Override
	public int listCountCriteriaReview(Criteria cri) throws Exception {
		return dao.countPagingReview(cri);
	}

	
	/**		리뷰 페이징 처리	*/
	@Override
	public List<ReviewVO> listReviewPage(Integer product_no_fk, Criteria cri) throws Exception {
		return dao.listReviewPage(product_no_fk, cri);
	}
	
	@Override
	public int count(Integer product_no_fk) throws Exception {
		return dao.count(product_no_fk);
	}
	

	
	
}












