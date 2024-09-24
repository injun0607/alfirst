package org.alham.alhamfirst.repository.user;

import org.alham.alhamfirst.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.scheduleList WHERE u.id = :userIdx")
    public User getUserByUserIdxWithSchedule(@Param(value = "userIdx") long userIdx);
}
