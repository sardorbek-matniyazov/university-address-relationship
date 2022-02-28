package university.service.university;

import org.springframework.stereotype.Service;
import university.dto.university.GroupsDto;
import university.entity.university.Groups;
import university.entity.university.Subject;
import university.repository.university.FacultyRepo;
import university.repository.university.GroupsRepo;
import university.repository.university.SubjectRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupsService {
    private final FacultyRepo facultyRepo;
    private final GroupsRepo repo;
    private final SubjectRepo subjectRepo;
    private final StudentService studentService;

    public GroupsService(FacultyRepo facultyRepo, GroupsRepo repo, SubjectRepo subjectRepo, StudentService studentService) {
        this.facultyRepo = facultyRepo;
        this.repo = repo;
        this.subjectRepo = subjectRepo;
        this.studentService = studentService;
    }

    public List<Groups> getAll() {
        return repo.findAll();
    }

    public Groups get(Long id) {
        if (!repo.existsById(id)) return new Groups("there aren't got with that id");
        return repo.getById(id);
    }

    public String edit(Long id, Groups dto) {
        if (dto.getTitle().equals("")) return "the title mustn't be empty";
        if (!repo.existsById(id)) return "there aren't got any group";

        Groups groups = repo.getById(id);
        groups.setTitle(dto.getTitle());
        groups.setSubjects(
                getSubjects(
                        dto.getSubjects()
                )
        );
        repo.save(groups);

        return "successfully edited";
    }

    public String add(GroupsDto dto) {
        if (dto.getTitle().equals("")) return "the title mustn't be empty";

        if (dto.getFacultyId() == null || dto.getSubjects() == null)
            return "Oops, something went wrong !";
        if (!facultyRepo.existsById(dto.getFacultyId()))
            return "there aren't any university with that id";
        if (repo.existsByTitleAndFacultyId(dto.getTitle(), dto.getFacultyId()))
            return "the group has already created";
        repo.save(
                new Groups(
                        dto.getTitle(),
                        facultyRepo.getById(
                                dto.getFacultyId()
                        ),
                        getSubjects(dto.getSubjects())
                )
        );

        return "successfully created";
    }

    public String delete(Long id) {
        if (!repo.existsById(id)) return "there aren't got any faculty";

        studentService.deleteStudents(id);
        repo.deleteById(id);
        return "successfully deleted";
    }

    private List<Subject> getSubjects(List<Subject> subjects) {
        List<Subject> list = new ArrayList<>();
        for (Subject s: subjects) {
            if (
                    subjectRepo.existsByTitle(
                            s.getTitle()
                    )
            ) list.add(subjectRepo.getByTitle(s.getTitle()));
            else {
                subjectRepo.save(
                        new Subject(
                                s.getTitle()
                        )
                );
                list.add(subjectRepo.getByTitle(s.getTitle()));
            }
        }
        return list;
    }

    public void deleteGroups(Long id) {
        for (Groups g: repo.findAllByFacultyId(id)) {
            studentService.deleteStudents(id);
            repo.deleteById(id);
        }
    }
}
