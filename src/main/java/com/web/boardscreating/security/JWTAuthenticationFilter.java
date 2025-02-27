package com.web.boardscreating.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter { //Выполняется один раз в каждом запросе


    private final UserAuthProvider userAuthProvider;


    //Выполняется при каждом HTTP запросе
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {
            String[] elements = header.split(" ");

            if (elements.length == 2 && "Bearer".equals(elements[0])) { //должен иметь Bearer и правильную длину!
                try {
                    SecurityContextHolder.getContext().setAuthentication(
                            userAuthProvider.validateToken(elements[1])
                    );
                }
                catch (RuntimeException exe) {
                    SecurityContextHolder.clearContext(); // если что-то идет не так, тогда чистим контекст и кидаем ошибку
                    throw exe;
                }
            }
        }
        filterChain.doFilter(request, response); // передача запроса дальше


//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String userEmail;
//
//        if (authHeader == null) {
//            filterChain.doFilter(request, response); //передача запроса дальше
//            return;
//        }
//
//        jwt = authHeader.substring(7);
//        userEmail = jwtService.extractUsername(jwt);
//
//        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//            if (jwtService.isTokenValid(jwt, userDetails)) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);
    }
}
