package br.com.ocartaxo.kopet.api.infra.security

import br.com.ocartaxo.kopet.api.infra.error.ValidationException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
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

            if (!jwtService.isTokenValid(jwt, userDetails)){
                throw ValidationException("Token inválido")
            }

            val authentication = UsernamePasswordAuthenticationToken(
                userDetails.username,
                null,
                userDetails.authorities
            )

            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
            context.authentication = authentication

        }

        val authentication = context?.authentication
        val isAuthenticated = if (authentication != null && authentication.isAuthenticated) "SIM" else "NÃO"
        logger.info("Usuário está logado? $isAuthenticated")


        filterChain.doFilter(request, response)
    }

    private fun extractToken(request: HttpServletRequest): String? {
        val header = request.getHeader("Authorization")


        return header?.replace("Bearer ", "")
    }

}