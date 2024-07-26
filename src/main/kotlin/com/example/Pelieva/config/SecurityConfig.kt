package com.example.Pelieva.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig {
    @Autowired
    private val jwtFilter: JwtFilter? = null

    @Bean
    @Throws(Exception::class)
    open fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .csrf { csrf -> csrf.disable() }
            .cors(Customizer.withDefaults())
            .sessionManagement { sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { authRequest ->
                authRequest
                    .requestMatchers("/auth/").permitAll()
                    .requestMatchers("/client/").hasAnyRole("admin", "ventas")
                    .requestMatchers(HttpMethod.GET, "/film/").hasAnyRole("admin")
                    .requestMatchers(HttpMethod.POST, "/film/").hasAnyRole("admin")
                    .requestMatchers(HttpMethod.PUT, "/film/").hasAnyRole("admin")
                    .requestMatchers(HttpMethod.DELETE, "/film/").hasAnyRole("admin")
                    .requestMatchers(HttpMethod.GET, "/scene/").hasAnyRole("admin")
                    .requestMatchers(HttpMethod.POST, "/scene/").hasAnyRole("admin")
                    .requestMatchers(HttpMethod.PUT, "/scene/").hasAnyRole("admin")
                    .requestMatchers(HttpMethod.DELETE, "/scene/").hasAnyRole("admin")
                    .requestMatchers(HttpMethod.GET, "/characters/").hasAnyRole("admin")
                    .requestMatchers(HttpMethod.POST, "/characters/").hasAnyRole("admin")
                    .requestMatchers(HttpMethod.PUT, "/characters/").hasAnyRole("admin")
                    .requestMatchers(HttpMethod.DELETE, "/characters/").hasAnyRole("admin")
                    .anyRequest().denyAll()
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()


    }

    @Bean
    @Throws(java.lang.Exception::class)
    fun authenticationManager(configuration: AuthenticationConfiguration,
                                    authenticationConfiguration: AuthenticationConfiguration
    ): AuthenticationManager? {
        return configuration.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}