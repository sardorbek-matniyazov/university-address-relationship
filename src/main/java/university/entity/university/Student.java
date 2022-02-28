package university.entity.university;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String surname;

    @ManyToOne
    private Groups group;

    public Student(String firstname) {
        this.firstname = firstname;
    }

    public Student(String firstname, String surname, Groups group) {
        this.firstname = firstname;
        this.surname = surname;
        this.group = group;
    }
}
