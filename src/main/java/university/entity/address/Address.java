package university.entity.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "BigInt default 0")
    private Long number;

    public Address(String title) {
        this.title = title;
    }

    public Address(String title, Long number) {
        this.title = title;
        this.number = number;
    }
}
