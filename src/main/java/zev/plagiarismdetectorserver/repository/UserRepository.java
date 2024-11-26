package zev.plagiarismdetectorserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import zev.plagiarismdetectorserver.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>, PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);
}
