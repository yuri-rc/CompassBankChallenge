package br.com.compass.core.voter;

public interface Voter<I> {
    void invoke(I useCaseInput);
}
