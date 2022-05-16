package fr.tobby.socrud.service;

import fr.tobby.socrud.entity.ProgramEntity;
import fr.tobby.socrud.model.ProgramModel;
import fr.tobby.socrud.model.Searchable;
import fr.tobby.socrud.repository.ProgramRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public final class ProgramSearchService {
    private final ProgramRepository programRepository;

    public ProgramSearchService(final ProgramRepository programRepository)
    {
        this.programRepository = programRepository;
    }

    public List<ProgramModel> search(Collection<String> keywords)
    {
        Collection<ProgramEntity> programs = programRepository.findAll();
        return search(programs.stream()
                              .map(ProgramModel::of)
                              .toList(), keywords);
    }

    @NotNull <T extends Searchable> List<T> search(final Collection<T> searchables, final Collection<String> keywords)
    {
        ProgramComparator comparator = new ProgramComparator(keywords);
        return searchables.stream()
                          .sorted(comparator.reversed())
                          .toList();
    }
}

