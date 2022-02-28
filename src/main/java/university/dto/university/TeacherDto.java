package university.dto.university;

import lombok.Data;

@Data
public class TeacherDto {
    private String firstname;
    private String lastname;
    private String subjectTitle;
    private Long universityId;
}
