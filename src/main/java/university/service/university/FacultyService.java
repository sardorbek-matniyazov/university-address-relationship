package university.service.university;

import org.springframework.stereotype.Service;
import university.dto.university.FacultyDto;
import university.entity.university.Faculty;
import university.entity.university.Groups;
import university.repository.university.FacultyRepo;
import university.repository.university.UniversityRepo;

import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepo repo;
    private final UniversityRepo universityRepo;
    private final GroupsService groupsService;


    public FacultyService(FacultyRepo repo, UniversityRepo universityRepo, GroupsService groupsService) {
        this.repo = repo;
        this.universityRepo = universityRepo;
        this.groupsService = groupsService;
    }

    public List<Faculty> getAll() {
        return repo.findAll();
    }

    public Faculty get(Long id) {
        if (!repo.existsById(id)) return new Faculty("there aren't got with that id");
        return repo.getById(id);
    }

    public String edit(Long id, Faculty dto) {
        if (dto.getTitle().equals("")) return "the title mustn't be empty";
        if (!repo.existsById(id)) return "there aren't got any faculty";
        Faculty faculty = repo.getById(id);

        faculty.setTitle(dto.getTitle());
        repo.save(faculty);

        return "successfully edited";
    }

    public String add(FacultyDto dto) {
        if (dto.getUniversityId() == null)
            return "Oops, something went wrong !";
        if (!universityRepo.existsById(dto.getUniversityId()))
            return "there aren't any university with that id";
        if (repo.existsByTitleAndUniversityId(dto.getTitle(), dto.getUniversityId()))
            return "the faculty has already created";

        repo.save(
                new Faculty(
                        dto.getTitle(),
                        universityRepo.getById(
                                dto.getUniversityId()
                        )
                )
        );

        return "successfully created";
    }

    public String delete(Long id) {
        if (!repo.existsById(id)) return "there aren't got any faculty";

        groupsService.deleteGroups(id);
        repo.deleteById(id);
        return "successfully deleted";
    }

    public void deleteFaculty(Long id) {
        for (Faculty g: repo.findAllByUniversityId(id)) {
            groupsService.deleteGroups(id);
            repo.deleteById(id);
        }
    }
}
