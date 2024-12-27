package com.util;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
//La uso para validar los tokens enviado por los usuarios desde el frontend,tambien para extraer el id del token  , y almacenarlo
//para que mis controladores tenga acceso a ese id
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
//                System.out.println("Token válido. UserId extraído: " + userId+" token "+authHeader);
            } catch (Exception e) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
//                System.out.println("Error al validar el token de jwt filter " + e.getMessage()+ " "+token);
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                return;
            }
        }

        chain.doFilter(request, response);
    }


}
