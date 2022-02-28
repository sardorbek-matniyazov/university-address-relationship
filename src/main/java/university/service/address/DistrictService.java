package university.service.address;

import org.springframework.stereotype.Service;
import university.dto.address.DistrictDto;
import university.entity.address.District;
import university.entity.address.Region;
import university.repository.address.DistrictRepo;
import university.repository.address.RegionRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DistrictService {
    private final DistrictRepo repo;
    private final RegionRepo regionRepo;

    public DistrictService(DistrictRepo repo, RegionRepo regionRepo) {
        this.repo = repo;
        this.regionRepo = regionRepo;
    }

    public List<District> getAll() {
        return repo.findAll();
    }

    public District get(Long id) {
        if (!repo.existsById(id)) return new District("There haven't got with selected id !");
        return repo.getById(id);
    }

    public String add(DistrictDto dto) {
        if (dto.getTitle().trim().equals("")) return "the district name can't be empty";
        if (!regionRepo.existsByTitle(dto.getRegionTitle())) return "the Region isn't found";
        if (regionRepo.existsByTitleAndDistrictsTitle(dto.getRegionTitle().trim(), dto.getTitle().trim()))
            return "the district already exist";
        Region myRegion = regionRepo.getByTitle(dto.getRegionTitle().trim());
        List<District> districts = myRegion.getDistricts();
        repo.save(new District(dto.getTitle().trim()));
        districts.add(repo.getByTitle(dto.getTitle().trim()));
        myRegion.setDistricts(districts);
        regionRepo.save(myRegion);
        return "the district successfully saved";
    }

    public String edit(Long id, District district) {
        if (!repo.existsById(id))
            return "There haven't got with selected id !";
        if (district.getTitle().trim().equals("")) return "the district name can't be empty";
        District myDistrict = repo.getById(id);
        myDistrict.setTitle(district.getTitle());
        repo.save(myDistrict);
        return "the district successfully edited";
    }

    public String delete(Long id) {
        if (!repo.existsById(id))
            return "There haven't got with selected id !";
        deleteDistrict(repo.getById(id));
        repo.deleteById(id);
        return "the country successfully deleted";
    }

    private void deleteDistrict(District byId) {
        List<District> myDistricts = new ArrayList<>();
        Region region = regionRepo.getByDistricts(byId);
        for (District district: region.getDistricts()) {
            if (!Objects.equals(district.getId(), byId.getId()))
                myDistricts.add(district);
        }
        region.setDistricts(myDistricts);
        regionRepo.save(region);
    }
}
