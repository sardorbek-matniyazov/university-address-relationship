package university.repository.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.entity.university.Student;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Student> findAllByGroupId(Long id);
}
