package org.board.mapper;

import org.board.dto.SearchPairDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicQueryProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(DynamicQueryProvider.class);
	
	/**
	 * 유저 id/email 존재 여부 동적 쿼리
	 * @date	: 2017. 4. 16.
	 * @param dto
	 * @return 동적 쿼리
	 */
	public static String checkMember(SearchPairDTO dto) {
		StringBuilder sb = new StringBuilder("select count(user_no) from tbl_member where user_no > 0 ");
		String searchType = null;
		
		if(dto.getSearchType() != null) {			
			if(dto.getSearchType().equals("id")) {
				searchType = "user_id";
			} else if(dto.getSearchType().equals("email")) {
				searchType = "email";
			}			
			sb.append("and ")
			.append(searchType)
			.append("=")
			.append("#{keyword}");			
		}
		logger.info(sb.toString());
		return sb.toString();
		
	}

}
