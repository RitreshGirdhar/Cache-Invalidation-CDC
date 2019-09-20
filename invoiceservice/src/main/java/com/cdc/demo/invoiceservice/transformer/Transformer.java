package com.cdc.demo.invoiceservice.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Transformer<T, R> {

    public abstract R transform(T t);

    public List<R> transformList(List<T> fromList) {

        if (fromList == null) {
            return new ArrayList<>();
        }

        return fromList.stream().map(this::transform).collect(Collectors.toList());

    }


}
