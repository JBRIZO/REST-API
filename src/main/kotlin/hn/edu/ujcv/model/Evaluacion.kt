package hn.edu.ujcv.PDM_2022_I_EQUIPO3.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "evaluacion")//, catalog="dbo"
data class Evaluacion(@Column(name = "id_usuario")val idUsuario : Int = 0,
                      @Column(name = "id_paciente") val idPaciente : Int = 0,
                      @Column(name = "id_estado")val idEstado : Int = 0,
                      @Column(name = "id_tipo_caso")val idTipoCaso : Int = 0,
                      @Column(name = "fecha_hora")val fechaHora: LocalDateTime?=null,
                      var comentarios : String = ""){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluacion")
    var id: Int = 0
}
