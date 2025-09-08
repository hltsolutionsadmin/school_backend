package com.hlt.usermanagement.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hlt.usermanagement.model.RoleModel;
import com.hlt.usermanagement.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserModel u WHERE u.email = :email AND u.id <> :userId")
    Boolean existsByEmailAndNotUserId(@Param("email") String email, @Param("userId") Long userId);

    Optional<UserModel> findByEmail(String email);

//    Page<UserModel> findByRolesContaining(RoleModel roleModel, Pageable pageable);

    List<UserModel> findByRolesContaining(RoleModel roleModel);

    Boolean existsByPrimaryContact(String primaryContact);

    Optional<UserModel> findByPrimaryContact(String primaryContact);

    Optional<UserModel> findByPrimaryContactHash(String primaryContact);

    Optional<UserModel> findByEmailHash(String emailHash);
}
