package com.pfe.backend.security;

import com.pfe.backend.properties.SecurityProperties;
import com.pfe.backend.utils.BackendUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class OncePerRequestFilterImpl extends OncePerRequestFilter {
    private final SecurityProperties securityProperties;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        authenticate(request);
        filterChain.doFilter(request, response);
    }
    private void authenticate(HttpServletRequest request){
        String token = request.getHeader("Authorization");

        if(BackendUtils.isEmptyOrNull(token)||!token.startsWith(securityProperties.getTokenPrefix())){
            return;
        }
        token = token.substring(securityProperties.getTokenPrefix().length());
        Claims claims = Jwts.parser().setSigningKey(
                securityProperties.getSecret())
                .parseClaimsJws(token).
                getBody();
        if(claims.getExpiration().before(new Date())){
            return;
        }
        String email = claims.getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}