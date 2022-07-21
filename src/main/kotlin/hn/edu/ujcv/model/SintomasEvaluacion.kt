package hn.edu.ujcv.PDM_2022_I_EQUIPO3.model

import javax.persistence.*

@Entity
@Table(name = "sintomas_evaluacion")
data class SintomasEvaluacion(@Column(name = "id_evaluacion")val idEvaluacion : Int = 0,
                              @Column(name = "id_sintoma") val idSintoma : Int = 0) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int = 0
}