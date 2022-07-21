package hn.edu.ujcv.PDM_2022_I_EQUIPO3.model

import javax.persistence.*

@Entity
@Table(name = "sintoma")
data class Sintoma(@Column(name = "gravedad_id")val gravedad : Int = 0,
                   var nombre:String = ""){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
}

