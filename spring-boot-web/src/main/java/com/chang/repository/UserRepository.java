package com.chang.repository;

import com.chang.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByUserNameOrEmail(String username, String email);

    @Query(value = "select * from `user` where create_time = :date", nativeQuery = true)
    User getByCreateTime(@Param("date") Date date);

    @Query(value = "select * from `user` where create_time = :date", nativeQuery = true)
    User getByCreateTimeStr(@Param("date") String date);

}