package ecommerce.project.ecommerce.com.surabhi.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

    private final SecretKey key;

    // Constructor injection of the JWT secret key.
    public JwtProvider(@Value("${jwt.secret}") String secret) {
        // Ensure the secret is at least 32 characters (256 bits)
        if (secret == null || secret.length() < 32) {
            throw new IllegalArgumentException("JWT secret must be at least 32 characters long");
        }
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Generate a JWT token based on the Authentication details.
    public String generateToken(Authentication auth) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 846000000)) // token valid for 10 days
                .claim("email", auth.getName())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract the email claim from the JWT token.
    public String getEmailFromToken(String jwt) {
        try {
            // Remove "Bearer " prefix if present.
            if (jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7);
            }
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
            return claims.get("email", String.class);
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token has expired", e);
        } catch (MalformedJwtException | SignatureException | UnsupportedJwtException e) {
            throw new RuntimeException("Invalid token", e);
        } catch (Exception e) {
            throw new RuntimeException("Token processing error", e);
        }
    }
}
