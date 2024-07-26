package com.example.Pelieva.service

import com.example.Pelieva.entity.Film
import com.example.Pelieva.repository.FilmRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class FilmService {
    @Autowired
    lateinit var filmRepository : FilmRepository

    fun list() : List<Film>{
        return filmRepository.findAll()
    }
        fun listById(id: Long): Film {
            return filmRepository.findById(id)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Film with id $id not found") }
        }

    fun save(film: Film): Film {
        return filmRepository.save(film)
    }

    fun update(film: Film): Film {
        try {
            filmRepository.findById(film.id)
                ?:throw Exception ("Ya existe el id")
            return filmRepository.save(film)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun updateName(film: Film): Film? {
        try {
            var response = filmRepository.findById(film.id)
                ?: throw Exception("Ya existe el id")
            response.apply {
                id= film.id
            }
            return filmRepository.save (response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun delete(id: Long) {
        if (!filmRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Film with id $id not found")
        }
        filmRepository.deleteById(id)
    }
}