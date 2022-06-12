package fr.tobby.socrud.auth;

import fr.tobby.socrud.exception.TokenExpiredException;
import fr.tobby.socrud.exception.TokenParsingException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TokenManagerTest {

    public static final String JWT_SECRET = "socrudjwt";
    private static TokenManager tokenManager;
    private static UserDetailsService userDetailsService;

    @BeforeAll
    static void beforeAll()
    {
        userDetailsService = mock(UserDetailsService.class);
        tokenManager = new TokenManager(JWT_SECRET, userDetailsService);
    }

    @AfterEach
    void tearDown()
    {
        reset(userDetailsService);
    }

    private boolean areInstantsSimilar(Instant a, Instant b)
    {
        long diff = a.getEpochSecond() - b.getEpochSecond();
        return diff >= -1 && diff <= 1;
    }

    @Test
    void validJwtGenerationTest()
    {
        Instant now = Instant.now();
        String jwt = tokenManager.generateFor("Joe", 42, now);

        // Reparse jwt
        Claims claims = Jwts.parser()
                            .setSigningKey(JWT_SECRET)
                            .parseClaimsJws(jwt)
                            .getBody();

        // Check content
        assertTrue(areInstantsSimilar(now.plus(10, ChronoUnit.DAYS), claims.getExpiration().toInstant()));
        assertEquals("Joe", claims.getSubject());
        assertTrue(areInstantsSimilar(now, claims.getIssuedAt().toInstant()));
    }

    @Test
    void verifyValidTokenTest()
    {
        Instant now = Instant.now();
        String jwt = tokenManager.generateFor("Joe", 42, now);
        when(userDetailsService.loadUserByUsername("Joe")).thenReturn(new UserDetailsImpl(42, "Joe", "root"));

        UserDetails userDetails = tokenManager.verifyToken(jwt, now);

        assertEquals("Joe", userDetails.getUsername());
        assertEquals("root", userDetails.getPassword());
    }

    @Test
    void verifyInvalidTokenTest()
    {
        Instant now = Instant.now();

        assertThrows(TokenParsingException.class, () -> tokenManager.verifyToken("Hello world!", now));
    }

    @Test
    void verifyExpiredTokenTest()
    {
        Instant now = Instant.now();
        String jwt = tokenManager.generateFor("Joe", 42, now);
        when(userDetailsService.loadUserByUsername("Joe")).thenReturn(new UserDetailsImpl(42, "Joe", "root"));

        Instant then = now.plus(12, ChronoUnit.DAYS);

        assertThrows(TokenExpiredException.class, () -> tokenManager.verifyToken(jwt, then));
    }
}