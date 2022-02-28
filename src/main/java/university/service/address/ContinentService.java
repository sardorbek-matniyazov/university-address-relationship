package university.service.address;

import university.entity.address.Continent;
import university.repository.address.ContinentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinentService {
    private final ContinentRepo repo;

    public ContinentService(ContinentRepo repo) {
        this.repo = repo;
    }

    public List<Continent> getAll() {
        return repo.findAll();
    }

    public Continent get(Long id) {
        if (!repo.existsById(id)) return new Continent("There haven't got with selected id !");
        return repo.getById(id);
    }

    public String add(Continent continent) {
        if (continent.getTitle().trim().equals("")) return "The continent's name is empty";
        if (repo.existsByTitle(continent.getTitle().trim())) return "The continent already exist";
        repo.save(new Continent(continent.getTitle().trim()));
        return "the continent successfully added";
    }


    public String edit (Long id, Continent continent) {
        if (!repo.existsById(id)) return "There haven't got with selected id !";
        if (continent.getTitle().trim().equals("")) return "The continent's name is empty";
        if (repo.existsByTitle(continent.getTitle().trim())) return "The continent already exist";
        Continent myContinent = repo.getById(id);
        myContinent.setTitle(continent.getTitle().trim());
        repo.save(myContinent);
        return "the continent successfully edited";
    }

    public String delete(Long id) {
        if (!repo.existsById(id)) return "There haven't got with selected id !";
        repo.deleteById(id);
        return "the continent successfully deleted.";
    }
}
