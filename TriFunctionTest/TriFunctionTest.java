import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
interface TriFunction<A, B, C, R> {
    R apply(A a, B b, C c);

    default <V> TriFunction<A, B, C, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (A a, B b, C c) -> after.apply(apply(a, b, c));
    }
}

public class TriFunctionTest {
    public static void main(String[] args) {
        TriFunction sum3 = (a, b, c) -> (int) a + (int) b + (int) c;
        System.out.println(sum3.andThen((a) -> (int) a * 5).apply(1, 1, 1));
        System.out.println(sum3.andThen((a) -> {
                    System.out.println("A");
                    return (int) a * 5;
                }
        ).andThen((a) -> {
            System.out.println("B");
            return (int) a * 2;
        }).apply(1, 1, 1));
        sum3 = sum3.andThen((a) -> (int) a * 5);
        System.out.println(sum3.apply(1, 1, 1));
    }
}