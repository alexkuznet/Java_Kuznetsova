import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class IncludeThreeMethods {

    public static void PrintStart() {
        System.out.println("========================Test method start");
    }

    public static void PrintEnd() {
        System.out.println("Test method end========================");
    }

    private static final Random random = new Random();
    private static Stream<Integer> randomScores() {
        return IntStream.range(0, 15)
                .mapToObj(i -> random.nextInt(101));
    }

    @Test
    @Tag("smoke")

    public void isEven()
    {
        PrintStart();
        int n = random.nextInt(100)+1;
        System.out.println(n);
        if (n % 2 == 0)
        {

            System.out.println("True");

        }
        else {
            System.out.println("False");

        }
        PrintEnd();
    }
    @Tag("smoke")
    @RepeatedTest(20)
    public void checkAccess() {
        PrintStart();
        int age = random.nextInt(100);
        System.out.println(age);
        if (age > 18) {

            System.out.println("Allowed");

        } else {
            System.out.println("Denied");

        }
        PrintEnd();

    }
    @Tag("smoke")
    @ParameterizedTest(name = "getGrade({0})")
    @MethodSource("randomScores")
    public void getGrade(int score) {
        PrintStart();

        String answer = (score <0 || score > 100) ? "Error":
                (score >= 81) ? "A" :
                        (score >= 61) ? "B" :
                                (score >= 41) ? "C" :
                                        (score >= 21) ? "D" :"E";
        System.out.println(answer);
        PrintEnd();
    }
}





