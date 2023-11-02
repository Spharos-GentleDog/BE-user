package egenius.address.infrastructure;

import egenius.address.entity.AddressList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressListRepository extends JpaRepository<AddressList, Long> {

}
