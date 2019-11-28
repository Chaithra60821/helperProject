package functionInterface;

import java.util.function.Function;

public class FunctionEx {

    public static int testForFunctionInterfaceApply(Function<String, Integer> function) {
        return function.apply("John");
    }

    public static String testForFunctionInterfacethenApply(Function<String, String> function){
        Function<String, String> applyThen =  function.andThen( s -> s + " and i works in xxx");
        return applyThen.apply("I am ");
    }
}
