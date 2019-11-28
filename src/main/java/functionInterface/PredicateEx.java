package functionInterface;

import java.util.function.Predicate;

public class PredicateEx {

    public static boolean testPredicateForTestMethod(Predicate<String> predicate){
        return predicate.test("amar");
    }

    public static boolean testPredicateForNegateMethod(Predicate<String> predicate){
        return predicate.negate().test("amar");
    }

    public static boolean testPredicateForAndMethod(Predicate<String> predicate){
        return predicate.and(s -> s.length()== 4).test("amar");
    }

    public static boolean testPredicateForOrMethod(Predicate<String> predicate){
        return predicate.or(s -> s.length()== 4).test("test");
    }
}
