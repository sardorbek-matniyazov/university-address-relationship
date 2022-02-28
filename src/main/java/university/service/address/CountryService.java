package university.service.address;

import university.dto.address.CountryDto;
import org.springframework.stereotype.Service;
import university.entity.address.Continent;
import university.entity.address.Country;
import university.repository.address.ContinentRepo;
import university.repository.address.CountryRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CountryService {
    private final CountryRepo repo;
    private final ContinentRepo continentRepo;

    public CountryService(CountryRepo repo, ContinentRepo continentRepo) {
        this.repo = repo;
        this.continentRepo = continentRepo;
    }

    public List<Country> getAll() {
        return repo.findAll();
    }

    public Country get(Long id) {
        if (!repo.existsById(id)) return new Country("There haven't got with selected id !");
        return repo.getById(id);
    }

    public String add(CountryDto dto) {
        if (dto.getTitle().trim().equals("")) return "the country name can't be empty";
        if (!continentRepo.existsByTitle(dto.getContinentTitle())) return "the continent isn't found";
        if (continentRepo.existsByTitleAndCountryTitle(dto.getContinentTitle().trim(), dto.getTitle().trim()))
            return "the country already exist";
        Continent myContinent = continentRepo.getByTitle(dto.getContinentTitle().trim());
        List<Country> country = myContinent.getCountry();
        repo.save(new Country(dto.getTitle().trim()));
        country.add(repo.getByTitle(dto.getTitle().trim()));
        myContinent.setCountry(country);
        continentRepo.save(myContinent);
        return "the country successfully saved";
    }

    public String edit(Long id, Country country) {
        if (!repo.existsById(id))
            return "There haven't got with selected id !";
        if (country.getTitle().trim().equals("")) return "the country name can't be empty";
        Country myCountry = repo.getById(id);
        myCountry.setTitle(country.getTitle());
        repo.save(myCountry);
        return "the country successfully edited";
    }

    public String delete(Long id) {
        if (!repo.existsById(id))
            return "There haven't got with selected id !";
        deleteCountry(repo.getById(id));
        repo.deleteById(id);
        return "the country successfully deleted";
    }

    private void deleteCountry(Country byId) {
        List<Country> myCountries = new ArrayList<>();
        Continent continent = continentRepo.getByCountry(byId);
        for (Country c: continent.getCountry()) {
            if (!Objects.equals(c.getId(), byId.getId()))
                myCountries.add(c);
        }
        continent.setCountry(myCountries);
        continentRepo.save(continent);
    }
}
