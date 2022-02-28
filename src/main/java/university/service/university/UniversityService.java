package university.service.university;

import org.springframework.stereotype.Service;
import university.dto.university.UniversityDto;
import university.entity.university.University;
import university.repository.address.AddressRepo;
import university.repository.university.UniversityRepo;

import java.util.List;

@Service
public class UniversityService {
    private final UniversityRepo repo;
    private final AddressRepo addressRepo;
    private final FacultyService facultyService;
    private final TeacherService teacherService;

    public UniversityService(UniversityRepo repo, AddressRepo addressRepo, FacultyService facultyService, TeacherService teacherService) {
        this.repo = repo;
        this.addressRepo = addressRepo;
        this.facultyService = facultyService;
        this.teacherService = teacherService;
    }

    public List<University> getAll() {
        return repo.findAll();
    }

    public University get(Long id) {
        if (!repo.existsById(id)) return new University("there aren't got with that id");

        return repo.getById(id);
    }

    public String edit(Long id, University dto) {
        if (dto.getTitle().equals("")) return "the title mustn't be empty";
        if (!repo.existsById(id)) return "there aren't got any university";

        University university = repo.getById(id);
        university.setTitle(dto.getTitle());
        repo.save(university);

        return "successfully edited";
    }

    public String add(UniversityDto dto) {
        if (dto.getTitle().equals("")) return "the title must not be empty";
        if (
                !addressRepo.existsByTitleAndNumber(
                        dto.getAddressTitle(),
                        dto.getAddressNumber()
                )
        ) return "there aren't got any address";
        if (
                repo.existsByAddress(
                        addressRepo.getByTitleAndNumber(
                                dto.getAddressTitle(),
                                dto.getAddressNumber()
                        )
                )
        ) return "the address is already used";

        repo.save(
                new University(
                        dto.getTitle(),
                        addressRepo.getByTitleAndNumber(
                                dto.getAddressTitle(),
                                dto.getAddressNumber()
                        )
                )
        );
        return "successfully created";
    }

    public String delete(Long id) {
        facultyService.deleteFaculty(id);
        teacherService.deleteTeachers(id);
        repo.deleteById(id);
        return "successfully deleted";
    }
}
