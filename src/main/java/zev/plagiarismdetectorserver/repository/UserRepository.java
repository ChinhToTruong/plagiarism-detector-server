package zev.plagiarismdetectorserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zev.plagiarismdetectorserver.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
