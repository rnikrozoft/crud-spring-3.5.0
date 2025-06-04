package com.example.demo.service;
import com.example.demo.model.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserIF{
    private final UserRepository userRepository;

    @Override
    public UserEntity newUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        userRepository.findById(user.getId()).orElseThrow(EntityNotFoundException::new);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userRepository.deleteById(id);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }
}
