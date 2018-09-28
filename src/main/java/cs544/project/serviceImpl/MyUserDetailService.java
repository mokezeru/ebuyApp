package cs544.project.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cs544.project.model.User;
import cs544.project.service.IUserService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyUserDetailService implements UserDetailsService {
	
	@Autowired
	IUserService userService;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(username);
		User user = userService.findByUsername(username);
		return new MyUserPrincipal(user);
	}

}
