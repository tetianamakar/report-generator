package com.tetianamakar.reportgenerator.repository;

import com.tetianamakar.reportgenerator.entity.User;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    Boolean existsByName(String name);

    @Query(nativeQuery = true, value = "SELECT u.name FROM users u INNER JOIN articles a ON u.id=a.user_id GROUP BY u.id, u.name HAVING COUNT(a.id)>3")
    Set<String> getUserNamesByArticlesNumOver3();

}
