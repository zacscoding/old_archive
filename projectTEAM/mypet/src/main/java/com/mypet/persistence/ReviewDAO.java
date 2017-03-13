package com.mypet.persistence;

import java.util.List;

import com.mypet.domain.Criteria;
import com.mypet.domain.ReviewVO;

public interface ReviewDAO {

	//리뷰 입력
	public void registerReview(ReviewVO vo) throws Exception;
	
	//읽기
	public ReviewVO readReview(Integer review_no) throws Exception;
	
	//수정
	public void modifyReview(ReviewVO vo) throws Exception;
	
	//삭제
	public void deleteReview(Integer review_no) throws Exception;
	
	//리뷰리스트
	public List<ReviewVO> listAllReview() throws Exception;
	
	//리뷰페이징처리
	public List<ReviewVO>listPageReview(int page) throws Exception;
	public List<ReviewVO>listCriteriaReview(Criteria cri) throws Exception;
	public int countPagingReview(Criteria cri) throws Exception;
	
	
	/**	리뷰 페이징 처리 리스트	*/
	public int count(Integer product_no_fk) throws Exception;
	public List<ReviewVO> listReviewPage(Integer product_no_fk,Criteria cri)throws Exception;
	
}





