package university.entity.university;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import university.entity.address.Address;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToOne(optional = false)
    private Address address;

    public University(String title) {
        this.title = title;
    }

    public University(String title, Address address) {
        this.title = title;
        this.address = address;
    }
}
