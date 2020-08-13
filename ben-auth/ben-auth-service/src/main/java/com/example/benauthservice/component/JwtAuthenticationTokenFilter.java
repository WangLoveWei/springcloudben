package com.example.benauthservice.component;

import com.example.bencommon.config.properties.JwtProperties;
import com.example.bencommon.utils.JwtTokenUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登录授权过滤器
 *
 * @author lenovo
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    public Logger log = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    private static final String JWT_FILTER_STATUS_VALUE = "jwt_filter_status_true";
    private static final String JWT_FILTER_STATUS_KEY = "jwt_status";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        if (request.getAttribute(JWT_FILTER_STATUS_KEY) != null) {
            chain.doFilter(request, response);
            return;
        }
        String authHeader = request.getHeader(jwtProperties.getHeader());
        if (authHeader != null && authHeader.startsWith(JwtProperties.AUTH_HEADER_PREFIX)) {
            // The part after "Bearer "
            String authToken = authHeader.substring(JwtProperties.AUTH_HEADER_PREFIX.length());
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            log.info("checking username:{}", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
                if (jwtTokenUtil.getUsernameFromToken(authToken).equals(userDetails.getUsername())) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authenticated user:{}", username);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        request.setAttribute(JWT_FILTER_STATUS_KEY,JWT_FILTER_STATUS_VALUE);
        chain.doFilter(request, response);
    }
}
