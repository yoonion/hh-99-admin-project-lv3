package com.sparta.admin.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.admin.dto.user.UserLoginRequestDto;
import com.sparta.admin.entity.UserRoleEnum;
import com.sparta.admin.entity.security.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /*
    UsernamePasswordAuthenticationFilter >> 인증 토큰 만드는 기능이 있음
    그대로 쓰게되면 세션 방식으로 되기 때문에, 우리가 기능을 재정의 해줘야 한다.
    JWT 를 쓸 것 이기 때문.
     */

    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
        setFilterProcessesUrl("/users/login"); // 로그인 처리 경로 설정
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("로그인 시도");
        try {
            UserLoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequestDto.class);

            // 인증 처리 (인증 객체 토큰 생성) - UsernamePasswordAuthenticationFilter 상속 받아서 메서드를 사용할 수 있게 된다
            // 인증 처리에서는 권한이 필요 없기 때문에 authorities 를 null 로 넣어준다
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("로그인 성공 및 JWT 생성");
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername(); // AuthenticationManager 가 담아준다
        UserRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole(); // AuthenticationManager 가 담아준다

        String token = jwtUtils.createToken(username, role);
        jwtUtils.addJwtToCookie(token, response);

        // 성공 response 반환
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");

        String successMessage = "{\"message\": \"로그인 인증에 성공했습니다.\"}";
        PrintWriter writer = response.getWriter();
        writer.print(successMessage);
        writer.flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("로그인 실패");

        // 로그인 실패 response 반환
        response.setStatus(401);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");

        String failMessage = "{\"message\": \"로그인 인증에 실패 했습니다. 아이디와 비밀번호를 확인해주세요\"}";
        PrintWriter writer = response.getWriter();
        writer.print(failMessage);
        writer.flush();
    }
}