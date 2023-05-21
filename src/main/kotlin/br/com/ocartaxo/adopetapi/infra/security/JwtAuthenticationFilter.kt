package br.com.ocartaxo.adopetapi.infra.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    private val Logger = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val context = SecurityContextHolder.getContext()
        Logger.info("Interceptando requisição para ${request.requestURI}")

        val jwt = extractToken(request)

        if (jwt != null) {

            val username = jwtService.extractUsername(jwt)
            val userDetails = userDetailsService.loadUserByUsername(username)

            Logger.info("Autenticando o usuário ${userDetails.username}")

            val authentication = UsernamePasswordAuthenticationToken(
                userDetails.username,
                null,
                userDetails.authorities
            )

            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            context.authentication =  authentication

        }

        val isAuthenticated = if (context.authentication.isAuthenticated) "SIM" else "NÃO"
        Logger.info("Usuário está autenticado? $isAuthenticated")
        filterChain.doFilter(request, response)
    }

    private fun extractToken(request: HttpServletRequest): String? {
        val header = request.getHeader("Authorization")

        return header?.replace("Bearer ", "")
    }


}