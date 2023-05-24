package org.nhnacademy.family.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class RandomNumberGenerator {

    public static BigDecimal generateRandomNumber(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int digit = secureRandom.nextInt(10); // 0 ~ 9
            sb.append(digit);
        }

        BigInteger bigInteger = new BigInteger(sb.toString());
        return new BigDecimal(bigInteger);
    }
}
