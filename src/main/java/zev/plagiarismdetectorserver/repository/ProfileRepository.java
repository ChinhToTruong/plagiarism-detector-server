package zev.plagiarismdetectorserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zev.plagiarismdetectorserver.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
}
