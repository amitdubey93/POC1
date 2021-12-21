package io.h2o.repository;

import io.h2o.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User where deleted = 0")
    List<User> getAll();

    @Query("from User where deleted = 0 and firstName = ?1")
    List<User> findByFirstName(String name);

    @Query("from User where deleted = 0 and pinCode = ?1")
    List<User> findByPinCode(String pinCode);

    @Query("from User where deleted = 0 order by doj desc")
    List<User> getUsersOrderByDoj();

    @Modifying
    @Query("update User set deleted = 1 where id = ?1")
    int deleteUserSoft(Long id);
}
