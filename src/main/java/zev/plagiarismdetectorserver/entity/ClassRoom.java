package zev.plagiarismdetectorserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity(name = "ClassRoom")
@Table(name = "java_class_001")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String description;
    @ManyToMany(mappedBy = "classRooms", fetch = FetchType.LAZY, targetEntity = Profile.class)
    Set<Profile> users;
}
