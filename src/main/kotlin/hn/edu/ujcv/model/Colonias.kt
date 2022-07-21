package hn.edu.ujcv.PDM_2022_I_EQUIPO3.model

import javax.persistence.*
@Entity
@Table(name = "colonias")
data class Colonias(var nombre:String = "", val activo:Boolean = false){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
}
