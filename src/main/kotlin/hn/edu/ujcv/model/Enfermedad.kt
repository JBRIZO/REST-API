package hn.edu.ujcv.PDM_2022_I_EQUIPO3.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "enfermedad_base")//, catalog="dbo"
data class Enfermedad(var nombre : String = "", val activo : Boolean = false){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
}
