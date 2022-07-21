package hn.edu.ujcv.PDM_2022_I_EQUIPO3.web

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.ICasoBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.TipoCaso
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils.Constants
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_TIPO_CASO)
class TipoCasoRestController {
    @Autowired
    val casoBusiness: ICasoBusiness?=null
    @GetMapping("")
    fun list(): ResponseEntity<List<TipoCaso>> {
        return try {
            ResponseEntity(casoBusiness!!.getTipoCaso(),HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idCaso: Int): ResponseEntity<Any> {
        return try {
            ResponseEntity(casoBusiness!!.getTipoCasoById(idCaso), HttpStatus.OK)
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
    fun loadByNombre(@PathVariable("nombre") tipoCaso:String):ResponseEntity<Any>{
        return try {
            ResponseEntity(casoBusiness!!.getTipoCasoByTipo(tipoCaso), HttpStatus.OK)
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
    @PostMapping("/addTipoCaso")
    fun insert(@RequestBody tipoCaso: TipoCaso):ResponseEntity<Any>{
        return try {
            casoBusiness!!.saveTipoCaso(tipoCaso)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_TIPO_CASO + "/" + tipoCaso.id)
            ResponseEntity(tipoCaso,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/addTiposCaso")
    fun insert(@RequestBody tipoCaso: List<TipoCaso>):ResponseEntity<Any>{
        return try {
            ResponseEntity(casoBusiness!!.saveTipoCaso(tipoCaso),HttpStatus.CREATED)
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
    fun update(@RequestBody tipoCaso: TipoCaso):ResponseEntity<Any>{
        return try {
            casoBusiness!!.updateTipoCaso(tipoCaso)
            ResponseEntity(tipoCaso, HttpStatus.OK)
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