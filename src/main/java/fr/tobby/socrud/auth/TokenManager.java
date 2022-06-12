package fr.tobby.socrud.auth;

import fr.tobby.socrud.exception.TokenExpiredException;
import fr.tobby.socrud.exception.TokenParsingException;
import fr.tobby.socrud.exception.TokenVerificationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;

@Component
public class TokenManager {

    private final String jwtSecret;
    private final UserDetailsService userDetailsService;

    public TokenManager(@Value("${jwt.secret}") final String jwtSecret,
                        final UserDetailsService userDetailsService)
    {
        this.jwtSecret = jwtSecret;
        this.userDetailsService = userDetailsService;
    }

    public String generateFor(UserDetails userDetails, final long userId)
    {
        return generateFor(userDetails.getUsername(), userId);
    }

    public String generateFor(String username, final long userId)
    {
        return generateFor(username, userId, Instant.now());
    }

    public String generateFor(String username, final long userId, Instant now)
    {
        final HashMap<String, Object> claims = new HashMap<>();

        claims.put("userId", userId);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(Date.from(now.plus(10, ChronoUnit.DAYS)))
                .setIssuedAt(Date.from(now))
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public UserDetails verifyToken(final String tokenString) throws TokenVerificationException
    {
        return verifyToken(tokenString, Instant.now());
    }

    public UserDetails verifyToken(final String tokenString, Instant now) throws TokenVerificationException
    {
        Claims claims;
        try
        {
            claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(tokenString)
                    .getBody();
        }
        catch (Exception e)
        {
            throw new TokenParsingException(e);
        }

        if (claims.getExpiration().before(Date.from(now)))
            throw new TokenExpiredException();
        String username = claims.getSubject();
        return userDetailsService.loadUserByUsername(username);
    }
}
