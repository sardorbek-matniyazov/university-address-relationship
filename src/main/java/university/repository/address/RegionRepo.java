package university.repository.address;


import org.springframework.data.jpa.repository.JpaRepository;
import university.entity.address.District;
import university.entity.address.Region;

public interface RegionRepo extends JpaRepository<Region, Long> {

    Region getByTitle(String trim);

    boolean existsByTitle(String regionTitle);

    boolean existsByTitleAndDistrictsTitle(String trim, String trim1);

    Region getByDistricts(District byId);
}
