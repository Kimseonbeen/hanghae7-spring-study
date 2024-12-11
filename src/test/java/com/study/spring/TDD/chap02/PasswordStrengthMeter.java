package com.study.spring.TDD.chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        // null or Empty
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;

        // 충족 카운트
        int meetCounts = getMetCriteriaCounts(s);

        if (meetCounts <= 1) return PasswordStrength.WEAK;
        if (meetCounts == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private static int getMetCriteriaCounts(String s) {
        int meetCounts = 0;

        // 8글자 제한
        if (s.length() >= 8) meetCounts++;
        // 0부터 9사이의 숫자 포함
        if (meetsContainingNumberCriteria(s)) meetCounts++;
        // 대문자 포함 확인
        if (meetsContainingUppercaseCriteria(s)) meetCounts++;
        return meetCounts;
    }

    private static boolean meetsContainingUppercaseCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }

    private static boolean meetsContainingNumberCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }
}
