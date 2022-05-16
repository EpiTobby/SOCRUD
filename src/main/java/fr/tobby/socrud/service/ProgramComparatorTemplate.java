package fr.tobby.socrud.service;

import fr.tobby.socrud.model.Searchable;

import java.util.Collection;
import java.util.Comparator;

public abstract class ProgramComparatorTemplate implements Comparator<Searchable> {
    protected final Collection<String> keywords;

    protected ProgramComparatorTemplate(final Collection<String> keywords)
    {
        this.keywords = keywords;
    }

    @Override
    public int compare(final Searchable o1, final Searchable o2)
    {
        return Float.compare(evaluate(o1.getDescription()), evaluate(o2.getDescription()));
    }

    abstract float evaluate(String input);
}
