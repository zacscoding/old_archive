package com.mypet.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mypet.domain.MemberVO;
import com.mypet.persistence.MemberDAO;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Inject
	private MemberDAO dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO vo = null;		
		try {
			vo = dao.selectById(username);
//			if(vo == null)
//				throw new UsernameNotFoundException(username);			
//			List<UserRole> perms = dao.selectPermissionById(vo.getUser_no());
//			List<GrantedAuthority> authorities = new ArrayList<>(perms.size());
//			
//			for(UserRole perm : perms) {
//				authorities.add(new SimpleGrantedAuthority(perm.getName()));				
//			}			
//			return new User(username,vo.getPassword(),authorities);
			if(vo == null)
				return null;			
			return new SecurityUserVO(vo);
		}catch(Exception e) {
			e.printStackTrace();			
		}		
		return null;
	}
}
