package com.FullStackFinalProject.CRUDFullStack.repository;

import com.FullStackFinalProject.CRUDFullStack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
