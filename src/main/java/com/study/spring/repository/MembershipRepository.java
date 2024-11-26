package com.study.spring.repository;

import com.study.spring.domain.Membership;
import com.study.spring.domain.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    Membership findByUserIdAndMembershipType(final String userId, final MembershipType membershipType);

}
