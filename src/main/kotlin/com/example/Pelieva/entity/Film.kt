package com.example.Pelieva.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "film")
class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null
    var title: String? = null
    var director: String? = null
    var duration: BigDecimal? = null
    @Column(name = "release_date")
    var releaseDate: LocalDate? = null

    var genre: String? = null
    @Column(name = "box_Office")
    var boxOffice: BigDecimal? = null

    var rating: Double? = null
    var summary: String? = null
}