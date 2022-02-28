package university.service.university;

import org.springframework.stereotype.Service;
import university.dto.university.TeacherDto;
import university.entity.university.Student;
import university.entity.university.Subject;
import university.entity.university.Teacher;
import university.repository.university.*;

import java.util.Arrays;
import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepo repo;
    private final UniversityRepo universityRepo;
    private final SubjectRepo subjectRepo;

    public TeacherService(TeacherRepo repo, UniversityRepo universityRepo, SubjectRepo subjectRepo) {
        this.repo = repo;
        this.universityRepo = universityRepo;
        this.subjectRepo = subjectRepo;
    }

    public List<Teacher> getAll() {
        return repo.findAll();
    }

    public Teacher get(Long id) {
        if (!repo.existsById(id)) return new Teacher("there aren't got with that id");
        return repo.getById(id);
    }

    public String edit(Long id, Teacher dto) {
        if (dto.getFirstname().equals("") || dto.getLastname().equals(""))
            return "the name mustn't be empty";
        if (!repo.existsById(id)) return "there aren't got any student";

        Teacher teacher = repo.getById(id);
        teacher.setFirstname(dto.getFirstname());
        teacher.setLastname(dto.getLastname());
        repo.save(teacher);

        return "successfully edited";
    }

    public String add(TeacherDto dto) {
        if (dto.getFirstname().equals("") || dto.getLastname().equals("")) return "the name mustn't be empty";

        if (dto.getUniversityId() == null)
            return "Oops, something went wrong !";

        if (dto.getSubjectTitle() == null)
            return "Oops, something went wrong !";

        if (!universityRepo.existsById(dto.getUniversityId()))
            return "there aren't any university with that id";

        repo.save(
                new Teacher(
                        dto.getFirstname(),
                        dto.getLastname(),
                        universityRepo.getById(
                                dto.getUniversityId()
                        ),
                        getSubject(dto.getSubjectTitle())
                )
        );

        return "successfully created";
    }

    public String delete(Long id) {
        if (!repo.existsById(id)) return "there aren't got any student";

        repo.deleteById(id);
        return "successfully deleted";
    }

    private Subject getSubject(String subject) {
            if (
                    subjectRepo.existsByTitle(
                            subject
                    )
            ) return subjectRepo.getByTitle(subject);
            subjectRepo.save(
                        new Subject(
                                subject
                        )
                );

        return subjectRepo.getByTitle(subject);
    }

    public void deleteTeachers(Long id) {
        repo.deleteAll(repo.findAllByUniversityId(id));
    }
}
