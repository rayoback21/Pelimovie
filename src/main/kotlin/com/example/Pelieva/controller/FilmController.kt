package com.example.Pelieva.controller

import com.example.Pelieva.entity.Film
import com.example.Pelieva.service.FilmService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/film")
class FilmController {
    @Autowired
    lateinit var filmService: FilmService

    @GetMapping
    fun list(): List<Film> {
        return filmService.list()
    }

    @GetMapping("/{id}")
    fun listById(@PathVariable id: Long): Film {
        return filmService.listById(id)
    }

    @PostMapping
    fun save(@RequestBody film: Film): Film {
        return filmService.save(film)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long?, @RequestBody film: Film?): ResponseEntity<Film> {
        val updatedFilm = filmService.update(film!!)
        return ResponseEntity.ok(updatedFilm)
    }

    @PatchMapping("/{id}")
    fun patch(@PathVariable id: Long?, @RequestBody film: Film?): ResponseEntity<Film> {
        val updatedFilm = filmService.updateName(film!!)
        return ResponseEntity.ok(updatedFilm)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        filmService.delete(id)
    }

}