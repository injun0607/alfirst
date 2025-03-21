package org.alham.alhamfirst.repository.user;

import org.alham.alhamfirst.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository: JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.uuid = :uuid")
    fun findByUuid(@Param("uuid") uuid: String): User?

    @Query("SELECT u FROM User u WHERE u.oauthProvider = :oauthProvider AND u.oauthId = :oauthId")
    fun findByOauthProviderAndOauthId(@Param("oauthProvider") oauthProvider: String, @Param("oauthId") oauthId: String): User?



}
