package kr.co.sol.persistence;

import org.springframework.data.repository.CrudRepository;

import kr.co.sol.domain.Member3;

public interface MemberRepository extends CrudRepository<Member3, String> {
}
