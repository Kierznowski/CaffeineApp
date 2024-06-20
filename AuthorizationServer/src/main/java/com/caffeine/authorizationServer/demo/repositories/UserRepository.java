package com.caffeine.authorizationServer.demo.repositories;


import com.caffeine.authorizationServer.demo.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
