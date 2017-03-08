package com.mypet.service;

import java.util.List;

import com.mypet.domain.Criteria;
import com.mypet.domain.ReviewVO;

public interface ReviewService {

	//입력
	public void registerReview(ReviewVO vo) throws Exception;
	
	//클릭시
	public ReviewVO readReview(Integer review_no) throws Exception;
	
	//수정
	public void modifyReview(ReviewVO vo) throws Exception;
	
	//삭제
	public void deleteReview(Integer review_no) throws Exception;
	
	//리스트
	public List<ReviewVO> listAllReview() throws Exception;
	
	//페이징처리
	public List<ReviewVO> listCriteriaReview(Criteria cri) throws Exception;
	public int listCountCriteriaReview(Criteria cri) throws Exception;
	
	public List<ReviewVO> listReviewPage(Integer product_no_fk,Criteria cri) throws Exception;
	public int count(Integer product_no_fk) throws Exception;
	
	
}
