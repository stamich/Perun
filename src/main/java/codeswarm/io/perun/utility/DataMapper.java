package codeswarm.io.perun.utility;

import java.util.Collection;
import java.util.stream.Collectors;

@FunctionalInterface
public interface DataMapper<F,T> {
    
    public T convert(F from);
    
    default public Collection<T> convertAll(Collection<F> elements) {
        return elements.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
