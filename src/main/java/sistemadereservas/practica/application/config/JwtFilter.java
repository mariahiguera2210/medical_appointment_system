package sistemadereservas.practica.application.config;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sistemadereservas.practica.application.service.JwtService;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final  JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        //validar si el header no tiene valor o no es jwt, si no lo es no hay que validar ningun token
        if(authHeader == null || authHeader.startsWith("Beaber")){
            filterChain.doFilter(request, response);
            return;
        }
        //aca se extrae el jwt
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUserName(jwt);
        // se verifica que llegue el correro y que en el contexto de spring security no este autenticado

        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            //se obtiene el componente del userDetaials a traves del email del usuario

            setAuthenticationToContext(request,jwt, userDetails);
        }
    }

    private void setAuthenticationToContext(HttpServletRequest request, String jwt, UserDetails userDetails) {
        if(jwtService.isTokenValid(jwt, userDetails)){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            //Asignar la informacion que viene en el jwt al security context holder
            //dura lo que dura la peticion, para cada peticion hay que poner el token
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }


}
