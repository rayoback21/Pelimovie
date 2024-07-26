package com.example.Pelieva.repository

import com.example.Pelieva.entity.Film
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FilmRepository: JpaRepository<Film, Long> {
    fun  findById(id: Long?): Film
}