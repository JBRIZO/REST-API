package hn.edu.ujcv.PDM_2022_I_EQUIPO3.web

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.ICentroBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Centro
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
@RequestMapping(Constants.URL_BASE_CENTRO_VACUNACION)
class CentroRestController {
    @Autowired
    val centroBusiness: ICentroBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Centro>> {
        return try {
            ResponseEntity(centroBusiness!!.getCentros(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idCentro: Int): ResponseEntity<Any> {
        return try {
            ResponseEntity(centroBusiness!!.getCentroById(idCentro), HttpStatus.OK)
        }catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: NotFoundException){
            val apiError = RestApiError(HttpStatus.NOT_FOUND,
                "Informacion enviada no es valida.",
                e.message.toString())
            ResponseEntity(apiError,HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreCentro:String):ResponseEntity<Any>{
        return try {
            ResponseEntity(centroBusiness!!.getCentroByNombre(nombreCentro), HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:NotFoundException){
            val apiError = RestApiError(HttpStatus.NOT_FOUND,
                "Informacion enviada no es valida.",
                e.message.toString())
            ResponseEntity(apiError,HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addCentro")
    fun insert(@RequestBody centro: Centro):ResponseEntity<Any>{
        return try {
            centroBusiness!!.saveCentro(centro)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_CENTRO_VACUNACION + "/" + centro.id)
            ResponseEntity(centro,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/addCentros")
    fun insert(@RequestBody centro: List<Centro>):ResponseEntity<Any>{
        return try {
            ResponseEntity(centroBusiness!!.saveCentros(centro),HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida.",
                e.message.toString())
            ResponseEntity(apiError,HttpStatus.NOT_FOUND)
        }
    }
    @PutMapping("")
    fun update(@RequestBody centro: Centro):ResponseEntity<Any>{
        return try {
            centroBusiness!!.updateCentro(centro)
            ResponseEntity(centro, HttpStatus.OK)
        }catch (e:BusinessException){
            val apiError = RestApiError(HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida.",
                e.message.toString())
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:NotFoundException){
            val apiError = RestApiError(HttpStatus.NOT_FOUND,
                "Informacion enviada no es valida.",
                e.message.toString())
            ResponseEntity(apiError,HttpStatus.NOT_FOUND)
        }
    }


}