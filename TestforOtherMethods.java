import org.assertj.core.api.Assertions;
import org.example.MethodsForTesting;
import org.example.OtherMethods;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestforOtherMethods {

    private static final Random random = new Random();
    private static Stream<Integer> randomScores() {
        return IntStream.range(0, 15)
                .mapToObj(i -> random.nextInt(101));
    }

    @Tag("other")
    @Test
    public void isNegative()
    {
        int n = random.nextInt();
        boolean expected = (n < 0);
        boolean actual = OtherMethods.isNegative(n);
        String message = String.format("[Число %d должно быть %s, но фактическое значение: %b]",
                n, expected ? "отрицательным" : "неотрицательным", actual);

        assertThat(actual)
                .as(message)
                .isEqualTo(expected);

    }

    @Tag("other")
    @Test
    public void returnEvenElements()
    {
        List<Integer> multipleList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            multipleList.add(random.nextInt(20) - 10);
        }

        List<Integer> expectedMultiple = new ArrayList<>();
        for (int num : multipleList) {
            if (num % 2 == 0) {
                expectedMultiple.add(num);
            }
        }

        List<Integer> actualResult = OtherMethods.returnEvenElements(multipleList);

        assertThat(actualResult)
                .as("Ожидаемые и фактические чётные числа не совпадают")
                .containsExactlyElementsOf(expectedMultiple);

    }

    @Tag("other")
    @Test
    public void isEvenMistake()
    {
        int n = random.nextInt(100);
        boolean expected = (n % 2 == 0);
        boolean actual = OtherMethods.isEvenMistake(n);
        Assertions.assertThat(actual)
                .as("Число %d должно быть %s", n, expected ? "чётным" : "нечётным")
                .isEqualTo(expected);
    }

}
