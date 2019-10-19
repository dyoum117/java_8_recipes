package currying;

import java.util.function.Function;

public class CurryExamples {
    public static void main(String[] args) {

        Function<Integer, Function<Integer, Integer>> curryAdd =
                (x) -> (y) -> x + y;

        int result = curryAdd.apply(10).apply(5);
        System.out.println("result: " + result);


    }

    /**
     * @param bar
     * @return foobar
     */
    private int foo(int bar)
    {
        return bar + 1;
    }
}
