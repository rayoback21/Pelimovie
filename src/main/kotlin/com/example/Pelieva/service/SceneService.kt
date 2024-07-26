package com.example.Pelieva.service


import com.example.Pelieva.entity.Scene
import com.example.Pelieva.entity.SceneView
import com.example.Pelieva.repository.FilmRepository
import com.example.Pelieva.repository.SceneRepository
import com.example.Pelieva.repository.SceneViewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SceneService {
    @Autowired
    private lateinit var filmRepository: FilmRepository

    @Autowired
    lateinit var sceneRepository: SceneRepository

    @Autowired
    lateinit var sceneViewRepository: SceneViewRepository

    fun listWithFilm(): List<SceneView> {
        return sceneViewRepository.findAll()
    }

    fun list(): List<Scene> {
        return sceneRepository.findAll()
    }
    fun save(scene: Scene):Scene {
        val scenes = sceneRepository.findByFilmId(scene.filmId)
        val film = filmRepository.findById(scene.filmId)

        val totalMinutes = scenes.sumByDouble { it.minutes?.toDouble() ?: 0.0 }

        if ((totalMinutes + (scene.minutes?.toDouble() ?: 0.0)) > (film?.duration?.toDouble() ?: 0.0))
            throw Exception("El total de minutos alcanzado")

        return sceneRepository.save(scene)
    }


    fun update(scene: Scene): Scene {
        try {
            sceneRepository.findById(scene.id)
                ?: throw Exception("Ya existe el id")
            return sceneRepository.save(scene)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun updateName(scene: Scene): Scene? {
        try {
            var response = sceneRepository.findById(scene.id)
                ?: throw Exception("Ya existe el id")
            response.apply {
                id= scene.id
            }
            return sceneRepository.save (response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun listById(id: Long):Scene {
        return sceneRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Film with id $id not found") }
    }

    fun delete(id: Long) {
        if (!sceneRepository.existsById(id)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Film with id $id not found")
        }
        filmRepository.deleteById(id)
    }
}