package fr.tobby.socrud.service;

import fr.tobby.socrud.model.Searchable;
import fr.tobby.socrud.repository.ProgramRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProgramSearchServiceTest {
    private ProgramSearchService searchService;

    @BeforeEach
    void setUp()
    {
        searchService = new ProgramSearchService(Mockito.mock(ProgramRepository.class));
    }

    private Collection<Searchable> getSearchables()
    {
        return List.of(
                new Program(1, "Après les études vous serez capable de participer aux Comités d’Architecture pour garantir la bonne conformité des bonnes pratiques des APIs. Promouvoir les pratiques API First au sein du groupe. Rédiger / Maintenir un Guideline de développement d’API (création de modèle d’API, ...)"),
                new Program(2, "Après les études vous serez capable de participer aux Comités d’Architecture pour garantir la bonne conformité des bonnes pratiques de développement."),
                new Program(3, "Après les études vous serez capable de garantir la bonne conformité des bonnes pratiques des APIs. Promouvoir les pratiques API First au sein du groupe. Rédiger / Maintenir un Guideline de développement d’API (création de modèle d’API, ...)"),
                new Program(4, "Après les études vous serez capable de développer l’expérience utilisateur du produit."),
                new Program(5, "Après les études vous serez capable de garantir la bonne conformité des bonnes pratiques des APIs. Promouvoir les pratiques API First au sein du groupe. Développement en Java. Mise en place une architecture micro-services avec une structure de code reposant sur une architecture hexagonale.")
        );
    }

    @Test
    void shouldReturn51423WhenSearchIsCalledWithJavaApiArchitecture()
    {
        List<Searchable> result = searchService.search(getSearchables(), List.of("java", "api", "architecture"));

        assertEquals(5, result.size());
        assertTrue(result.get(0).getId() == 1 || result.get(0).getId() == 5);
        assertTrue(result.get(1).getId() == 1 || result.get(1).getId() == 5);
        assertEquals(4, result.get(2).getId());
        assertEquals(2, result.get(3).getId());
        assertEquals(3, result.get(4).getId());
    }
}

final record Program(long id, String description) implements Searchable {
    @Override
    public long getId()
    {
        return id;
    }

    @Override
    public String getDescription()
    {
        return description;
    }
}