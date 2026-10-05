import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.assertj.core.data.Percentage;
import org.example.MethodsForTesting;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.as;


public class IncludeAllMethods {

    private static final Random random = new Random();
    private static Stream<Integer> randomScores() {
        return IntStream.range(0, 15)
                .mapToObj(i -> random.nextInt(101));
    }


    @Tag("smoke")
    @RepeatedTest(10)
    public void isEven(RepetitionInfo info) {
        int n = random.nextInt(100);
        boolean expected = (n % 2 == 0);
        boolean actual = MethodsForTesting.isEven(n);
        Assertions.assertThat(actual)
                   .as("[Попытка %d] Число %d должно быть %s", info.getCurrentRepetition(), n, expected ? "чётным" : "нечётным")
                   .isEqualTo(expected);
        //if (actual == expected) {
           // System.out.println("TEST PASSED");
        //} else {
           // System.out.println("TEST FAILED");
       // }
    }

    @Tag("smoke")
    @RepeatedTest(10)
    public void checkAccess(RepetitionInfo info) {
        int age = random.nextInt(100);
        String actual = MethodsForTesting.checkAccess(age);
        String expected = (age > 18) ? "Allowed" : "Denied";
        Assertions.assertThat(actual)
                .as("[Попытка %d] Доступ для возраста %d должен быть %s",info.getCurrentRepetition(),age, expected)
                .isEqualTo(expected);
//        if (actual == expected) {
//            System.out.println("TEST PASSED");
//        } else {
//            System.out.println("TEST FAILED");
//        }
    }

    @Tag("smoke")
    @RepeatedTest(10)
    public void isPositive(RepetitionInfo info)
    {
        int n = random.nextInt();
        boolean expected = (n >0);
        boolean actual = MethodsForTesting.isPositive(n);
        org.junit.jupiter.api.Assertions.assertEquals(expected, actual,
                "Для числа " + n + " результат не совпал (попытка " + info.getCurrentRepetition() + "/10)");
//        if (actual == expected) {
//            System.out.println("TEST PASSED");
//        } else {
//            System.out.println("TEST FAILED");
//        }

    }

@Tag("smoke")
@ParameterizedTest(name = "getGrade({0})")
@MethodSource("randomScores")
public void getGrade(int score) {

    String actual = MethodsForTesting.getGrade(score);
    String expected;
    if (score < 0 || score > 100) {
        expected = "Error";
    } else if (score >= 81) {
        expected = "A";
    } else if (score >= 61) {
        expected = "B";
    } else if (score >= 41) {
        expected = "C";
    } else if (score >= 21) {
        expected = "D";
    } else {
        expected = "E";
    }
    org.junit.jupiter.api.Assertions.assertEquals(expected,actual, "Для балла '" + score + "' ожидалась оценка '" + expected + "' однако, пришла '" + actual + "'");
//    if (actual.equals(expected)) {
//        System.out.println("TEST PASSED");
//    } else {
//        System.out.println("TEST FAILED");
//    }
}
@Tag("smoke")
@RepeatedTest(10)
public void blastOff(RepetitionInfo info)
    {
        int start = random.nextInt(50);
        String actual = MethodsForTesting.blastOff(start);
        String expected;

        StringBuilder result = new StringBuilder();
        for (int i = start;i >= 1; i--)
        {
            result.append(i).append(" ");
        }
        result.append("Поехали!");
        expected = result.toString();
        Assertions.assertThat(actual)
                .as("[Попытка %d] Ожидалась строка %s, а пришла %s",info.getCurrentRepetition(),expected, actual)
                .isEqualTo(expected);
//        if (actual.equals(expected)) {
//            System.out.println("TEST PASSED");
//        } else {
//            System.out.println("TEST FAILED");
//        }
    }


@Tag("smoke")
@RepeatedTest(10)
public void sumToN(RepetitionInfo info)
{
    int n = random.nextInt(100);
    int actual = MethodsForTesting.sumToN(n);
    int expected;
    int sum = 0;
    for (int i = n;i >= 1; i--)
    {
        sum += i;
    }
    expected = sum;
    Assertions.assertThat(actual)
            .as("[Попытка %d] Ожидалось число %d, а пришло %d",info.getCurrentRepetition(),expected, actual)
            .isEqualTo(expected);
//    if (actual == expected) {
//        System.out.println("TEST PASSED");
//    } else {
//        System.out.println("TEST FAILED");
//    }
}
    @Tag("smoke")
    @RepeatedTest(10)
    public void hasBug(RepetitionInfo info){
        String[] options = {"Info", "Warning", "Bug", "Error", null};
            int size = random.nextInt(10) + 1;
            String[] messages = new String[size];
            for (int i = 0; i < size; i++) {
                messages[i] = options[random.nextInt(options.length)];
            }
        System.out.print("Input: [");
            for (int i = 0; i < messages.length; i++) {
                System.out.print(messages[i]);
                if (i < messages.length - 1){
                    System.out.print(", ");
                }
            }
        System.out.println("]");
            boolean expected = false;
            for (String msg : messages) {
                if (msg != null && msg.equalsIgnoreCase("Bug")) {
                    expected = true;
                    break;
                }
            }

            boolean actual = MethodsForTesting.hasBug(messages);
            Assertions.assertThat(actual)
                .as("[Попытка %d] Для массива, содержащего 'Bug', ожидалось true, но получено %b",info.getCurrentRepetition(), actual)
                .isEqualTo(expected);

            /*if (actual == expected) {
                System.out.println("TEST PASSED");
            } else {
                System.out.println("TEST FAILED");
            }*/
    }
@Tag("smoke")
@RepeatedTest(10)
public void GetEvenInRange(RepetitionInfo info) {
            int start = random.nextInt(50);
            int end = start + random.nextInt(50);
            String actual = MethodsForTesting.getEvenInRange(start, end);

            StringBuilder sb = new StringBuilder();
            for (int i = start; i <= end; i++) {
                if (i % 2 == 0) {
                    if (sb.length() > 0) sb.append(" ");
                    sb.append(i);
                }
            }
            String expected = sb.toString();
    org.junit.jupiter.api.Assertions.assertEquals(expected, actual,
            "Для диапазона [" + start + "; " + end + "] ожидалась строка '" + expected
                    + "' однако, пришла '" + actual + "' (попытка " + info.getCurrentRepetition() + "/10)");
//            if (actual.equals(expected)) {
//                System.out.println("TEST PASSED");
//            } else {
//                System.out.println("TEST FAILED");
//            }
        }

    @Tag("smoke")
    @RepeatedTest(10)
    public void findMax(RepetitionInfo info){
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(200) - 10;
        }
        int actualMax = MethodsForTesting.findMax(arr);
        int expectedMax = arr[0];
        for (int num : arr) {
            if (num > expectedMax) expectedMax = num;
        }
        Assertions.assertThat(actualMax)
                .as("[Попытка %d] Ожидалось число %d, а пришло %d",info.getCurrentRepetition(),expectedMax, actualMax)
                .isEqualTo(expectedMax);

//        if (actualMax == expectedMax) {
//            System.out.println("TEST PASSED");
//        } else {
//            System.out.println("TEST FAILED");
//        }
    }

    @Tag("smoke")
    @RepeatedTest(10)
    public void reverse(RepetitionInfo info){
        String[] arr = new String[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "Val" + random.nextInt(200);
        }
        String[] actual = MethodsForTesting.reverse(arr);
        String[] expected = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            expected[i] = arr[arr.length - 1 - i];
        }
        Assertions.assertThat(actual)
                .as("[Попытка %d] Метод reverse должен корректно развернуть массив из 10 элементов", info.getCurrentRepetition())
                .isEqualTo(expected);
//        if (Arrays.equals(actual, expected)) {
//            System.out.println("TEST PASSED");
//        } else {
//            System.out.println("TEST FAILED");
//    }

    }
    @Tag("smoke")
    @RepeatedTest(10)
    public void calcAverage(RepetitionInfo info) {
        int size = random.nextInt(10) + 1;

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(100) - 100);
        }

        double  actualAvg = MethodsForTesting.calcAverage(list);
        double sum = 0;
        for (int num : list) sum += num;
        double expectedAvg = sum / list.size();

        double delta = Math.abs(actualAvg - expectedAvg);

        Assertions.assertThat(actualAvg)
                .as("[Попытка %d] Среднее арифметическое для списка размера %d должно быть близко к %f",info.getCurrentRepetition(), list.size(), expectedAvg)
                .isCloseTo(expectedAvg, Offset.offset(0.0001));
//        if (delta < 0.0001) {
//            System.out.println("TEST PASSED");
//        } else {
//            System.out.println("TEST FAILED");
//        }
}
    @Tag("smoke")
    @RepeatedTest(10)
    public void removeSpecificName(RepetitionInfo info) {
        String[] namesFC = {"Liverpool", "FC Barcelona", "Chelsea", "Real Madrid"};
        int size = random.nextInt(8) + 1;
        List<String> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            list.add(namesFC[random.nextInt(namesFC.length)]);
        }

        String nameToRemove = "Real Madrid";
        List<String> expectedList = new ArrayList<>();
        for (String name : list) {
            if (!name.equals(nameToRemove)) {
                expectedList.add(name);
            }
        }
        List<String> actualList = MethodsForTesting.removeSpecificName(list, nameToRemove);
        list = actualList;
        Assertions.assertThat(actualList)
                .as("[Попытка %d] После удаления '%s' из списка длины %d ожидаем %s, а получили %s",info.getCurrentRepetition(),
                        nameToRemove, list.size(), expectedList, actualList)
                .isEqualTo(expectedList);

//        if (actualList.equals(expectedList)) {
//
//            System.out.println("TEST Passed");
//        } else {
//
//            System.out.println("TEST FAILED");
//
//        }
}
}



