package br.com.compass.core.repository;

import br.com.compass.core.domain.User;

public interface UserRepositoryInterface {
    void save(User user)  throws Exception;
}
