package com.example.Pelieva.repository

import com.example.Pelieva.entity.Characters
import com.example.Pelieva.entity.CharactersView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CharactersViewRepository: JpaRepository<CharactersView, Long> {
    fun findById(id: Long?): Characters?
}