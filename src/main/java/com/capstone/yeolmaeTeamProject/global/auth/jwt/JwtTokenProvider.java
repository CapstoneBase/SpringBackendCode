package com.capstone.yeolmaeTeamProject.global.auth.jwt;

import com.capstone.yeolmaeTeamProject.domain.login.dto.response.TokenInfo;
import com.capstone.yeolmaeTeamProject.domain.user.domain.Role;
import com.capstone.yeolmaeTeamProject.global.auth.exception.TokenValidateException;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key key;

    private final long accessTokenDuration;

    private final long refreshTokenDuration;

    public JwtTokenProvider(
            @Value("${security.jwt.secret-key}") String secretKey,
            @Value("${security.jwt.token-validity-in-seconds.access-token}") long accessTokenDuration,
            @Value("${security.jwt.token-validity-in-seconds.refresh-token}") long refreshTokenDuration
    ) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.accessTokenDuration = accessTokenDuration;
        this.refreshTokenDuration = refreshTokenDuration;
    }

    public TokenInfo generateToken(String id, Role role) {
        Date expiry = new Date();
        Date accessTokenExpiry = new Date(expiry.getTime() + (accessTokenDuration));
        String accessToken = Jwts.builder()
                .setSubject(id)
                .claim("role", role)
                .setIssuedAt(expiry)
                .setExpiration(accessTokenExpiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        Date refreshTokenExpiry = new Date(expiry.getTime() + (refreshTokenDuration));
        String refreshToken = Jwts.builder()
                .setSubject(id)
                .claim("role", role)
                .setIssuedAt(expiry)
                .setExpiration(refreshTokenExpiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return TokenInfo.create(accessToken, refreshToken);
    }

    public TokenInfo reissue(String token) {
        if (!isRefreshToken(token)) {
            throw new TokenValidateException("refresh 토큰이 아닙니다.");
        }
        Claims claims = parseClaims(token);
        String id = claims.getSubject();
        Role role = Role.valueOf(claims.get("role", String.class));
        return generateToken(claims.getSubject(), role);
    }

    public boolean isRefreshToken(String token) {
        try {
            Claims claims = parseClaims(token);
            Date issuedAt = claims.getIssuedAt();
            Date expiration = claims.getExpiration();
            if (issuedAt != null && expiration != null) {
                long duration = expiration.getTime() - issuedAt.getTime();
                return duration == refreshTokenDuration;
            }
        } catch (Exception e) {
            log.debug("Failed to check if the token is a refresh token", e);
        }
        return false;
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get("role") == null) {
            throw new TokenValidateException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("role").toString().split(","))
                        .map(this::formatRoleString)
                        .map(SimpleGrantedAuthority::new)
                        .toList();

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.");
        }
        return false;
    }

    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    private String formatRoleString(String role) {
        if (!role.startsWith("ROLE_")) {
            return "ROLE_" + role;
        }
        return role;
    }
}
