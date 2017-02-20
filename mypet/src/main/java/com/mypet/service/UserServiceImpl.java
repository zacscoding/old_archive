package com.mypet.service;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mypet.domain.MemberVO;
import com.mypet.persistence.MemberDAO;
import com.mypet.security.SecurityUserVO;

public class UserServiceImpl implements UserService {
	@Inject
	private MemberDAO dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO vo = null;		
		try {
			vo = dao.selectById(username);
			if(vo == null)
				throw new UsernameNotFoundException(username);
			
//			List<UserRole> perms = dao.selectPermissionById(vo.getUser_no());
//			List<GrantedAuthority> authorities = new ArrayList<>(perms.size());
//			
//			for(UserRole perm : perms) {
//				authorities.add(new SimpleGrantedAuthority(perm.getName()));				
//			}			
//			return new User(username,vo.getPassword(),authorities);
			return new SecurityUserVO(vo);
		}catch(Exception e) {
			e.printStackTrace();			
		}		
		return null;
	}
}
