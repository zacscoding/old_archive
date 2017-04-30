package org.board.service;

import org.board.domain.MemberVO;
import org.board.dto.SearchPairDTO;
import org.board.exception.DuplicateValueException;
import org.board.persistence.MemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 회원 관련 Service 클래스
 * @author 	:	Zaccoding
 * @date 	: 	2017. 4. 16.
 */
@Service
public class MemberService {
	
	private final static Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordService passwordService;
	

	/**
	 * 회원 가입 처리 메소드
	 * 
	 * @date	: 2017. 4. 16.
	 * @param vo
	 * @throws Exception
	 */
	public void register(MemberVO vo) throws Exception {
		
		MemberVO anotherUser = memberMapper.selectById( vo.getUserId() );		
		if( anotherUser != null )
			throw new DuplicateValueException("Duplicate ID. Please use another id");
		
		passwordService.encryptPassword(vo);
		
		memberMapper.regist(vo);
	}
	
	
	/**
	 * 존재 유무 체크 처리 메소드
	 * 
	 * @date	: 2017. 4. 16.
	 * @param dto
	 * <ul>
	 * 	<li> searchType : email or user_id</li>
	 *  <li> keyword : 중복여부 </li>
	 * </ul>
	 * 	
	 * @return 존재 : true , 존재 하지 않음 : false
	 * @throws Exception
	 */
	public boolean existMember(SearchPairDTO dto) throws Exception {		
		logger.info( String.valueOf(memberMapper.existMember(dto)) );		
		return ( memberMapper.existMember(dto) > 0 );
	}
	
	
	
	
	
	
	

}
