package com.study.spring.service;

import com.study.spring.domain.Membership;
import com.study.spring.domain.MembershipType;
import com.study.spring.dto.res.MembershipResponse;
import com.study.spring.global.Exception.MembershipErrorResult;
import com.study.spring.global.Exception.MembershipException;
import com.study.spring.repository.MembershipRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @ExtendWith(MockitoExtension.class) : Junit5와 Mockito 연동 시 필요 
 */
@ExtendWith(MockitoExtension.class)
public class MembershipServiceTest {

    /**
     * 테스트 대상인 MembershipService에는 가짜 객체 주입을 위한 
     * @InjectMocks 사용
     */
    @InjectMocks
    private MembershipService target;

    /**
     * 가짜 객체 생성을 위한 @Mock 사용
     */
    @Mock
    private MembershipRepository membershipRepository;

    private final String userId = "userId";
    private final MembershipType membershipType = MembershipType.NAVER;
    private final Integer point = 10000;

    @Test
    void 멤버십등록실퍠_이미존재함() {
        // given
        // 멤버십등록 (객체 내부 값은 이 테스트에 중요하지 않아 빈 객체를 반환)
        // 이미 멤버십이 존재한다고 반환한 객체임
        // 이 시나리오에서는 중복된 멤버십 등록 요청을 차단하는게 목적이므로, 실제 저장 로직은 실행되지 않음
        doReturn(Membership.builder().build()).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);
        //when(membershipRepository.findByUserIdAndMembershipType(userId, membershipType)).thenReturn(Membership.builder().build());

        // when
        // target.addMembership 호출 시 MembershipException 발생 확인
        final MembershipException result = assertThrows(MembershipException.class, () -> target.addMembership(userId, membershipType, point));

        // then
        assertThat(result.getErrorResult()).isEqualTo(MembershipErrorResult.DUPLICATED_MEMBERSHIP_REGISTER);

        // save 로직 실행 확인
        verify(membershipRepository, never()).save(any(Membership.class));
    }

    @Test
    void 멤버십등록성공() {
        // given
        doReturn(null).when(membershipRepository).findByUserIdAndMembershipType(userId, membershipType);
        doReturn(membership()).when(membershipRepository).save(any(Membership.class));

        // when
        // 이떄 반환되는 값은 save 메서드의 실제 동작 결과가 아니라, Mockito로 설정된 Mock 반환값
        final MembershipResponse result = target.addMembership(userId, membershipType, point);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getMembershipType()).isEqualTo(MembershipType.LINE);

        // verity
        verify(membershipRepository, times(1)).findByUserIdAndMembershipType(userId, membershipType);
        verify(membershipRepository, times(1)).save(any(Membership.class));
    }

    private Membership membership() {
        return Membership.builder()
                .id(-1L)
                .userId(userId)
                .point(point)
                .membershipType(MembershipType.LINE)
                .build();
    }
}
