package com.ecommerce2.utils;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecommerce2.dto.ValidateTokenResponse;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

                String authorizationHeader = request.getHeader("Authorization");
                String token = null;
                String userName = null;

                if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
                    token = authorizationHeader.substring(7);
                    try {
                        userName = jwtUtil.extractUsername(token);
                    } catch (ExpiredJwtException ex) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Jwt Token Expired");
                        return;
                    } catch (Exception ex) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
                        return;
                    }
                }

                if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                    ValidateTokenResponse validateTokenResponse = jwtUtil.validateToken(token);
                    if (validateTokenResponse.isValid()) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }

                chain.doFilter(request, response);
    }

}
