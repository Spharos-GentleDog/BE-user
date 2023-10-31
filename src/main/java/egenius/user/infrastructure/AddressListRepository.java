package egenius.user.infrastructure;

import egenius.user.entity.AddressList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressListRepository extends JpaRepository<AddressList, Long> {

}
