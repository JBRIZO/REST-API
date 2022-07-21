package hn.edu.ujcv.PDM_2022_I_EQUIPO3.web


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IGravedadBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Gravedad
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
@RequestMapping(Constants.URL_BASE_GRAVEDAD_SINTOMA)
class GravedadRestController {
    @Autowired
    val gravedadBusiness: IGravedadBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Gravedad>> {
        return try {
            ResponseEntity(gravedadBusiness!!.getGravedades(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idGravedad: Int): ResponseEntity<Any> {
        return try {
            ResponseEntity(gravedadBusiness!!.getGravedadById(idGravedad), HttpStatus.OK)
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
    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreGravedad:String):ResponseEntity<Any>{
        return try {
            ResponseEntity(gravedadBusiness!!.getGravedadByNombre(nombreGravedad), HttpStatus.OK)
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
    @PostMapping("/addGravedad")
    fun insert(@RequestBody gravedad: Gravedad):ResponseEntity<Any>{
        return try {
            gravedadBusiness!!.saveGravedad(gravedad)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_GRAVEDAD_SINTOMA + "/" + gravedad.id)
            ResponseEntity(gravedad,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/addGravedades")
    fun insert(@RequestBody gravedad: List<Gravedad>):ResponseEntity<Any>{
        return try {
            ResponseEntity(gravedadBusiness!!.saveGravedades(gravedad),HttpStatus.CREATED)
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
    fun update(@RequestBody gravedad: Gravedad):ResponseEntity<Any>{
        return try {
            gravedadBusiness!!.updateGravedad(gravedad)
            ResponseEntity(gravedad, HttpStatus.OK)
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