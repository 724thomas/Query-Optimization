package com.wonjoon.query_optimization;

import com.wonjoon.query_optimization.entity.User;
import com.wonjoon.query_optimization.repository.UserRepository;
import com.wonjoon.query_optimization.service.UserCachedService;
import com.wonjoon.query_optimization.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")  // local 프로필을 사용하여 MySQL 설정이 적용되도록 함
public class UserServicePerformanceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCachedService userCachedService;

    private User testUser;

    @BeforeEach
    void setup() {
        // 테스트 전에 유저 저장
        testUser = User.builder()
                .id(1L)
                .username("test")
                .email("test@gmail.com")
                .build();

        // 실제 DB에 테스트 유저 저장
        userRepository.save(testUser);
    }

    @Test
    void testCachedServicePerformance() {
        // 캐싱된 서비스 성능 테스트
        userCachedService.addUser(testUser);

        // 첫 번째 호출 - 캐시 미스
        long startTime = System.currentTimeMillis();
        Optional<User> cachedUser = userCachedService.getUserById(1L);
        long endTime = System.currentTimeMillis();

        long firstCallTime = endTime - startTime;
        System.out.println("첫 번째 (캐시 미스) 호출 시간: " + firstCallTime + "ms");

        // 두 번째 호출 - 캐시 히트
        startTime = System.currentTimeMillis();
        cachedUser = userCachedService.getUserById(1L);
        endTime = System.currentTimeMillis();

        long secondCallTime = endTime - startTime;
        System.out.println("두 번째 (캐시 히트) 호출 시간: " + secondCallTime + "ms");

        // 세 번째 호출 - 캐시 히트
        startTime = System.currentTimeMillis();
        cachedUser = userCachedService.getUserById(1L);
        endTime = System.currentTimeMillis();

        long thirdCallTime = endTime - startTime;
        System.out.println("세 번째 (캐시 히트) 호출 시간: " + thirdCallTime + "ms");

        // 네 번째 호출 - 캐시 히트
        startTime = System.currentTimeMillis();
        cachedUser = userCachedService.getUserById(1L);
        endTime = System.currentTimeMillis();

        long fourthCallTime = endTime - startTime;
        System.out.println("네 번째 (캐시 히트) 호출 시간: " + fourthCallTime + "ms");

        // 다섯 번째 호출 - 캐시 히트
        startTime = System.currentTimeMillis();
        cachedUser = userCachedService.getUserById(1L);
        endTime = System.currentTimeMillis();

        long fifthCallTime = endTime - startTime;
        System.out.println("다섯 번째 (캐시 히트) 호출 시간: " + fifthCallTime + "ms");

    }

    @Test
    void testNonCachedServicePerformance() {
        // 일반 서비스 성능 테스트
        userService.addUser(testUser);

        // 첫 번째 호출 - DB 조회
        long startTime = System.currentTimeMillis();
        Optional<User> nonCachedUser = userService.getUserById(1L);
        long endTime = System.currentTimeMillis();

        long firstCallTime = endTime - startTime;
        System.out.println("첫 번째 호출 (DB 조회) 시간: " + firstCallTime + "ms");

        // 두 번째 호출 - 다시 DB 조회
        startTime = System.currentTimeMillis();
        nonCachedUser = userService.getUserById(1L);
        endTime = System.currentTimeMillis();

        long secondCallTime = endTime - startTime;
        System.out.println("두 번째 호출 (다시 DB 조회) 시간: " + secondCallTime + "ms");

        // 세 번째 호출 - 다시 DB 조회
        startTime = System.currentTimeMillis();
        nonCachedUser = userService.getUserById(1L);
        endTime = System.currentTimeMillis();

        long thirdCallTime = endTime - startTime;
        System.out.println("세 번째 호출 (다시 DB 조회) 시간: " + thirdCallTime + "ms");

        // 네 번째 호출 - 다시 DB 조회
        startTime = System.currentTimeMillis();
        nonCachedUser = userService.getUserById(1L);
        endTime = System.currentTimeMillis();

        long fourthCallTime = endTime - startTime;
        System.out.println("네 번째 호출 (다시 DB 조회) 시간: " + fourthCallTime + "ms");

        // 다섯 번째 호출 - 다시 DB 조회
        startTime = System.currentTimeMillis();
        nonCachedUser = userService.getUserById(1L);
        endTime = System.currentTimeMillis();

        long fifthCallTime = endTime - startTime;
        System.out.println("다섯 번째 호출 (다시 DB 조회) 시간: " + fifthCallTime + "ms");
    }
}
