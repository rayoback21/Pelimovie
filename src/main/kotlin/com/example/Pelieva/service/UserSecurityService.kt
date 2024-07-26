package com.example.Pelieva.service

import com.example.Pelieva.dto.registerDto
import com.example.Pelieva.entity.UserEntity
import com.example.Pelieva.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Service
class UserSecurityService: UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository
    @Override
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val userEntity = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User not found: $username")
        val roles: Array<String?> = userEntity.roles?.map {
                role -> role.role }!!.toTypedArray()

        return User.builder()
            .username(userEntity.username)
            .password(userEntity.password)
            .roles(*roles)
            .accountLocked(userEntity.locked!!)
            .disabled(userEntity.disabled!!)
            .build()
    }

    fun register(registerDto: registerDto):UserEntity{
        val newUserEntity = UserEntity()
        newUserEntity.apply {
            username = registerDto.username
            password = BCryptPasswordEncoder().encode(registerDto.password)
            email = registerDto.email
            locked = false
            disabled = false
        }
        userRepository.save(newUserEntity)

        return newUserEntity
    }

}