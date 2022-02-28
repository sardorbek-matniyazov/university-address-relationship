package university.entity.university;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    private University university;

    public Faculty(String title) {
        this.title = title;
    }

    public Faculty(String title, University university) {
        this.title = title;
        this.university = university;
    }
}
