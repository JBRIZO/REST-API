package hn.edu.ujcv.PDM_2022_I_EQUIPO3.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "centro_vacunacion")//, catalog="dbo"
data class Centro(var nombre:String = "",  var direccion: String  = ""){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
}
