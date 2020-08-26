package codeswarm.io.perun.repository;

import codeswarm.io.perun.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findOne(Long id);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.roles=:roles")
    List<User> findAllByRoles(String role);

    List<User> findAll();

    @Modifying
    @Query("UPDATE User u SET u.password=?1 WHERE u.email=?2")
    void updateUserPassword(String password, String email);

    @Modifying
    @Query("UPDATE User u SET u.token=?1 WHERE u.id=?2")
    void updateUserToken(String token, Long id);

    @Modifying
    @Query("UPDATE User u SET u.enabled=?1 WHERE u.id=?2")
    void updateUserActivity(Boolean enabled, Long id);

    @Modifying
    @Query("UPDATE User u SET u.email=?1 WHERE u.id=?2")
    void updateUserEmail(String email, Long id);

    @Modifying
    @Query("update User u SET u.roles=?1 WHERE u.id=?2")
    void updateUserRole(String role, Long id);

    @Modifying
    @Query("UPDATE User u SET u.confirmed=?1 WHERE u.id=?2")
    void updateUserConfirmation(Boolean confirmed, Long id);

    void deleteById(Long id);
    void deleteAll();
}
