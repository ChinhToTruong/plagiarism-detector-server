package zev.plagiarismdetectorserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zev.plagiarismdetectorserver.entity.Document;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, String> {

  Optional<Document> findByTitle(String title);
}
