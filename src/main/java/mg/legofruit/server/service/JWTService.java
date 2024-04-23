package mg.legofruit.server.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JWTService {
    private final String ENCRIPTION_KEY = "0c5678873406ad88616c26feabb00f321be9fcfd1f1fe698bebcd2f69f4c3434";

    public String generate(String email,long expirationTime) {
        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + expirationTime * 60 * 60 * 1000))
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS256, getKey())
                .compact();
    }

    private Key getKey() {
         byte[] decoder = Decoders.BASE64.decode(ENCRIPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }

    public boolean verify(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getKey())
                    .parseClaimsJws(token)
                    .getBody();

            Date expiration = claims.getExpiration();
            return expiration.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String decode(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            return body.getSubject();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
