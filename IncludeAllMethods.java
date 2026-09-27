import com.sun.tools.javac.Main;
import org.example.MethodsForTesting;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class IncludeAllMethods {

    private static final Random random = new Random();
    private static Stream<Integer> randomScores() {
        return IntStream.range(0, 15)
                .mapToObj(i -> random.nextInt(101));
    }

    @Test
    @Tag("smoke")
    public void isEven() {
        int n = random.nextInt(100);
        boolean expected = (n % 2 == 0);
        boolean actual = MethodsForTesting.isEven(n);
        if (actual == expected) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @Tag("smoke")
    @RepeatedTest(5)
    public void checkAccess() {
        int age = random.nextInt(100);
        String actual = MethodsForTesting.checkAccess(age);
        String expected = (age > 18) ? "Allowed" : "Denied";

        if (actual == expected) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @Tag("smoke")
    @Test
    public void isPositive()
    {
        int n = random.nextInt();
        boolean expected = (n >0);
        boolean actual = MethodsForTesting.isPositive(n);
        if (actual == expected) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }

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

    if (actual.equals(expected)) {
        System.out.println("TEST PASSED");
    } else {
        System.out.println("TEST FAILED");
    }
}
@Tag("smoke")
@Test
public void blastOff()
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
        if (actual.equals(expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }


@Tag("smoke")
@Test
public void sumToN()
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
    if (actual == expected) {
        System.out.println("TEST PASSED");
    } else {
        System.out.println("TEST FAILED");
    }
}
    @Tag("smoke")
    @Test
    public void hasBug(){
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

            if (actual == expected) {
                System.out.println("TEST PASSED");
            } else {
                System.out.println("TEST FAILED");
            }
    }
@Tag("smoke")
@Test
public void GetEvenInRange() {
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
            if (actual.equals(expected)) {
                System.out.println("TEST PASSED");
            } else {
                System.out.println("TEST FAILED");
            }
        }

    @Tag("smoke")
    @Test
    public void findMax(){
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(200) - 10;
        }
        int actualMax = MethodsForTesting.findMax(arr);
        int expectedMax = arr[0];
        for (int num : arr) {
            if (num > expectedMax) expectedMax = num;
        }
        if (actualMax == expectedMax) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
    }

    @Tag("smoke")
    @Test
    public void reverse(){
        String[] arr = new String[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "Val" + random.nextInt(200);
        }
        String[] actual = MethodsForTesting.reverse(arr);
        String[] expected = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            expected[i] = arr[arr.length - 1 - i];
        }
        if (Arrays.equals(actual, expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
    }

    }
    @Tag("smoke")
    @Test
    public void calcAverage() {
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

        if (delta < 0.0001) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
}
    @Tag("smoke")
    @Test
    public void removeSpecificName() {
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

        if (actualList.equals(expectedList)) {

            System.out.println("TEST Passed");
        } else {

            System.out.println("TEST FAILED");

        }
}
}



