package com.example.Pelieva.repository

import com.example.Pelieva.entity.Scene
import com.example.Pelieva.entity.SceneView
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SceneViewRepository: JpaRepository<SceneView, Long> {
    fun findById(id: Long?): Scene?

}