package com.alkemy.icons.icons.auth.filter;

import com.alkemy.icons.icons.auth.service.JwtUtils;
import com.alkemy.icons.icons.auth.service.UserDetailsCustomService;


import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

//Se ejecuta el filtro cada vez que llegue un request
@Component
public class JwtRequestFilter extends OncePerRequestFilter{
    
    @Autowired
    private UserDetailsCustomService userDetailsCustomService;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    /**
           * Este método realiza el procesamiento de filtrado de información del encabezado de la solicitud
           * Si el token en el encabezado de la solicitud no es nulo, vaya a la verificación jwt
           * De lo contrario, déjelo ir directamente
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        final String authorizationHeader = request.getHeader("Authorization");
        
        String username = null;
        String jwt = null;                                       //Bearer tipo de Token
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7); //Se sacan los primeros 7 caracteres
            username = jwtUtils.extractUsername(jwt); // Analiza token para obtener el nombre de usuario
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsCustomService.loadUserByUsername(username);
            
                        // Verifica si la información del usuario coincide con el token
            if(jwtUtils.validateToken(jwt, userDetails)){
                // Genere proactivamente el token de autenticación de seguridad después de que se pase la verificación
                UsernamePasswordAuthenticationToken authReq =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
                Authentication auth = authenticationManager.authenticate(authReq);
                
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}
