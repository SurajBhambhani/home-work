package com.sb.spring.authorization.user;

import com.sb.spring.authorization.exception.UserAlreadyFoundException;
import com.sb.spring.authorization.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserTO create(UserTO userTO) {
        log.info("create: Entering userDTO.getUsername(): {}", userTO.getUsername());
        Optional<UserEntity> optionalPrevUser = userRepository.findByUsername(userTO.getUsername());
        if (optionalPrevUser.isPresent()) {
            throw new UserAlreadyFoundException();
        }
        userTO.setId(UUID.randomUUID().toString());
        userTO.setRoles("ROLE_USER");
        userTO.setPassword(encoder.encode(userTO.getPassword()));

        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userTO, user);
        userRepository.save(user);
        log.info("Saved user with id: {}", user.getId());

        userTO.setPassword(null);
        return userTO;
    }

    public UserTO get(String id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException();
        }
        UserTO userTO = new UserTO();
        UserEntity user = optionalUser.get();
        log.info("Found user with id: {}", user.getId());
        BeanUtils.copyProperties(user, userTO);
        return userTO;
    }
}
