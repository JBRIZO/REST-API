package hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils

class Constants {
    companion object{
        private const val URL_API_BASE = "/api"
        private const val URL_API_VERSION = "/v1"
        private const val URL_BASE = URL_API_BASE + URL_API_VERSION
        const val URL_BASE_TIPO_CASO = "$URL_BASE/tipo_caso"
        const val URL_BASE_ROL = "$URL_BASE/rol"
        const val URL_BASE_CONFIRMADOS = "$URL_BASE/confirmados"
        const val URL_BASE_TIPO_DOCUMENTO = "$URL_BASE/tipo_documento"
        const val URL_BASE_FABRICANTE_VACUNA = "$URL_BASE/fabricante_vacuna"
        const val URL_BASE_ENFERMEDAD_BASE_PACIENTE = "$URL_BASE/enfermedad_base_paciente"
        const val URL_BASE_DOSIS_PACIENTE = "$URL_BASE/dosis_paciente"
        const val URL_BASE_PACIENTE = "$URL_BASE/paciente"
        const val URL_BASE_ENFERMEDAD_BASE = "$URL_BASE/enfermedad"
        const val URL_BASE_CENTRO_VACUNACION = "$URL_BASE/centro"
        const val URL_BASE_EVALUACION = "$URL_BASE/evaluacion"
        const val URL_BASE_GRAVEDAD_SINTOMA = "$URL_BASE/gravedad"
        const val URL_BASE_USUARIOS = "$URL_BASE/usuario"
        const val URL_BASE_COLONIAS = "$URL_BASE/colonias"
        const val URL_BASE_SINTOMA = "$URL_BASE/sintoma"
        const val URL_BASE_LABORATORIO = "$URL_BASE/laboratorio"
        const val URL_BASE_ESTADO = "$URL_BASE/estado"
        const val URL_BASE_SINTOMAS_EVALUACION= "$URL_BASE/sintomas_evaluacion"



    }
}