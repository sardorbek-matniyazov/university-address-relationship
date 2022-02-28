package university.dto.university;

import lombok.Data;
import university.entity.university.Subject;

import java.util.List;

@Data
public class GroupsDto {
    private String title;
    private Long facultyId;
    private List<Subject> subjects;
}
