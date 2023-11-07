package egenius.address.application;

import egenius.address.dto.AddressRegistrationRequestDto;
import egenius.address.entity.Address;
import egenius.address.entity.AddressList;
import egenius.address.infrastructure.AddressListRepository;
import egenius.address.infrastructure.AddressRepository;
import egenius.address.response.AddressInfoResponse;
import egenius.global.base.BaseResponseStatus;
import egenius.global.exception.BaseException;
import egenius.user.entity.User;
import egenius.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AddressServiceImple implements AddressService {

    /**
     * 1. 배송지 등록
     * 2. 배송지 조회
     * 3. 배송지 수정
     * 4. 대표 배송지 변경
     * 5. 배송지 삭제
     */

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AddressListRepository addressListRepository;
    private final ModelMapper modelMapper;

    /**
     * 1. 배송지 등록
     * @param userEmail
     * @param addressRegistrationRequestDto
     */
    @Override
    public void registerAddress(String userEmail, AddressRegistrationRequestDto addressRegistrationRequestDto) {
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));

        // 모델메퍼로 address 값 매핑
        Address address = modelMapper.map(addressRegistrationRequestDto, Address.class);

        addressRepository.save(address);

        /**
         * addressRegistrationRequestDto에서 true값이 들어오고 기존에 기존에 다른 default true값이 있다면 false로 변경 하고
         * address에 default true값을 넣어준다.
         */

        if (addressRegistrationRequestDto.getDefaultAddress()) {
            AddressList addressList1 = addressListRepository.findByUserIdAndDefaultAddress(user.getId(),
                    true);

            // ture값인 주소지가 있다면 false로 변경
            if (addressList1 != null) {
                addressList1.toBuilder()
                        .defaultAddress(false)
                        .build();
            }

            // 새로운 주소지를 default true로 저장
            AddressList addressList = AddressList.builder()
                    .user(user)
                    .address(address)
                    .defaultAddress(true)
                    .build();

            addressListRepository.save(addressList);

        } else {
            // addressRegistrationRequestDto에서 false값이 들어온 경우 새로운 주소지를 default false로 저장
            AddressList addressList = AddressList.builder()
                    .user(user)
                    .address(address)
                    .defaultAddress(false)
                    .build();

            addressListRepository.save(addressList);
        }
    }

    /**
     * @param userEmail
     * @return
     */
    @Override
    public List<AddressInfoResponse> findAddress(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));
        // 유저의 주소지 리스트를 조회
        List<AddressList> addressListList = addressListRepository.findAllByUserId(user.getId());

        // 주소지 리스트를 AddressInfoResponse로 매핑
        return addressListList.stream().map(item -> {
            // 주소지 엔터티를 조회
            Address address = item.getAddress();
            // 주소지 엔터티를 AddressInfoResponse로 매핑
            AddressInfoResponse addressInfoResponse = modelMapper.map(address, AddressInfoResponse.class);
            // 주소지 엔터티의 defaultAddress값을 AddressInfoResponse에 넣어준다.
            addressInfoResponse = addressInfoResponse.toBuilder()
                    .defaultAddress(item.getDefaultAddress())
                    .build();
            return addressInfoResponse;
        }).toList();

    }

    /**
     * @param addressId
     * @param addressRegistrationRequestDto
     */
    @Override
    public void updateAddress(Long addressId, AddressRegistrationRequestDto addressRegistrationRequestDto) {
        AddressList addressList = addressListRepository.findByAddressId(addressId);
        Address address = addressList.getAddress();
        address.updateAddress(addressRegistrationRequestDto);

    }

    /**
     * @param userEmail
     * @param oldAddressId
     * @param newAddressId
     */
    @Override
    public void updateDefaultAddress(String userEmail, Long oldAddressId, Long newAddressId) {

        User user  = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));

        // 1. oldAddressId가 null이 아니라면 기존의 defaultAddress를 false로 변경
        if (oldAddressId != null) {
            AddressList addressList = addressListRepository.findByAddressId(oldAddressId);
            addressList.toBuilder()
                    .defaultAddress(false)
                    .build();
        }

        // 2. newAddress로 addressList의 defaultAddress를 true로 변경
        AddressList addressList = addressListRepository.findByAddressId(newAddressId);
        addressList.toBuilder()
                .defaultAddress(true)
                .build();

    }

    /**
     * @param userEmail
     * @param addressId
     */
    @Override
    public void deleteAddress(String userEmail, Long addressId) {
        User user  = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));

        AddressList addressList = addressListRepository.findByUserIdAndAddressId(user.getId(), addressId);
        addressListRepository.delete(addressList);

    }
}
