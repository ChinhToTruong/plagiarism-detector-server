package zev.plagiarismdetectorserver.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import zev.plagiarismdetectorserver.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>,
    PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User> {

  Optional<User> findByEmail(String email);
}
