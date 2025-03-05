package com.pharmacy.management.domain.utils;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CollectionUtils {

    private CollectionUtils() {
    }

    public static <IN, OUT> Set<OUT> map(
            final Set<IN> aSet,
            final Function<IN, OUT> aMapper
    ) {
        if (aSet == null) {
            return null;
        }
        return aSet.stream()
                .map(aMapper)
                .collect(Collectors.toSet());
    }
}
