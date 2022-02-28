package university.service.address;


import org.springframework.stereotype.Service;
import university.dto.address.AddressDto;
import university.entity.address.Address;
import university.entity.address.District;
import university.repository.address.AddressRepo;
import university.repository.address.DistrictRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AddressService {
    private final AddressRepo repo;
    private final DistrictRepo districtRepo;

    public AddressService(AddressRepo repo, DistrictRepo districtRepo) {
        this.repo = repo;
        this.districtRepo = districtRepo;
    }

    public List<Address> getAll() {
        return repo.findAll();
    }

    public Address get(Long id) {
        if (!repo.existsById(id)) return new Address("There haven't got with selected id !");
        return repo.getById(id);
    }

    public String add(AddressDto dto) {
        if (dto.getTitle().trim().equals("")) return "the address name can't be empty";
        if (dto.getDistrictTitle() == null)
            return "Oops, something went wrong !";

        if (dto.getNumber() == null) return "the address number can't be empty";
        if (!districtRepo.existsByTitle(dto.getDistrictTitle())) return "the District isn't found";
        if (repo.existsByTitleAndNumber(dto.getTitle(), dto.getNumber())){
            if (districtRepo.existsByTitleAndAddress(dto.getDistrictTitle().trim(),
                    repo.getByTitleAndNumber(dto.getTitle(), dto.getNumber())))
                return "the address already exist";
        }
        District myDistrict = districtRepo.getByTitle(dto.getDistrictTitle().trim());
        List<Address> addresses = myDistrict.getAddress();
        repo.save(new Address(dto.getTitle().trim(), dto.getNumber()));
        addresses.add(repo.getByTitleAndNumber(dto.getTitle().trim(), dto.getNumber()));
        myDistrict.setAddress(addresses);
        districtRepo.save(myDistrict);
        return "the address successfully saved";
    }

    public String edit(Long id, Address district) {
        if (!repo.existsById(id))
            return "There haven't got with selected id !";
        if (district.getTitle().trim().equals("")) return "the district name can't be empty";
        Address address = repo.getById(id);
        address.setTitle(district.getTitle());
        address.setNumber(address.getNumber());
        repo.save(address);
        return "the address successfully edited";
    }

    public String delete(Long id) {
        if (!repo.existsById(id))
            return "There haven't got with selected id !";
        deleteAddress(repo.getById(id));
        repo.deleteById(id);
        return "the country successfully deleted";
    }

    private void deleteAddress(Address byId) {
        List<Address> myAddresses = new ArrayList<>();
        District district = districtRepo.getByAddress(byId);
        for (Address address: district.getAddress()) {
            if (!Objects.equals(address.getId(), byId.getId()))
                myAddresses.add(address);
        }
        district.setAddress(myAddresses);
        districtRepo.save(district);
    }

}
