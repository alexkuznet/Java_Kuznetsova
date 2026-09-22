package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Task_1 {

    //Задание 1: разработать метод с сигнатурой publiс static boolean isEven(int n). Метод возвращает true, если число чётное, и false — если нечётное.
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int n = scanner.nextInt();
        isEven(n);
        scanner.close();

    }
    public static boolean isEven(int n)
    {

        if (n % 2 == 0)
        {

            System.out.println("True");
            return true;
        }
        else {
            System.out.println("False");
            return false;
        }
    }
}

class Task_2 {
    //Задание 2: разработать метод с сигнатурой public static String checkAccess(int age). Метод возвращает Allowed, если число строго больше 18, и Denied — если меньше. import java.util.Scanner;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        checkAccess(age);
        scanner.close();
    }
    public static String checkAccess(int age) {

        if (age > 18) {

            System.out.println("Allowed");
            return "Allowed";
        } else {
            System.out.println("Denied");
            return "Denied";
        }
    }
}
class Task_3
//Задание 3: разработать метод с сигнатурой public static boolean isPositive(int n). Метод должен возвращать true, если переданное число больше или равно нулю, и false, если переданное число меньше нуля. Проверка внутри метода должна происходить с помощью тернарного оператора. import java.util.Scanner
{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int n = scanner.nextInt();
        isPositive(n);
        scanner.close();
    }
    public static boolean isPositive(int n)
    {
        boolean result = (n >= 0);;
        System.out.println(result);
        return result;
    } }

class Task_4
//Задание 4: разработать метод с сигнатурой public static String getGrade(int score). Метод возвращает строку, соответствующую строгому вхождению в границы:
//
//0–20: E; 21–40: D; 41–60: C; 61–80: B; 81–100: A. Если переданное число не входит в границы — вернуть строку Error.

{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int score = scanner.nextInt();
        getGrade(score);
        scanner.close();
    }
    public static String getGrade(int score)
    {
        String answer = (score <0 || score > 100) ? "Error":
                (score >= 81) ? "A" :
                        (score >= 61) ? "B" :
                                (score >= 41) ? "C" :
                                        (score >= 21) ? "D" :
                                                (score >= 0) ? "E":"E";
        System.out.println(answer);
        return answer;
    } }

class Task_5
//Задание 5: разработать метод с сигнатурой public static String blastOff(int start). Метод принимает стартовое число (например, 5) и возвращает строку со всеми числами до 1 и словом «Поехали!» в конце (например, «5 4 3 2 1 Поехали!»).
{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int start = scanner.nextInt();
        blastOff(start);
        scanner.close();
    }
    public static String blastOff(int start)
    {
        StringBuilder result = new StringBuilder();
        for (int i = start;i >= 1; i--)
        {
            result.append(i).append(" ");
        }
        result.append("Go!");
        System.out.println(result.toString());
        return result.toString();
    } }

class Task_6
//Задание 6: разработать метод с сигнатурой publiс static int sumToN(int n). Метод возвращает сумму всех целых чисел от 1 до n. import
{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int n = scanner.nextInt();
        sumToN(n);
        scanner.close();
    }
    public static int sumToN(int n)
    {
        int sum = 0;
        for (int i = n;i >= 1; i--)
        {
            sum += i;
        }

        System.out.println(sum);
        return sum;
    } }

class Task_7
//Задание 7: разработать метод с сигнатурой publiс static boolean hasBug(String[] messages). Метод принимает массив строк и возвращает true, если хотя бы одна строка в массиве равна Bug. Сравнение можно выполнять без учёта регистра.
{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        String[] messages = new String[size];
        System.out.print("Enter the array elements (separated by a space: ");
        for (int i = 0; i < size; i++) {
            System.out.print("Element [" + i + "]: ");
            messages[i] = scanner.next();
        };
        boolean result = hasBug(messages);
        System.out.println("Result: " + result);
        scanner.close();
    }
    public static boolean hasBug(String[] messages)
    {
        {
            for (String msg : messages) {
                if (msg != null && msg.equalsIgnoreCase("Bug")) {
                    return true;
                }
            }

            return false;
        }
    }

}

class Task_8
//Задание 8: разработать метод с сигнатурой publiс static getEvenInRange(int start, int end). Метод принимает границы диапазона и возвращает строку, состоящую только из чётных чисел внутри этого промежутка (включая границы), разделённых пробелом. Перед первым и после последнего числа пробел не ставится. Например: (2, 5) -> “2 4”
{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the start of the range: ");
        int start = scanner.nextInt();

        System.out.print("Enter the end of the range: ");
        int end = scanner.nextInt();

        getEvenInRange(start, end);

        scanner.close();
    }

    public static String getEvenInRange(int start, int end) {
        StringBuilder sb = new StringBuilder();

        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {

                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(i);
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}

class Task_9
//Задание 9: разработать метод с сигнатурой publiс static public int findMax(int[] arr). Метод находит и возвращает самое большое число в переданном массиве.
{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];
        System.out.print("Enter the array elements (integers, separated by a space): ");
        for (int i = 0; i < size; i++) {
            System.out.print("Element [" + i + "]: ");
            arr[i] = scanner.nextInt();
        }
        findMax(arr);
        scanner.close();
    }
    public static int findMax(int[] arr)
    {
        int max = arr[0];
        for (int m : arr)

        {
            if (m > max)
            {
                max = m;
            }
        }

        System.out.println(max);
        return max;
    }
}

class Task_10
//Задание 10: разработать метод с сигнатурой publiс static String[] reverse(String[] arr). Метод возвращает новый массив, в котором элементы исходного массива расположены в обратном порядке. Например, {“One”, “Two”, “Zero”} -> {“Zero”, “Two”, “One}.
{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        String[] arr = new String[size];
        System.out.print("Enter the array elements (separated by a space: ");
        for (int i = 0; i < size; i++) {
            System.out.print("Element [" + i + "]: ");
            arr[i] = scanner.next();
        }
        String[] reversedArr = reverse(arr);
        scanner.close();
    }
    public static String[] reverse(String[] arr) { String[] result = new String[arr.length];

        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[arr.length - 1 - i];
        }
        System.out.println("Inside method: " + Arrays.toString(result));
        return result;
    }
}

class Task_11
//Задание 11: разработать метод с сигнатурой publiс static calcAverage(List list). Метод вычисляет и возвращает среднее арифметическое всех чисел в списке.
{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int size = scanner.nextInt();

        List<Integer> list = new ArrayList<>();
        System.out.print("Enter the array elements (integers, separated by a space): ");
        for (int i = 0; i < size; i++) {
            System.out.print("Element [" + i + "]: ");
            list.add(scanner.nextInt());
        }

        calcAverage(list);

        scanner.close();
    }

    public static double calcAverage(List<Integer> list) {

        if (list == null || list.isEmpty()) {
            System.out.println("0.0");
            return 0.0;
        }

        double sum = 0;
        for (int num : list) {
            sum += num;
        }

        double avg = sum / list.size();

        System.out.println("Average: " + avg);
        return avg;
    }
}

class Task_12
//Задание 12: разработать метод с сигнатурой publiс static List removeSpecificName(List list, String nameToRemove). Метод принимает список и имя, которое нужно исключить. Возвращает новый список, не содержащий указанного имени.
{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int size = scanner.nextInt();

        List<String> list = new ArrayList<>();
        System.out.print("Enter the array elements (integers, separated by a space): ");
        for (int i = 0; i < size; i++) {
            System.out.print("Element [" + i + "]: ");
            list.add(scanner.next());
        }
        System.out.print("Enter the number to remove: ");
        String nameToRemove = scanner.next();
        removeSpecificName(list, nameToRemove);

        scanner.close();
    }

    public static List<String> removeSpecificName(List<String> list, String nameToRemove)
    {
        List<String> new_list = new ArrayList<>();

        for (String name : list) {
            if (!name.equals(nameToRemove)) {
                new_list.add(name);
            }
        }

        System.out.println(new_list);
        return new_list;
    }
}