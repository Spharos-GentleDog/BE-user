package egenius.address.infrastructure;

import egenius.address.entity.AddressList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressListRepository extends JpaRepository<AddressList, Long> {

    AddressList findByUserIdAndDefaultAddress(Long userId, Boolean defaultAddress);
    List<AddressList> findAllByUserId(Long userId);
    AddressList findByAddressId(Long addressId);
    AddressList findByUserIdAndAddressId(Long userId, Long addressId);

}
