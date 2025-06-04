package com.example.demo.mapping;

import com.example.demo.model.dto.UserDto;
import com.example.demo.model.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapping {

    public UserEntity toEntity(UserDto req) {
        return UserEntity.builder()
                .id(req.getId())
                .email(req.getEmail())
                .password(req.getPassword())
                .build();
    }

    public UserDto toDto(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .createTime(entity.getCreateTime())
                .updateTime(entity.getUpdateTime())
                .build();
    }

    public List<UserDto> toDtoList(List<UserEntity> entities) {
        return entities
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
