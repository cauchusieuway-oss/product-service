package com.vanson.product_service.util;
import io.jsonwebtoken.Jwts;

public class JwtUtil {
    private static final String SECRET = "Vanson@EcommerceSaaS_SUPABASE_PRODUCTION_SECRET_KEY_2026_!9xKqL8mZp";

    public static String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody()
                .get("role", String.class);
    }
}