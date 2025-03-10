package org.alham.alhamfirst.repository.user;

import org.alham.alhamfirst.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository: JpaRepository<User, Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.scheduleList WHERE u.id = :userIdx")
    fun getUserByUserIdxWithSchedule(@Param(value = "userIdx")userIdx: Long): User;
}
