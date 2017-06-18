package org.board.persistence;

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
		String searchType = dto.getSearchType();
		
		if( searchType != null) {			
			if(searchType.equals("id")) {
				searchType = "user_id";
			} 		
			
			sb.append("and ")
			.append(searchType)
			.append("=#{keyword}");
		}		
		
		logger.info( sb.toString() );
		
		return sb.toString();		
	}
	
	public static String addAttach(String[] files) {
		StringBuilder sb = new StringBuilder(
				"insert into tbl_board_attach (board_no,full_name) values(last_insert_id(),"+ files[0] + ")");
		
		for(int i=1;i<files.length;i++ ) {
			sb.append(",(last_insert_id(),\'")
			.append(files[i])
			.append("\')");
		}
		
		return sb.toString();
	}
	
	

}
