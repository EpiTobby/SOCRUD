package fr.tobby.socrud.service;

import fr.tobby.socrud.model.Searchable;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public final class ProgramSearchService {

    @NotNull
    List<Searchable> search(final Collection<Searchable> searchables, final List<String> keywords)
    {
        ProgramComparator comparator = new ProgramComparator(keywords);
        return searchables.stream()
                          .sorted(comparator.reversed())
                          .toList();
    }
}

