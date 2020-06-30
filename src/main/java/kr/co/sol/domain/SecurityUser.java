package kr.co.sol.domain;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {
	private static final long serialVersionUID = 1L;

	public SecurityUser(Member3 member) {
		super(member.getId(), member.getPassword(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}
}
