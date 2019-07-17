package com.oocl.web.sampleWebApp.jpaSample.repository;

import com.oocl.web.sampleWebApp.jpaSample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByName(String name);

    List<User> findByOrderByAgeDesc();

    @Query(value = "select distinct name from user", nativeQuery = true)
    List<String> findDistinctName();

    @Query(value = "delete from user where id = :userId", nativeQuery = true)
    @Modifying
    void deleteByIdNative(@Param("userId") Long id);

//    List<User> findUsersByNameIgnoreCase(String name);

//    @Override
//    void deleteById(Long id);
}
