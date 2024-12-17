package ru.leonid.labs.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "e04eda5acc4666d7ac8d8ea3a7d0d06627392e8a77032ef234b0427ac72d7671167797b0207f80502bccc5d5e08bdd5fbf8ee33643472798d3051a64ecd2dfff9f82781cba0343053b967288c2387ca6fb1b341e9756ffdc80f068be2500e18e94b669bc63c24d76c685c9a60b1e1789651387d359408f4cdc10e54442d9e6ae95d7e4fb6188c3044d46aaf53a8efba39658ce15bfc5788c32df76e28903dadbbdf6444a375414725486ce30ef7718cf23d3cf04a3d281ccc9023a7074fefe6c9e1a3c9abbad13ebe548ee28180cb7d73480e480c23940e5837e78fbe8bbed99ae19c72d11f90c3fdead9b86f943c5e010c1d2aab0267ba32dd3bfb7fd5e1cca2df7aaaaaaa203e1403f5540795d5178a7c3dd003033489228eb26ad8902e3074fccfd8a1aba2d216c27e51b741f49638c763a496bdf7ea534a48b5d885c3a9b191f853205ed36559d585c16d74cc65e396d8b7c8027d70df113aba1e709f79508401923dbe55ebfe1f799dd96acb30fda688e8278e47330c346d80b95abb6478ab30e999754d9fd974dac7e401d9924f567f9bf96a9ffec2ddd2513cc52f83b97accec3e2e72fd0401d7687076d5efce5d334bf1ca2d55ec15efd2e8e91b2524a8b89baf1e0e20549007d352f885f4dfa0f71bbfb3dfcea2363ebde145d6a1ba10f71970bfcc8ca1c2e1655cfff5c2e9fac5ae4f9d9845acc8b42bd496c5be4123";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 час
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token, String username) {
        try {
            return (extractUsername(token).equals(username) && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}