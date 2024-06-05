package com.tetianamakar.reportgenerator.service;

import com.tetianamakar.reportgenerator.converter.EntityConverter;
import com.tetianamakar.reportgenerator.entity.User;
import com.tetianamakar.reportgenerator.payload.request.UserRequest;
import com.tetianamakar.reportgenerator.payload.response.UserResponse;
import com.tetianamakar.reportgenerator.repository.UserRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        userRepository.save(user);
    }

    public List<UserResponse> getUsersByAgeOver(int age) {
        return userRepository.findAll().stream()
            .filter(user -> user.getAge() != null)
            .filter(user -> user.getAge() > age)
            .map(EntityConverter::convertUser)
            .collect(Collectors.toList());
    }

    public Set<String> getUserNamesByArticlesNumOver3() {
        return userRepository.getUserNamesByArticlesNumOver3();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User with id:" + id + " not found)"));
    }

}
