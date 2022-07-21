package hn.edu.ujcv.PDM_2022_I_EQUIPO3.model

import javax.persistence.*

@Entity
@Table(name = "estado")
data class Estado(var nombre:String = "", var descripcion:String = ""){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
}
