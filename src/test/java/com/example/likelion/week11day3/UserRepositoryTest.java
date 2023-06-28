package com.example.likelion.week11day3;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RequiredArgsConstructor
public class UserRepositoryTest {
    private final UserRepository userRepository;

    // 새 UserEntity를 데이터베이스 추가 성공

    @Test
    void testSaveNew() {
        // given : 테스트가 진행되기 위한 전제 조건 준비 하는 구간
        String username = "jaewon";
        UserEntity user = new UserEntity();
        user.setUsername(username);
        // when : 테스트 하고싶은 "실제 기능" 을 작성하는 구간
        user = userRepository.save(user);

        // then : 실행한 결과가 기대한 것과 같은지를 "검증" 하는 구간
        // 1. 새로 반환되는 user의 id는 널이 아님
        assertNotNull(user.getId());
        // 2. 새로 반환받은 username은 우리가 넣었던 username과 동일
        assertEquals(username, user.getUsername());
    }

    @Test
    void testSaveNewFail() {
        String username = "jaewon";
        UserEntity userGiven = new UserEntity();
        userGiven.setUsername(username);
        userRepository.save(userGiven);


        // when
        UserEntity user = new UserEntity();
        user.setUsername(username);

        // then : 예외발생 기대
        assertThrows(Exception.class, () -> userRepository.save(user));
    }


    @Test
    void testFindName() {
        String username = "jaewon";
        UserEntity userGiven = new UserEntity();
        userGiven.setUsername(username);
        userRepository.save(userGiven);


        // when

        Optional<UserEntity> findUsername =
                userRepository.findByUsername(username);


        // then
        assertTrue(findUsername.isPresent());
        assertEquals(userGiven.getUsername(), findUsername.get().getUsername());

    }
}
