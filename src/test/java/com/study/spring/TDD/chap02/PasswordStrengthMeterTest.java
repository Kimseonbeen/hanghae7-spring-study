package com.study.spring.TDD.chap02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordStrengthMeterTest {
    // 테스트코드도 유지보수의 대상이다
    // PasswordStrengthMeter 객체의 중복을 제거해보자.
    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    // 암호 강도 측정 기능을 실행 후, 검증 하는 코드도 중복이니 제거해보자.
    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }

    // 암호가 모든 조건을 충족하면 암호 강도는 강함이여야함
    // 가장 쉽게 통과 하는 테스트부터 실행
    @Test
    @DisplayName("암호가 모든 조건을 충족하면 암호 강도는 강함이여야함")
    void meetsAllCriteria_Then_Strong() {
        //PasswordStrengthMeter meter = new PasswordStrengthMeter();

        //PasswordStrength result = meter.meter("ab12!@AB");
        //assertEquals(PasswordStrength.STRONG, result);

        assertStrength("ab12!@AB", PasswordStrength.STRONG);

        //PasswordStrength result2 = meter.meter("abc1!Add");
        //assertEquals(PasswordStrength.STRONG, result2);

        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    // 길이만 8글자 미만이고 나머지 조건은 충족하는 경우
    @Test
    @DisplayName("길이만 8글자 미만이고 나머지 조건은 충족하는 경우")
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("숫자를 포함하지 않고 나머지 조건은 충족하는 경우")
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }
}
