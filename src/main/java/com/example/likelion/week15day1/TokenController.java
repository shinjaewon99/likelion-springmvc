package com.example.likelion.week15day1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("token")
@RequiredArgsConstructor
public class TokenController {
    private final JwtUtils jwtUtils;
    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;


    // JWT 발급을 받는 Mapping
    // ReqeustBody : 인증 받고자 하는 사용자 ID 비밀번호를 전달한다.
    // ResponseBody : 발급이 완료한 JWT를 전달한다. (JwTokenDto)
    @PostMapping("/issues")
    public JwtTokenDto issueJwt(@RequestBody JwtRequestDto dto) {
        UserDetails userDetails = manager.loadUserByUsername(dto.getUsername());

        // 암호화되지않은 비밀번호와
        // 암호화된 비밀번호를 대조하여 일치하는지
        if(!encoder.matches(
                dto.getPassword(), userDetails.getPassword()
        )) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        JwtTokenDto response = new JwtTokenDto();
        response.setToken(jwtUtils.generateToken(userDetails));

        return response;
    }

}
