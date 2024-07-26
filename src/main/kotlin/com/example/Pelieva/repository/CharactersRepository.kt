package com.example.Pelieva.repository

import com.example.Pelieva.entity.Characters
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface CharactersRepository: JpaRepository<Characters, Long> {
    fun findById(id: Long?): Characters?
    @Query("SELECT SUM(s.minutes) FROM Scene s JOIN Characters c ON s.id = c.scene.id WHERE s.filmId = :filmId")
    fun  sumMinutesByFilm(@Param("filmId") filmId: Long): BigDecimal?
}
