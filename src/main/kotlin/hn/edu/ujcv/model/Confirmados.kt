package hn.edu.ujcv.PDM_2022_I_EQUIPO3.model
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "confirmados")
data class Confirmados(@Column(name = "id_paciente")val idPaciente : Int = 0, @Column(name = "id_laboratorio")val idLaboratorio : Int = 0,
                       @Column(name = "fecha_examen") val fechaExamen: LocalDateTime?=null,
                       @Column(name = "fecha_entrega_resultado")val fechaEntregaResultado: LocalDateTime?=null,
                       @Column(name = "fecha_recuperacion", nullable = true)val fechaRecuperacion: LocalDate? = null,
                       @Column(name = "fecha_deceso", nullable = true)val fechaDeceso: LocalDate? = null,
                       val recuperado: Boolean = false, val fallecido: Boolean = false){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

}
