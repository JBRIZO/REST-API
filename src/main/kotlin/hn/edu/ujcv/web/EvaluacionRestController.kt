package hn.edu.ujcv.PDM_2022_I_EQUIPO3.web


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IEvaluacionBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Evaluacion
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils.Constants
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.URL_BASE_EVALUACION)
class EvaluacionRestController {
    @Autowired
    val evaluacionBusiness: IEvaluacionBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Evaluacion>> {
        return try {
            ResponseEntity(evaluacionBusiness!!.getEvaluaciones(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idEvaluacion: Int): ResponseEntity<Any> {
        return try {
            ResponseEntity(evaluacionBusiness!!.getEvaluacionById(idEvaluacion), HttpStatus.OK)
        }catch (e: BusinessException) {
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: NotFoundException){
            val apiError = RestApiError(
                HttpStatus.NOT_FOUND,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/idUsuario/{idUsuario}")
    fun loadById_usuario(@PathVariable("idUsuario") idUsuario: Int): ResponseEntity<Any> {
        return try {
            ResponseEntity(evaluacionBusiness!!.getEvaluacionByIdUsuario(idUsuario), HttpStatus.OK)
        }catch (e:BusinessException) {
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:NotFoundException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/idPaciente/{idPaciente}")
    fun loadById_paciente(@PathVariable("idPaciente") idPaciente: Int): ResponseEntity<Any> {
        return try {
            ResponseEntity(evaluacionBusiness!!.getEvaluacionByIdUsuario(idPaciente), HttpStatus.OK)
        }catch (e:BusinessException) {
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:NotFoundException){
            val apiError = RestApiError(
                HttpStatus.NOT_FOUND,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/idEstado/{idEstado}")
    fun loadById_estado(@PathVariable("idEstado") idEstado: Int): ResponseEntity<Any> {
        return try {
            ResponseEntity(evaluacionBusiness!!.getEvaluacionByIdEstado(idEstado), HttpStatus.OK)
        }catch (e:BusinessException) {
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:NotFoundException){
            val apiError = RestApiError(
                HttpStatus.NOT_FOUND,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/idCaso/{idCaso}")
    fun loadById_caso(@PathVariable("idCaso") idCaso: Int): ResponseEntity<Any> {
        return try {
            ResponseEntity(evaluacionBusiness!!.getEvaluacionByIdCaso(idCaso), HttpStatus.OK)
        }catch (e:BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:NotFoundException){
            val apiError = RestApiError(
                HttpStatus.NOT_FOUND,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addEvaluacion")
    fun insert(@RequestBody evaluacion: Evaluacion):ResponseEntity<Any>{
        return try {
            evaluacionBusiness!!.saveEvaluacion(evaluacion)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_EVALUACION + "/" + evaluacion.id)
            ResponseEntity(evaluacion,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/addEvaluaciones")
    fun insert(@RequestBody evaluacion: List<Evaluacion>):ResponseEntity<Any>{
        return try {
            ResponseEntity(evaluacionBusiness!!.saveEvaluaciones(evaluacion),HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody evaluacion: Evaluacion):ResponseEntity<Any>{
        return try {
            evaluacionBusiness!!.updateEvaluacion(evaluacion)
            ResponseEntity(evaluacion, HttpStatus.OK)
        }catch (e:BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:NotFoundException){
            val apiError = RestApiError(
                HttpStatus.NOT_FOUND,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.NOT_FOUND)
        }
    }


}