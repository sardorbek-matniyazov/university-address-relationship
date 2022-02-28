package university.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.entity.address.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

    Address getByTitleAndNumber(String title, Long number);

    boolean existsByTitleAndNumber(String title, Long number);
}
