package com.example.Pelieva.entity

import jakarta.persistence.*

@Entity
@Table(name = "characters")
class Characters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null
    @Column(name = "full_name")
    var fullname: String? = null

    var description: String? = null
    var cost: Double? = null
    var actor: String? = null
    var stock: String? = null
    var rol: String? = null

    @ManyToOne
    @JoinColumn(name = "scene_id")
    var scene: Scene? = null
}