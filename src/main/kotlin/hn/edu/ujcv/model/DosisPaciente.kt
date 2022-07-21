package hn.edu.ujcv.PDM_2022_I_EQUIPO3.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "dosis_paciente")
data class DosisPaciente(@Column(name = "id_centro_vacunacion")val idCentroVacunacion : Int = 0,
                         @Column(name = "id_paciente") val idPaciente : Int = 0,
                         @Column(name = "id_fabricante")val idFabricante : Int = 0,
                         val lote:String, val fecha: LocalDate?=null){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
}
