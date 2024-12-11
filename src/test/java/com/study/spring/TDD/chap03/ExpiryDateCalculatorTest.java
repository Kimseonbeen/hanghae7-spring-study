package com.study.spring.TDD.chap03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
/*
        LocalDate billingDate = LocalDate.of(2019, 3, 1);
        int payAmount = 10_000;

        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);

        assertEquals(LocalDate.of(2019,4,1), expiryDate);

        // 동일 조건을 추가해보자 ! (예를 추가하면서 구현을 일반화 시키는 작업)
        LocalDate billingDate2 = LocalDate.of(2019, 5, 5);
        int payAmount2 = 10_000;

        ExpiryDateCalculator cal2 = new ExpiryDateCalculator();
        LocalDate expiryDate2 = cal2.calculateExpiryDate(billingDate2, payAmount);

        assertEquals(LocalDate.of(2019,6,5), expiryDate2);
*/

        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2019,3,1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 4, 1));

        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2019, 5, 5))
                .payAmount(10_000)
                .build(),
                LocalDate.of(2019, 6, 5));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        // LocalDate.plusMonths 메서드가 알아서 처리해줌. 굿
        assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2019,1,31))
                .payAmount(10_000)
                .build(), LocalDate.of(2019,2,28));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를떄_만원_납부() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,31))
                .billingDate(LocalDate.of(2019,2,28))
                .payAmount(10_000)
                .build();

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,30))
                .billingDate(LocalDate.of(2019,2,28))
                .payAmount(10_000)
                .build();

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,5,31))
                .billingDate(LocalDate.of(2019,6,30))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2019,3,31));
        assertExpiryDate(payData2, LocalDate.of(2019,3,30));
        assertExpiryDate(payData3, LocalDate.of(2019,7,31));

    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);

        assertEquals(expectedExpiryDate, realExpiryDate);
    }
}
