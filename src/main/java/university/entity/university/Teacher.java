package university.entity.university;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @ManyToOne
    private University university;

    @ManyToOne
    private Subject subject;

    public Teacher(String firstname) {
        this.firstname = firstname;
    }

    public Teacher(String firstname, String lastname, University university, Subject subject) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.university = university;
        this.subject = subject;
    }
}
