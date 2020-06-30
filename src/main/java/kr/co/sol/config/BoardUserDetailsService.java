package kr.co.sol.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.sol.domain.Member3;
import kr.co.sol.domain.SecurityUser;
import kr.co.sol.persistence.MemberRepository;

@Service
public class BoardUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member3> optional = memberRepo.findById(username);
		if (!optional.isPresent()) {
			throw new UsernameNotFoundException(username + " 사용자 없음");
		} else {
			Member3 member = optional.get();
			return new SecurityUser(member);
		}
	}
}
