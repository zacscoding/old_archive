package org.board.persistence;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.board.domain.BoardVO;

/**
 * 게시글 관련 Mapper 인터페이스
 * 
 * @author zaccoding
 * @date 2017. 5. 28.
 */
public interface BoardMapper {
	
	@Insert("insert into tbl_board (title,user_no,cate_no) values(#{title},#{userNo},#{cateNo})")
	public void regist(BoardVO vo) throws Exception;
	
	@Insert("insert into tbl_board_detail values(last_insert_id(),#{content})")
	public void registDetails(BoardVO vo) throws Exception;
	
	@InsertProvider(type=DynamicQueryProvider.class, method="addAttach")
	public void registAttach(@Param("files") String[] files);
	

}
