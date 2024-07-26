package com.example.Pelieva.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "characters_with_scene")
class CharactersView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null

    var description: String? = null
    var cost: BigDecimal? = null

    @Column(name = "name_actor")
    var nameActor: String? = null
    var role: String? = null
    var importance: String? = null
    @Column(name= "scene_description")
    var sceneDescription: String? = null
}