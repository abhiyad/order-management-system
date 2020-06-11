package com.catalogue.accountservice.repositories;
import com.catalogue.accountservice.domain.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,String> {
    List<MyUser> findAll();
    MyUser findMyUserByUsername(String username);
}
