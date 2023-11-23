package egenius.address.infrastructure;
import org.springframework.data.repository.query.Param;
import egenius.address.entity.AddressList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressListRepository extends JpaRepository<AddressList, Long> {

    @Query("SELECT a FROM AddressList a " +
            "WHERE a.user.id = :userId " +
            "ORDER BY a.defaultAddress DESC")
    List<AddressList> findByUserId(@Param("userId") Long userId);
    AddressList findByUserIdAndDefaultAddress(Long userId, Boolean defaultAddress);
    AddressList findByUserIdAndAddressId(Long userId, Long addressId);
    AddressList findByAddressId(Long addressId);

}
