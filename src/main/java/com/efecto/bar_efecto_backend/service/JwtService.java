package com.efecto.bar_efecto_backend.service;

import com.efecto.bar_efecto_backend.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    // Estas variables almacenan configuraciones del JWT obtenidas desde el archivo application.properties
    @Value("${security.jwt.expiration-minutes}")
    private long EXPIRATION_MINUTES;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    public String generateToken(User user, Map<String, Object> extrClaims) {

        Date issuedAt = new Date(System.currentTimeMillis()); // Establece la fecha y hora actual como momento de emisión del token.
        Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES * 60 * 1000)); //Establece la fecha de expiración en 30 minutos después de la emisión.

        return Jwts.builder()
                .setClaims(extrClaims) // Se añaden claims adicionales como "name" y "role"
                .setSubject(user.getUsername())  //Define el subject del token como el nombre de usuario del usuario autenticado.
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // Configuración del encabezado.
                .signWith(generateKey(), SignatureAlgorithm.HS256) // Firma el token con el generateKey y Algoritmo de firma HMAC SHA256
                .compact(); //Compacta el token en un String JWT final.
    }

    private Key generateKey(){
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY); //  Decodifica la clave secreta SECRET_KEY que está en Base64.
        return Keys.hmacShaKeyFor(keyBytes); //Genera una clave con HMAC SHA-256, que es el algoritmo usado para firmar el token.
    }

    public String getUsernameFromToken(String jwt) { //Este metodo extrae el username (nombre de usuario) contenido dentro del JWT.
        return extraAllClaims(jwt) //Llama a extraAllClaims(jwt), que extrae todos los datos del JWT.
                .getSubject(); //Usa .getSubject(), que devuelve el "sujeto" del token (normalmente el username).
    }

    private Claims extraAllClaims(String jwt) { //Este metodo extrae todas las claims (información) del JWT.
        return Jwts.parser()//Crea un parser para leer y verificar tokens JWT.
                .setSigningKey(generateKey()) //Establece la clave secreta con la que se firmó el JWT.
                .build()//Construye el parser.
                .parseClaimsJws(jwt) //Verifica y decodifica el token. Si el token es válido, devuelve el contenido.
                .getBody(); // Obtiene el cuerpo (payload) del token, que contiene las claims.
    }

}
