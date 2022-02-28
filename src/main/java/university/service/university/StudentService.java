package university.service.university;

import org.springframework.stereotype.Service;
import university.dto.university.StudentDto;
import university.entity.university.Student;
import university.repository.university.GroupsRepo;
import university.repository.university.StudentRepo;

import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepo repo;
    private final GroupsRepo groupsRepo;

    public StudentService(StudentRepo repo, GroupsRepo groupsRepo) {
        this.repo = repo;
        this.groupsRepo = groupsRepo;
    }

    public List<Student> getAll() {
        return repo.findAll();
    }

    public Student get(Long id) {
        if (!repo.existsById(id)) return new Student("there aren't got with that id");
        return repo.getById(id);
    }

    public String edit(Long id, Student dto) {
        if (dto.getFirstname().equals("") || dto.getSurname().equals("")) return "the name mustn't be empty";
        if (!repo.existsById(id)) return "there aren't got any faculty";
        Student student = repo.getById(id);

        student.setFirstname(dto.getFirstname());
        student.setFirstname(dto.getSurname());
        repo.save(student);

        return "successfully edited";
    }

    public String add(StudentDto dto) {
        if (dto.getFirstname().equals("") || dto.getSurname().equals("")) return "the name mustn't be empty";

        if (dto.getGroupId() == null)
            return "Oops, something went wrong !";

        if (!groupsRepo.existsById(dto.getGroupId()))
            return "there aren't any group with that id";

        repo.save(
                new Student(
                        dto.getFirstname(),
                        dto.getSurname(),
                        groupsRepo.getById(
                                dto.getGroupId()
                        )
                )
        );

        return "successfully created";
    }

    public String delete(Long id) {
        if (!repo.existsById(id)) return "there aren't got any student";
        repo.delete(repo.getById(id));
        return "successfully deleted";
    }

    public void deleteStudents(Long id) {
        repo.deleteAll(repo.findAllByGroupId(id));
    }
}
