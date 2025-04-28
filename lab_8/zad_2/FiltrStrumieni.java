import java.util.function.Predicate;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class FiltrStrumieni {
    static Predicate<Long> podzielnePrzez2 = x -> x % 2 == 0;

    static Predicate<Long> podzielnePrzez3 = x -> x % 3 == 0;

    static Predicate<Long> implikuje(Predicate<Long> pr1, Predicate<Long> pr2) {
        return x -> !pr1.test(x) || pr2.test(x);
    }

    public static void main(String[] args) {
        Stream<Long> strumien = LongStream.rangeClosed(1, 20).boxed();

        System.out.println("Tylko liczby parzyste:");
        LongStream.rangeClosed(1, 20)
                .filter(x -> podzielnePrzez2.test(x))
                .forEach(System.out::println);

        System.out.println("\nTylko liczby podzielne przez 3:");
        LongStream.rangeClosed(1, 20)
                .filter(x -> podzielnePrzez3.test(x))
                .forEach(System.out::println);

        System.out.println("\nFiltr implikacji: liczby nieparzyste oraz parzyste podzielne przez 3:");
        LongStream.rangeClosed(1, 20)
                .filter(implikuje(podzielnePrzez2, podzielnePrzez3)::test)
                .forEach(System.out::println);
    }
}
