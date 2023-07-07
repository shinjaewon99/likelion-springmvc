package com.example.likelion.week14day5;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class JpaUserDetailManager implements UserDetailsManager {
    private final UserRepository userRepository;


    public JpaUserDetailManager(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
//        createUser(User.withUsername("user")
//                .password(passwordEncoder.encode("asdf"))
//                .build()
//        );

        createUser(CustomUserDetails.builder()
                .username("user")
                .password(passwordEncoder.encode("asdf"))
                .email("user@gmail.com")
                .build());
    }


    @Override
    // UserDetailService.loadUserByUsername(String)
    // 실제로 SpringSecurity 내부에서 사용하는 반드시 구현해야 정상 동작을 기대할수 있는 메소드
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> byUsername =
                userRepository.findByUsername(username);
        if (byUsername.isEmpty())
            throw new UsernameNotFoundException(username);

        UserEntity userEntity = byUsername.get();

        return User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .build();
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    // 새로운 사용자를 지정하는 메소드 (선택)
    public void createUser(UserDetails user) {
        // 사용자가 이미 있으면 생성 x
        if (userExists(user.getUsername()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        //        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername(user.getUsername());
//        userEntity.setPassword(user.getPassword());
//        this.userRepository.save(userEntity);
        try {
            userRepository.save(
                    ((CustomUserDetails) user).newEntity());
        } catch (ClassCastException e) {
            log.error("failed to cast to {}", CustomUserDetails.class);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    // 계정이름을 가진 사용자가 존재하는지 확인하는 메소드 (선택)
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }


}
