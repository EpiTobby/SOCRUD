package fr.tobby.socrud.service;

import fr.tobby.socrud.model.Searchable;

import java.util.Collection;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ProgramComparator implements Comparator<Searchable> {
    private final Collection<String> keywords;

    public ProgramComparator(final Collection<String> keywords)
    {
        this.keywords = keywords;
    }

    @Override
    public int compare(final Searchable o1, final Searchable o2)
    {
        return 0;
    }

    float evaluate(final String input)
    {
        int foundCount = 0;
        int occurrences = 0;
        for (final String keyword : keywords)
        {
            int i = countOccurrences(input, keyword);
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
