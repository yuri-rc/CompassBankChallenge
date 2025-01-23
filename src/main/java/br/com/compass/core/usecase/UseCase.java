package br.com.compass.core.usecase;

public interface UseCase<I, O> {
    O execute(I input);
}
