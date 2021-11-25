package com.alkemy.icons.icons.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class JwtUtils {
    
    //Obtener el valor secreto
    private String SECRET_KEY = "secret";
    
    
    /** Verificar si hay informacion de usuario coincidente a traves del token
     * 
     * @param token
     * @return 
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    /** Obtener el token es el tiempo de vencimiento
     * @param token
     */
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    /**
     *  Consulta la informacion de reclamos guardada por el token, sino devuelve nulo
     * @param <T>
     * @param token
     * @param claimsResolver
     * @return 
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
}

public Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody();
}

/**
 * Determina si el token ha expirado
 * @param token
 * @return 
 */
public Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
}
/**
 * Genera informacion de token de usuario
 * @param userDetails
 * @return 
 */
public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, userDetails.getUsername());
}

/**
 * Metodo principal de generar token
 * @param claims
 * @param subject
 * @return 
 */
public String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))//Duracion del token en milisegundos
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
}

/**
 * Verifica que la informacion del usuario de la informacion de la informacion del token coincide
 * @param token
 * @param userDetails
 * @return 
 */
public Boolean validateToken(String token, UserDetails userDetails) {
    final String userName = extractUsername(token);
    return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
}
}
    
    
    

