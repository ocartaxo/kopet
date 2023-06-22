package br.com.ocartaxo.adopetapi.infra.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@Slf4j
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        logger.info("Interceptando requisição para ${request.requestURI}")

        val context = SecurityContextHolder.getContext()
        val jwt = extractToken(request)

        if (jwt != null) {

            val username = jwtService.extractUsername(jwt)
            val userDetails = userDetailsService.loadUserByUsername(username)

            logger.info("Autenticando o usuário ${userDetails.username}")

            val authentication = UsernamePasswordAuthenticationToken(
                userDetails.username,
                null,
                userDetails.authorities
            )

            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            context.authentication = authentication

        }

        val isAuthenticated = if (context.authentication.isAuthenticated) "SIM" else "NÃO"
        logger.info("Usuário está autenticado? $isAuthenticated")
        filterChain.doFilter(request, response)
    }

    private fun extractToken(request: HttpServletRequest): String? {
        val header = request.getHeader("Authorization")

        return header?.replace("Bearer ", "")
    }


}