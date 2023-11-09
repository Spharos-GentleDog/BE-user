package egenius.address.infrastructure;

import egenius.address.entity.AddressList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressListRepository extends JpaRepository<AddressList, Long> {

    List<AddressList> findAllByUserId(Long userId);
    AddressList findByUserIdAndDefaultAddress(Long userId, Boolean defaultAddress);
    AddressList findByUserIdAndAddressId(Long userId, Long addressId);
    AddressList findByAddressId(Long addressId);

}
