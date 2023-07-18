package br.com.ocartaxo.adopetapi.infra.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function

@Service
class JwtService(
    @Value("\${application.security.jwt.secret-key}")
    private val secretKey: String,
    @Value("\${application.security.jwt.expiration}")
    private val jwtExpirationTime: Long,
    @Value("\${application.security.jwt.refresh-token.expiration}")
    private val refreshTokenExpirationTime: Long
) {

    private val signInKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
    private val issuer = "API Adopet"

    fun extractUsername(token: String): String {
        return extractClaims(token, Claims::getSubject)
    }

    fun extractExpirationTime(token: String): Long{
        return extractClaims(token, Claims::getExpiration).time * 1000
    }

    fun <T> extractClaims(
        token: String,
        claimsResolver: Function<Claims, T>
    ): T {

        val claims = extractAllClaims(token)
        return claimsResolver.apply(claims)

    }

    fun generateToken(userDetails: UserDetails) = generateToken(mapOf(), userDetails)

    fun generateToken(
        extraClaims: Map<String, Any>,
        userDetails: UserDetails
    ) = buildToken(extraClaims, userDetails, jwtExpirationTime)

    fun generateRefreshToken(userDetails: UserDetails) = buildToken(mapOf(), userDetails, refreshTokenExpirationTime)

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean{
        val username = extractUsername(token)
        return (username.equals(userDetails.username)) && !isTokenExpired(token)
    }

    private fun buildToken(
        extraClaims: Map<String, Any>,
        userDetails: UserDetails,
        expiration: Long
    ): String {

        return Jwts
            .builder()
            .setIssuer(issuer)
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(signInKey, SignatureAlgorithm.HS256)
            .compact()

    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpirationDate(token).before(Date())
    }

    private fun extractExpirationDate(token: String): Date {
        return extractClaims(token, Claims::getExpiration);
    }


    private fun extractAllClaims(
        token: String
    ): Claims {
        return Jwts
            .parserBuilder()
            .requireIssuer(issuer)
            .setSigningKey(signInKey)
            .build()
            .parseClaimsJws(token)
            .body
    }



}