package org.example;

import java.util.ArrayList;
import java.util.List;

public class OtherMethods {

    public static boolean isNegative(int n) {
        return (n < 0) ? true : false;
    }

    public static List<Integer> returnEvenElements(List<Integer> numbers) {
        List<Integer> evenNumbers = new ArrayList<>();

        for (int num : numbers) {
            if (num % 2 == 0) {
                evenNumbers.add(num);
            }
        }

        return evenNumbers;
    }

    public static boolean isEvenMistake(int n) {
        return n % 3 == 0;
    }
}
