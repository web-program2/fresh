package user_service.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo2 extends JpaRepository<Users, Long> {
    Optional<Users> findById(String id);
}
