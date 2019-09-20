package com.cdc.demo.customersservice.transformer;

public abstract class Transformer<T, R> {
    public abstract R transform(T t);
}
