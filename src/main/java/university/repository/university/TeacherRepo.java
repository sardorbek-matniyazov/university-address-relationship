package university.repository.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.entity.university.Teacher;

import java.util.List;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    List<Teacher> findAllByUniversityId(Long id);
}
