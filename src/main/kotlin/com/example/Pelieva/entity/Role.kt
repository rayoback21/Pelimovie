package com.example.Pelieva.entity

import jakarta.persistence.*

@Entity
@Table(name = "roles")
class Role {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var role: String? = null
    @Column(name = "user_id")
    var userId: Long? = null
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable=false, updatable=false)
    var userEntity:UserEntity? = null
}