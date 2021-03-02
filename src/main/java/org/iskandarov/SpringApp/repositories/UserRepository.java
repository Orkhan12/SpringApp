package org.iskandarov.SpringApp.repositories;

import org.iskandarov.SpringApp.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

    User findById(Long id);

    @Override
    <S extends User> S saveAndFlush(S s);

    @Override
    User getOne(Integer integer);

    void deleteById(Long id);

    @Override
    <S extends User> List<S> findAll(Example<S> example);


}
