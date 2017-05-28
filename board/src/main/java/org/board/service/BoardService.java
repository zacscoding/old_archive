package org.board.service;

import org.board.domain.BoardVO;
import org.board.persistence.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	
	@Transactional
	public void regist(BoardVO vo) throws Exception {
		boardMapper.regist(vo);
		boardMapper.registDetails(vo);
		if( vo.getFiles() != null ) {			
			boardMapper.registAttach( vo.getFiles() );
		}
	}
	
	
}
