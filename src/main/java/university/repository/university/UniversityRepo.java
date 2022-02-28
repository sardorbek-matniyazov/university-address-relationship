package university.repository.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.entity.address.Address;
import university.entity.university.University;

@Repository
public interface UniversityRepo extends JpaRepository<University, Long> {
    boolean existsByAddress(Address byTitleAndNumber);
}
