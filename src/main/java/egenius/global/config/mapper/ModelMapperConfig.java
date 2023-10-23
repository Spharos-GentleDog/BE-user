package egenius.global.config.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)  // private 필드에도 접근하여 매핑할 수 있도록 허용합니다.
                .setMatchingStrategy(MatchingStrategies.STRICT)  // 필드명이 완전히 일치하는 경우에만 매핑을 수행
                .setFieldMatchingEnabled(true);  // 필드 매칭 활성화
        return modelMapper;
    }
}