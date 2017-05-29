package streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ConcatStreamsTest {

    @Test
    public void concat() throws Exception {
        Stream<String> first = Stream.of("a", "b", "c").parallel();
        Stream<String> second = Stream.of("X", "Y", "Z");
        List<String> strings = Stream.concat(first, second)
                .collect(Collectors.toList());
        List<String> stringList = Arrays.asList("a", "b", "c", "X", "Y", "Z");
        assertEquals(stringList, strings);
    }

    @Test
    public void concatThree() throws Exception {
        Stream<String> first = Stream.of("a", "b", "c").parallel();
        Stream<String> second = Stream.of("X", "Y", "Z");
        Stream<String> third = Stream.of("alpha", "beta", "gamma");

        List<String> strings = Stream.concat(Stream.concat(first, second), third)
                .collect(Collectors.toList());
        List<String> stringList = Arrays.asList("a", "b", "c", "X", "Y", "Z", "alpha", "beta", "gamma");
        assertEquals(stringList, strings);
    }

    @Test
    public void reduce() throws Exception {
        Stream<String> first = Stream.of("a", "b", "c").parallel();
        Stream<String> second = Stream.of("X", "Y", "Z");
        Stream<String> third = Stream.of("alpha", "beta", "gamma");
        Stream<String> fourth = Stream.empty();

        List<String> strings = Stream.of(first, second, third, fourth)
                .reduce(Stream.empty(), Stream::concat)
                .collect(Collectors.toList());

        List<String> stringList = Arrays.asList("a", "b", "c", "X", "Y", "Z", "alpha", "beta", "gamma");
        assertEquals(stringList, strings);
    }

    @Test
    public void flatMap() throws Exception {
        Stream<String> first = Stream.of("a", "b", "c").parallel();
        Stream<String> second = Stream.of("X", "Y", "Z");
        Stream<String> third = Stream.of("alpha", "beta", "gamma");
        Stream<String> fourth = Stream.empty();

        List<String> strings = Stream.of(first, second, third, fourth)
                .flatMap(Function.identity())
                .collect(Collectors.toList());
        List<String> stringList = Arrays.asList("a", "b", "c", "X", "Y", "Z", "alpha", "beta", "gamma");
        assertEquals(stringList, strings);
    }

    @Test
    public void concatParallel() throws Exception {
        Stream<String> first = Stream.of("a", "b", "c").parallel();
        Stream<String> second = Stream.of("X", "Y", "Z");
        Stream<String> third = Stream.of("alpha", "beta", "gamma");

        Stream<String> total = Stream.concat(Stream.concat(first, second), third);

        assertTrue(total.isParallel());
    }

    @Test
    public void reduceParallel() throws Exception {
        Stream<String> first = Stream.of("a", "b", "c").parallel();
        Stream<String> second = Stream.of("X", "Y", "Z");
        Stream<String> third = Stream.of("alpha", "beta", "gamma");

        Stream<String> total = Stream.of(first, second, third)
                .reduce(Stream.empty(), Stream::concat);

        assertTrue(total.isParallel());
    }

    @Test
    public void flatMapParallel() throws Exception {
        Stream<String> first = Stream.of("a", "b", "c").parallel();
        Stream<String> second = Stream.of("X", "Y", "Z");
        Stream<String> third = Stream.of("alpha", "beta", "gamma");
        Stream<String> fourth = Stream.empty();

        Stream<String> total = Stream.of(first, second, third, fourth)
                .flatMap(Function.identity());
        assertFalse(total.isParallel());
    }

    @Test
    public void flatMapFindFirst() throws Exception {
        Stream<String> first = Stream.of("a", "b", "c").parallel();
        Stream<String> second = Stream.of("X", "Y", "Z");
        Stream<String> third = Stream.of("alpha", "beta", "gamma");
        Stream<String> fourth = Stream.empty();

        Optional<String> optionalFirst = Stream.of(first, second, third, fourth)
                .flatMap(Function.identity())
                .findFirst();
        assertNotNull(optionalFirst);
    }

    @Test
    public void flatMapFindAny() throws Exception {
        Stream<String> first = Stream.of("a", "b", "c").parallel();
        Stream<String> second = Stream.of("X", "Y", "Z");
        Stream<String> third = Stream.of("alpha", "beta", "gamma");
        Stream<String> fourth = Stream.empty();

        Optional<String> optionalFirst = Stream.of(first, second, third, fourth)
                .flatMap(Function.identity())
                .findAny();
        assertNotNull(optionalFirst);
    }

}