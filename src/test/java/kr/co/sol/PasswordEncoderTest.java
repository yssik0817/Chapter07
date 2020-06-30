package kr.co.sol;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import kr.co.sol.domain.Member3;
import kr.co.sol.domain.Role;
import kr.co.sol.persistence.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
public class PasswordEncoderTest {
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	public void testInsert() {
		Member3 member = new Member3();
		member.setId("manager2");
		member.setPassword(encoder.encode("manager456"));
		member.setName("매니저2");
		member.setRole(Role.ROLE_MANAGER);
		member.setEnabled('T');
		memberRepo.save(member);
	}
}
