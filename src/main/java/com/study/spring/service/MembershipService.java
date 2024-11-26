package com.study.spring.service;

import com.study.spring.domain.Membership;
import com.study.spring.domain.MembershipType;
import com.study.spring.dto.res.MembershipResponse;
import com.study.spring.global.Exception.CustomException;
import com.study.spring.global.Exception.ErrorCode;
import com.study.spring.global.Exception.MembershipErrorResult;
import com.study.spring.global.Exception.MembershipException;
import com.study.spring.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public MembershipResponse addMembership(final String userId, final MembershipType membershipType, final Integer point) {
        final Membership result = membershipRepository.findByUserIdAndMembershipType(userId, membershipType);

        if (result != null) {
            throw new CustomException(ErrorCode.DUPLICATED_MEMBERSHIP_REGISTER);
        }

        final Membership membership = Membership.builder()
                    .userId(userId)
                    .point(point)
                    .membershipType(MembershipType.NAVER)
                    .build();

        final Membership savedMembership = membershipRepository.save(membership);

        return MembershipResponse.builder()
                .id(savedMembership.getId())
                .membershipType(savedMembership.getMembershipType())
                .build();
    }
}
