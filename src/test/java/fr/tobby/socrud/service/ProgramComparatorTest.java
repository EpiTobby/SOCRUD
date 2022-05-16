package fr.tobby.socrud.service;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProgramComparatorTest {

    @NotNull
    private ProgramComparator getComparator(List<String> keywords)
    {
        return new ProgramComparator(keywords);
    }

    @Test
    void shouldReturn0WhenEvaluateIsCalledWithNoOccurrence()
    {
        String input = "foo bar bar";
        ProgramComparator comparator = getComparator(List.of("baz"));

        float result = comparator.evaluate(input);
        assertEquals(0, result);
    }

    @Test
    void shouldReturn0Point5WhenEvaluateIsCalledWithOneKeywordAndOneOccurrence()
    {
        String input = "foo bar bar";
        ProgramComparator comparator = getComparator(List.of("foo"));

        float result = comparator.evaluate(input);
        assertEquals(0.5f, result);
    }

    @Test
    void shouldReturn1WhenEvaluateIsCalledWithOneKeywordAndTwoOccurrences()
    {
        String input = "foo bar bar";
        ProgramComparator comparator = getComparator(List.of("bar"));

        float result = comparator.evaluate(input);
        assertEquals(1f, result);
    }

    @Test
    void shouldReturnOWhenEvaluateIsCalledWithTwoKeywordsAndOneOccurrence()
    {
        String input = "foo bar bar";
        ProgramComparator comparator = getComparator(List.of("foo", "baz"));

        float result = comparator.evaluate(input);
        assertEquals(0f, result);
    }

    @Test
    void shouldReturn2WhenEvaluateIsCalledWithTwoKeywordsAndTwoOccurrences()
    {
        String input = "foo bar bar baz";
        ProgramComparator comparator = getComparator(List.of("foo", "baz"));

        float result = comparator.evaluate(input);
        assertEquals(2f, result);
    }

    @Test
    void shouldReturn2WhenEvaluateIsCalledWithTwoKeywordsAndThreeOccurrences()
    {
        String input = "foo bar bar baz";
        ProgramComparator comparator = getComparator(List.of("foo", "bar"));

        float result = comparator.evaluate(input);
        assertEquals(3f, result);
    }

    @Test
    void shouldReturn6WhenEvaluateIsCalledWithThreeKeywordsAndFourOccurrences()
    {
        String input = "foo bar bar baz";
        ProgramComparator comparator = getComparator(List.of("foo", "bar", "baz"));

        float result = comparator.evaluate(input);
        assertEquals(6f, result);
    }

    @Test
    void shouldReturn0Point5WhenEvaluateIsCalledWithOneKeywordInUppercaseAndOneOccurrence()
    {
        String input = "foo bar bar";
        ProgramComparator comparator = getComparator(List.of("FOO"));

        float result = comparator.evaluate(input);
        assertEquals(0.5f, result);
    }
}