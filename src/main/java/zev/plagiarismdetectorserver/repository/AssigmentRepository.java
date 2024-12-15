package zev.plagiarismdetectorserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zev.plagiarismdetectorserver.entity.Assigment;

@Repository
public interface AssigmentRepository extends JpaRepository<Assigment, String> {

}
