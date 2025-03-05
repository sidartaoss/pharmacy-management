package com.pharmacy.management.application;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);

    public <T> T execute(IN in, Presenter<OUT, T> presenter) {
        if (presenter == null) {
            throw new IllegalArgumentException("UseCase 'presenter' is required");
        }

        return presenter.apply(execute(in));
    }
}