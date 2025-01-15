package com.util;

import com.model.User;
import com.services.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpMethod;


import java.io.IOException;
import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

//La uso para validar los tokens enviado por los usuarios desde el frontend,tambien para extraer el id del token  , y almacenarlo
//para que mis controladores tenga acceso a ese id
//TODO documentar
@Component
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public JwtFilter(JwtUtil jwtUtil, UserService userService){
        this.jwtUtil=jwtUtil;
        this.userService=userService;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authHeader = httpRequest.getHeader("Authorization");
        if (HttpMethod.OPTIONS.matches(httpRequest.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        if (httpRequest.getRequestURI().startsWith("/images/")) {
            chain.doFilter(request, response);
            return;
        }


        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String userId=jwtUtil.validateToken(token);
                httpRequest.setAttribute("userId",userId);
                User user=userService.getUserById(Long.valueOf(userId));
                if(user!=null){
                    //con esto establezco una autotenticacion del usuario para spring de forma manual
                    //con esto arreglo el problema ya que Spring security no me reconocia el token jwt de autenticacion correctamene pare las solicitudes
                    //lo que terminaba en el FilterChainProxy rechazandome las solicitudes
                    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRol().toUpperCase()));
                    //y se le la autorizacion SimpleGrantedAuthority que es solo un permiso para acceder y ya
                    //Tengo que añadirle todavia la logica para el admin

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userId, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            } catch (Exception e) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                return;
            }
        }else {
                System.out.println("Encabezado Authorization ausente o malformado.");
        }

        chain.doFilter(request, response);
    }


}
