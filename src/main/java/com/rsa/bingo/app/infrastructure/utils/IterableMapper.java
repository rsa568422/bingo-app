package com.rsa.bingo.app.infrastructure.utils;

import org.apache.commons.collections4.IterableUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class IterableMapper {

    private IterableMapper() { }

    public static <S, T> List<T> mapToList(Iterable<S> source, Function<S, T> mapper) {
        return new ArrayList<>(IterableUtils.toList(source).stream().map(mapper).toList());
    }
}
