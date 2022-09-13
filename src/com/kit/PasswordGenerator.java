package com.kit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordGenerator {
    static final List<Integer> ranges = new ArrayList<>();

    static String password (Integer length,boolean isLowerCase, boolean isCapital, boolean isNumbers, boolean isSpecialCh) {
        String result = "";

        if (!isLowerCase && !isCapital && !isNumbers && !isSpecialCh) {
            result = "Error! Select some options above.";
            return result;
        }

        if (isLowerCase) {
            addRange(97,122);
        }
        if (isNumbers) {
            addRange (48,57);
        }
        if (isCapital) {
            addRange(65,90);
        }
        if (isSpecialCh) {
            addRange(33,47);
        }

        for (int i = 0; i < length; i++) {
            int random = ranges.get(new Random().nextInt(ranges.size())); //capital letters
            char ch = (char) random;
            result+=ch;
        }
        return result;
    }

    private static void addRange(int min, int max) {
        for (int i = min; i <= max; i++) {
            ranges.add(i);
        }
    }

}
