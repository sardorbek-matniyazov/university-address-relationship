package university.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.entity.address.Address;
import university.entity.address.District;

@Repository
public interface DistrictRepo extends JpaRepository<District, Long> {

    District getByTitle(String trim);

    boolean existsByTitle(String districtTitle);

    boolean existsByTitleAndAddress(String trim, Address byTitleAndNumber);

    District getByAddress(Address byId);
}
