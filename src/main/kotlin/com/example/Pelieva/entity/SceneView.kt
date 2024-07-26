package com.example.Pelieva.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "scene")
class SceneView (

    @Id
    @Column(name = "id")
    val sceneId: Long,

    @Column(name = "description")
    val sceneDescription: String,

    @Column(name = "minutes")
    val minutes: BigDecimal,

    @Column(name = "location")
    val location: String,

    @Column(name = "setting")
    val setting: String,

    @Column(name = "film_title")
    val filmTitle: String
    )