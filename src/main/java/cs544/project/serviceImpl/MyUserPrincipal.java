package cs544.project.serviceImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cs544.project.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class MyUserPrincipal extends User implements UserDetails {


	public MyUserPrincipal(final User user) {
		super(user);
	}


	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		String role = getRole();
		List<String> roles = new ArrayList<>();
		roles.add(role);
//		SimpleGrantedAuthority k = new SimpleGrantedAuthority("Role_"+role);
		
		
		return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_"+r)).collect(Collectors.toList());

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPhone();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return super.getName();
	}


	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String getRole() {
		return super.getRole();
	}


	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
