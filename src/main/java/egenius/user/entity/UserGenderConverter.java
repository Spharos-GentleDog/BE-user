package egenius.user.entity;

import egenius.global.Enums.BaseEnumConverter;

public class UserGenderConverter extends BaseEnumConverter<UserGenderStatus, Integer, String> {
    public UserGenderConverter() { super(UserGenderStatus.class); }
}
