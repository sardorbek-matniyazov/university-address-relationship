package university.service.address;

import university.dto.address.RegionDto;
import university.entity.address.Country;
import university.entity.address.Region;
import university.repository.address.CountryRepo;
import university.repository.address.RegionRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RegionService {
    private final RegionRepo repo;
    private final CountryRepo countryRepo;

    public RegionService(RegionRepo repo, CountryRepo countryRepo) {
        this.repo = repo;
        this.countryRepo = countryRepo;
    }

    public List<Region> getAll() {
        return repo.findAll();
    }

    public Region get(Long id) {
        if (!repo.existsById(id)) return new Region("There haven't got with selected id !");
        return repo.getById(id);
    }

    public String add(RegionDto dto) {
        if (dto.getTitle().trim().equals("")) return "the region name can't be empty";
        if (!countryRepo.existsByTitle(dto.getCountryTitle())) return "the country isn't found";
        if (countryRepo.existsByTitleAndRegionsTitle(dto.getCountryTitle().trim(), dto.getTitle().trim()))
            return "the region already exist";
        Country myCountry = countryRepo.getByTitle(dto.getCountryTitle().trim());
        List<Region> regions = myCountry.getRegions();
        repo.save(new Region(dto.getTitle().trim()));
        regions.add(repo.getByTitle(dto.getTitle().trim()));
        myCountry.setRegions(regions);
        countryRepo.save(myCountry);
        return "the Region successfully saved";
    }

    public String edit(Long id, Region region) {
        if (!repo.existsById(id))
            return "There haven't got with selected id !";
        if (region.getTitle().trim().equals("")) return "the re name can't be empty";
        Region myRegion = repo.getById(id);
        myRegion.setTitle(region.getTitle());
        repo.save(myRegion);
        return "the region successfully edited";
    }

    public String delete(Long id) {
        if (!repo.existsById(id))
            return "There haven't got with selected id !";
        deleteRegion(repo.getById(id));
        repo.deleteById(id);
        return "the region successfully deleted";
    }

    private void deleteRegion(Region byId) {
        List<Region> myRegions = new ArrayList<>();
        Country country = countryRepo.getByRegions(byId);
        for (Region region: country.getRegions()) {
            if (!Objects.equals(region.getId(), byId.getId()))
                myRegions.add(region);
        }
        country.setRegions(myRegions);
        countryRepo.save(country);
    }

}
