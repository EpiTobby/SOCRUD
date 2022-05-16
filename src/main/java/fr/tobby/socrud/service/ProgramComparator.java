package fr.tobby.socrud.service;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class ProgramComparator extends ProgramComparatorTemplate {

    public ProgramComparator(final Collection<String> keywords)
    {
        super(keywords);
    }

    @Override
    protected float evaluate(String input)
    {
        input = input.toLowerCase();
        int foundCount = 0;
        int occurrences = 0;
        for (final String keyword : keywords)
        {
            int i = countOccurrences(input, keyword.toLowerCase());
            if (i > 0)
                foundCount++;
            occurrences += i;
        }

        // m * o - 0.5 * n * o
        return foundCount * occurrences - 0.5f * keywords.size() * occurrences;
    }

    private int countOccurrences(String input, String pattern)
    {
        // Search occurrences using a regex
        Matcher matcher = Pattern.compile(pattern).matcher(input);
        int occurrences = 0;
        while (matcher.find())
            occurrences++;
        return occurrences;
    }
}
