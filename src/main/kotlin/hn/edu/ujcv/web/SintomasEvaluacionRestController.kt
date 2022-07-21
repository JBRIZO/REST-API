package hn.edu.ujcv.PDM_2022_I_EQUIPO3.web

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.ISintomasEvaluacionBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.SintomasEvaluacion
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils.Constants
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_SINTOMAS_EVALUACION)
class SintomasEvaluacionRestController {
    @Autowired
    val sintomasEvaluacionBusiness: ISintomasEvaluacionBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<SintomasEvaluacion>> {
        return try {
            ResponseEntity(sintomasEvaluacionBusiness!!.getSintomasEvaluacion(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idSintomasEvaluacion: Int): ResponseEntity<Any> {
        return try {
            ResponseEntity(sintomasEvaluacionBusiness!!.getSintomasEvaluacionById(idSintomasEvaluacion), HttpStatus.OK)
        }catch (e: BusinessException) {
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
    @GetMapping("/idEvaluacion/{idEvaluacion}")
    fun loadByIdEvaluacion(@PathVariable("idEvaluacion") idEvaluacion:Int): ResponseEntity<Any> {
        return try {
            ResponseEntity(sintomasEvaluacionBusiness!!.getByIdEvaluacion(idEvaluacion), HttpStatus.OK)
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
    @GetMapping("/idSintoma/{idSintoma}")
    fun loadByIdSintoma(@PathVariable("idSintoma") idSintoma:Int): ResponseEntity<Any> {
        return try {
            ResponseEntity(sintomasEvaluacionBusiness!!.getByIdSintoma(idSintoma), HttpStatus.OK)
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
    @PostMapping("/addSintomaEvaluacion")
    fun insert(@RequestBody enfermedadBasePaciente: SintomasEvaluacion): ResponseEntity<Any> {
        return try {
            sintomasEvaluacionBusiness!!.saveSintomasEvaluacion(enfermedadBasePaciente)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_SINTOMAS_EVALUACION + "/" + enfermedadBasePaciente.id)
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
    @PostMapping("/addSintomasEvaluacion")
    fun insert(@RequestBody enfermedadesBasePaciente: List<SintomasEvaluacion>): ResponseEntity<Any> {
        return try {
            ResponseEntity(sintomasEvaluacionBusiness!!.saveSintomasEvaluacion(enfermedadesBasePaciente), HttpStatus.CREATED)
        }catch (e: BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idSintomasEvaluacion: Int): ResponseEntity<Any> {
        return try{
            sintomasEvaluacionBusiness!!.removeSintomasEvaluacion(idSintomasEvaluacion)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            val apiError = RestApiError(
                HttpStatus.NOT_FOUND,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.NOT_FOUND)
        }

    }

}