package hn.edu.ujcv.PDM_2022_I_EQUIPO3.web

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IEnfermedadesBasePacienteBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.EnfermedadBasePaciente
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils.Constants
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_ENFERMEDAD_BASE_PACIENTE)
class EnfermedadBasePacienteRestController {
    @Autowired
    val enfermedadesBasePacienteBusiness: IEnfermedadesBasePacienteBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<EnfermedadBasePaciente>> {
        return try {
            ResponseEntity(enfermedadesBasePacienteBusiness!!.getEnfermedadesBasePaciente(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/idEnfermedadBase/{idEnfermedadBase}")
    fun loadByIdEnfermedadBase(@PathVariable("idEnfermedadBase") idEnfermedadBase:Int): ResponseEntity<Any> {
        return try {
            ResponseEntity(enfermedadesBasePacienteBusiness!!.getByIdEnfermedadBase(idEnfermedadBase), HttpStatus.OK)
        }catch (e: BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: NotFoundException){
            val apiError = RestApiError(
                HttpStatus.NOT_FOUND,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/idPaciente/{idPaciente}")
    fun loadByIdPaciente(@PathVariable("idPaciente") idPaciente:Int): ResponseEntity<Any> {
        return try {
            ResponseEntity(enfermedadesBasePacienteBusiness!!.getByIdPaciente(idPaciente), HttpStatus.OK)
        }catch (e: BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: NotFoundException){
            val apiError = RestApiError(
                HttpStatus.NOT_FOUND,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.NOT_FOUND)
        }
    }
    @PostMapping("/addEnfermedadBasePaciente")
    fun insert(@RequestBody enfermedadBasePaciente: EnfermedadBasePaciente): ResponseEntity<Any> {
        return try {
            enfermedadesBasePacienteBusiness!!.saveEnfermedadBasePaciente(enfermedadBasePaciente)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_ENFERMEDAD_BASE_PACIENTE + "/" + enfermedadBasePaciente.id)
            ResponseEntity(enfermedadBasePaciente,responseHeader, HttpStatus.CREATED)
        }catch (e: BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/addEnfermedadesBasePaciente")
    fun insert(@RequestBody enfermedadesBasePaciente: List<EnfermedadBasePaciente>): ResponseEntity<Any> {
        return try {
            ResponseEntity(enfermedadesBasePacienteBusiness!!.saveEnfermedadBasePaciente(enfermedadesBasePaciente), HttpStatus.CREATED)
        }catch (e: BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}