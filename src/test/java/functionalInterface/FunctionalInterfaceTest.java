package functionalInterface;


import static org.junit.Assert.assertEquals;

import functionInterface.FunctionEx;
import functionInterface.PredicateEx;
import functionInterface.FunctionEx;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import org.junit.Test;

public class FunctionalInterfaceTest {

    @Test
    public void TestFunctionInterface(){
        //public interface Function<T, R> { … } : functionalInterface that receives one value and returns a one value
        Function<String, Integer> lengthOfString = s -> s.length();
        Integer lengthString = FunctionEx.testForFunctionInterfaceApply(lengthOfString);
        assertEquals((Integer) 4, lengthString);

        Map<String, Integer> map = new HashMap<>();
        Integer length = map.computeIfAbsent("John", s->s.length());
        assertEquals((Integer) 4, length);

        //Compose method of Functinal Interface : compose method that allows to combine several functions into one and execute them sequentially
        Function<String, String> firstFi = s -> s + " FirstFI";
        Function<String, String> secondFi = s -> s +" and adding the SecondFI";
        Function<String, String> quoateIntToString = secondFi.compose(firstFi);
        assertEquals("I am FirstFI and adding the SecondFI", quoateIntToString.apply("I am"));

        //ThenApply method
        Function<String, String> first = s -> s + "John";
        String finalValue = FunctionEx.testForFunctionInterfacethenApply(first);
        assertEquals("I am John and i works in xxx" , finalValue);
    }

    @Test
    public void PredicateFITest(){
        //predicate.test() method
        Predicate<String> predicate = s -> s.startsWith("a");
        boolean va = PredicateEx.testPredicateForTestMethod(predicate);
        assertEquals(true, va);

        //Predicate.negate()
        boolean negateValue = PredicateEx.testPredicateForNegateMethod(predicate);
        assertEquals(false, negateValue);

        //Predicate.and()
        boolean andMethodValue = PredicateEx.testPredicateForAndMethod(predicate);
        assertEquals(true, andMethodValue);

        //Predicate.or()
        boolean orMethodValue = PredicateEx.testPredicateForOrMethod(predicate);
        assertEquals(true, orMethodValue);
    }

    @Test
    public void BIFunctionalInterfaceTest(){
        BiFunction<Integer, Integer, Integer> biFun = (a, b) -> a+b;
        Integer sum = biFun.apply(2, 4);
        assertEquals((Integer) 6, sum);

        Integer sumAndMul = biFun.andThen(s -> s*2).apply(2, 4);
        assertEquals((Integer)12, sumAndMul);

        ToDoubleBiFunction<Integer, Integer> doubleBiFunction = (a, b) -> a + b;
        Double sumOfDouble = doubleBiFunction.applyAsDouble(2, 4);
        assertEquals((Double) 6.0, sumOfDouble);
    }

    @Test
    public void TestProducerFunctionalInterface(){
        int[] fibs ={ 0, 1};
        Supplier<Person> personSupplier = () -> {
            return new Person("aaa", "1234");
        };
        assertEquals("aaa", personSupplier.get().getName());
        assertEquals("1234", personSupplier.get().getIdNumber());

        Supplier<Person> personSupplier1 = () -> {
            return new Person("bbb", "12345");
        };
        assertEquals("bbb", personSupplier1.get().getName());
        assertEquals("12345", personSupplier1.get().getIdNumber());
    }

    @Test
    public void TestForConsumerInterface(){
        Consumer<String > consumer1 = (str) -> System.out.println(str + " FirstConsumer" );
        //consumer1.accept("Hello");

        Consumer<String > consumer2 = (str) -> System.out.print(str + " SecondConsumer");
        consumer1.andThen(consumer2).accept("Hello");
    }

}

class Person {
    private String name ;
    private String idNumber;



    public Person(String name, String idNumber){
        this.name = name;
        this.idNumber = idNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getName() {
        return name;
    }
}
