package university.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import university.entity.address.Country;
import university.entity.address.Region;

@Controller
public interface CountryRepo extends JpaRepository<Country, Long> {
    Country getByTitle(String title);

    boolean existsByTitle(String name);

    Country getByRegions(Region byId);

    boolean existsByTitleAndRegionsTitle(String trim, String trim1);
}
