package org.iskandarov.SpringApp.repositories;

import org.iskandarov.SpringApp.entities.Role;
import org.iskandarov.SpringApp.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Override
    <S extends Role> List<S> findAll(Example<S> example);

    Role findByRole(String role);

    Role findById(Long id);

    @Override
    Role getOne(Integer integer);
}
