package university.repository.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.entity.university.Groups;

@Repository
public interface GroupsRepo extends JpaRepository<Groups, Long> {
    @Override
    void deleteAll();

    boolean existsByTitleAndFacultyId(String title, long facultyId);

    Groups[] findAllByFacultyId(Long id);
}
