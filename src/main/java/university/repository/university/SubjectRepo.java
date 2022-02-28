package university.repository.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.entity.university.Subject;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Long> {
    boolean existsByTitle(String title);

    Subject getByTitle(String title);
}
