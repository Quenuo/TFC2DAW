package com.util;

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
import java.util.Enumeration;
import java.util.List;

//La uso para validar los tokens enviado por los usuarios desde el frontend,tambien para extraer el id del token  , y almacenarlo
//para que mis controladores tenga acceso a ese id
//TODO documentar
@Component
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;



    @Autowired
    public JwtFilter(JwtUtil jwtUtil){
        this.jwtUtil=jwtUtil;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authHeader = httpRequest.getHeader("Authorization");
        String path = httpRequest.getRequestURI();

        //para que permita las solicitudes options
        if (HttpMethod.OPTIONS.matches(httpRequest.getMethod())) {
            chain.doFilter(request, response);
            return;
        }
        if ( path.startsWith("/auth")) {
            chain.doFilter(request, response);
            return;
        }
//        System.out.println(authHeader+" solicitud desde el fornted");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String userId=jwtUtil.validateToken(token);
                httpRequest.setAttribute("userId",userId);
                //con esto establezco una autotenticacion del usuario para spring de forma manual
                //con esto arreglo el problema ya que Spring security no me reconocia el token jwt de autenticacion correctamene pare las solicitudes
                //lo que terminaba en el FilterChainProxy rechazandome las solicitudes
                List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("USER"));//con esto crea jna lista de roles para el usuario y se le asigna user
                //y se le la autorizacion SimpleGrantedAuthority que es solo un permiso para acceder y ya
                //Tengo que añadirle todavia la logica para el admin

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userId, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
//                System.out.println("Error al validar el token de jwt filter " + e.getMessage()+ " "+token);
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                return;
            }
        }else {
                System.out.println("Encabezado Authorization ausente o malformado.");
        }

        chain.doFilter(request, response);
    }


}
