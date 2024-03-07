package com.sparta.admin.jwt;

import com.sparta.admin.entity.security.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j(topic = "JWT 검증 및 인가")
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    // OncePerRequestFilter -> HttpServletRequest / HttpServletResponse 받아 올 수 있음

    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthorizationFilter(JwtUtils jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtils = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Authorization 진입함");

        String tokenValue = jwtUtils.getTokenFromRequest(request);

        if (StringUtils.hasText(tokenValue)) {
            // JWT 토큰 substring
            tokenValue = jwtUtils.substringToken(tokenValue);
            log.info(tokenValue);

            if (!jwtUtils.validateToken(tokenValue)) {
                // =========== 임의로 추가한 코드. 테스트를 잘 해봐야 할 듯 ===============
                // 로그인 / 회원가입은 시큐리티 설정에서 permitAll 해줬다.
                // 그런데, 허용한 URL 이어도 '유효하지 않은 쿠키' OR '만료된 쿠키' 가 들어 있으면, 이쪽을 들어와서 진행이 안된다.
                // 쿠키가 있을 때, 요청 경로가 - /users(회원가입) & /users/login(로그인) 이면 이곳을 지나가게 한다.
                String requestURI = request.getRequestURI();
                if (requestURI.equals("/users") || requestURI.equals("/users/login")) {
                    // 특정 경로에 대한 처리 추가
                    filterChain.doFilter(request, response);
                    return;
                }

                log.error("Token Error");

                // 유효하지 않은 쿠키 삭제를 위한 응답 생성 - 추가함
                ResponseCookie cookie = ResponseCookie.from("Authorization", null)
                        .maxAge(0)
                        .httpOnly(true)
                        .path("/")
                        .build();

                // 쿠키를 응답 헤더에 추가
                response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

                jwtUtils.AuthResultResponseBody(response, HttpServletResponse.SC_UNAUTHORIZED, "토큰이 유효하지 않습니다. 다시 로그인 해주세요.");
                return;
            }

            Claims info = jwtUtils.getUserInfoFromToken(tokenValue);

            try {
                setAuthentication(info.getSubject());
            } catch (Exception e) {
                log.error(e.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    // 인증 처리
    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    // 인증 객체 생성
    private Authentication createAuthentication(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }


}