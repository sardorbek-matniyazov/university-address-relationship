package university.entity.university;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    private Faculty faculty;

    @ManyToMany
    private List<Subject> subjects;

    public Groups(String title) {
        this.title = title;
    }

    public Groups(String title, Faculty faculty, List<Subject> subjects) {
        this.title = title;
        this.faculty = faculty;
        this.subjects = subjects;
    }
}
