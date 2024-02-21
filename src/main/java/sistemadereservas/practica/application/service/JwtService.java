package sistemadereservas.practica.application.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sistemadereservas.practica.domain.entity.User;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;


//aca va la logica de manipulacion del token
//se mapea lo que esta en application.ylm con @Value org
@Service
public record JwtService(
        @Value("${application.security.jwt.secret-key}")
        String secretKey,
        @Value("${application.security.jwt.expiration}")
        Long jwtExpiration
) {

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

//    private String generateToken(HashMap<String, Object> extraClaims, UserDetails userDetails) {
//        return buildToken(extraClaims, userDetails, jwtExpiration);
//    }
private String generateToken(HashMap<String, Object> extraClaims, UserDetails userDetails) {
        if(userDetails instanceof User){
            extraClaims.put("name", ((User) userDetails).getName());
            extraClaims.put("id", ((User) userDetails).getId());
        }
    return buildToken(extraClaims, userDetails, jwtExpiration);
}
    // despues de crear los metodos para los claims se crean los metodos para crear los tokens
    // este metodo se usa en el proceso de autenticacion
    private String buildToken(
            HashMap<String, Object> extraClaims,
            UserDetails userDetails,
            Long expiration
    ) {
        return Jwts.builder() //builder es para escritura
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) //setSubject saca id de la persona autenticada, del userDetails.
                .setIssuedAt(Date.from(
                        LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()
                        )) //definicion de la fecha de creacion del token
                .setExpiration(Date.from(
                        LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().plusMillis(expiration)
                )) //la misma fecha + los milisegundos de la expiracion
                .signWith(getSignKey(), SignatureAlgorithm.HS256) //firmar el token
                .compact();
    }


    //claim: declaracion de los atributos que se utilizan para autorizar dentro del token

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject); //con el getSubject extraigo el userName
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
        //(claims) -> claims.algo()
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)  //Jws con firma de seguridad
                .getBody();

    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
        //si no ha expirado el token y el userName es el mismo de la autenticacion, el token es valido
        // por ende se carga la autenticacion dentro del contextHolder especificado en JwtFilter
    }

    public boolean isTokenExpired(String token){
        return extracExpiration(token).isBefore(LocalDateTime.now());
    }

    private LocalDateTime extracExpiration(String token) {
        Date date = extractClaim(token, Claims::getExpiration);
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                //toInstant() -> convierte la fecha en un instante en el tiempo
                //atZone(ZoneId.systemDefault() -> para obtener la zona horaria del sistema
                //toLocalDateTime() -> se le da un formato a ese instante en el tiempo
    }

}
