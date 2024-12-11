package com.study.spring.TDD.chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = 1;

        if (payData.getFirstBillingDate() != null) {
            // 후보 만료일 구하기
            LocalDate candidateExp = payData.getBillingDate().plusMonths(1);

            // 첫 납부일의 일자와 후보 만료일의 일자가 다르면
            if (payData.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()) {
                // 첫 납부일의 일자를 후보 만료일의 일자로 사용
                return candidateExp.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
            }
        }

        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
