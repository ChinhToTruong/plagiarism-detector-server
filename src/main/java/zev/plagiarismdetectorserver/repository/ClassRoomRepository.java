package zev.plagiarismdetectorserver.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import zev.plagiarismdetectorserver.entity.ClassRoom;
import zev.plagiarismdetectorserver.entity.User;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, String>,
    PagingAndSortingRepository<ClassRoom, String> {

  Optional<ClassRoom> findClassRoomByName(String name);
}
