package hn.edu.ujcv.PDM_2022_I_EQUIPO3.model

import javax.persistence.*

@Entity
@Table(name = "enfermedades_base_paciente")
data class EnfermedadBasePaciente(@Column(name = "id_paciente")val idPaciente : Int = 0,
                                  @Column(name = "id_enfermedad_base")val idEnfermedadBase : Int = 0) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int = 0
}