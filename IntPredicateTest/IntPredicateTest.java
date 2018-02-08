import java.util.function.IntPredicate;

public class IntPredicateTest {
    public static void main(String[] args) {
        IntPredicate tt = (i) -> i > 2;
        tt = tt.and((i) -> i < 5);
        System.out.println(tt.test(4));
    }
}