package university.repository.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.entity.university.Faculty;

@Repository
public interface FacultyRepo extends JpaRepository<Faculty, Long> {
    boolean existsByTitleAndUniversityId(String title, Long id);

    @Override
    void deleteAll();

    Faculty[] findAllByUniversityId(Long id);
}
