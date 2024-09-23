package com.example.RollCall.repository;

import com.example.RollCall.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByUserName(String admin);

    boolean existsByUserName(String userName);

    List<Users> findByNameContaining(String name);
}
