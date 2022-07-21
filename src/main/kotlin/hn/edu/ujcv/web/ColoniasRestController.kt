package hn.edu.ujcv.PDM_2022_I_EQUIPO3.web


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IColoniaBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Colonias
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils.Constants
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_COLONIAS)
class ColoniasRestController {
    @Autowired
    val coloniasBusiness: IColoniaBusiness? = null

    @GetMapping("")
    fun list():ResponseEntity<List<Colonias>>{
        return try {
            ResponseEntity(coloniasBusiness!!.getColonia(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id")idColonias: Int):ResponseEntity<Any>{
        return try {
            ResponseEntity(coloniasBusiness!!.getColoniaById(idColonias), HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e: NotFoundException){
            val apiError = RestApiError(
                HttpStatus.NOT_FOUND,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre")nombreColonias: String):ResponseEntity<Any>{
        return try {
            ResponseEntity(coloniasBusiness!!.getColoniaByNombre(nombreColonias), HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:NotFoundException){
            val apiError = RestApiError(
                HttpStatus.NOT_FOUND,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/addColonia")
    fun insert(@RequestBody colonias: Colonias):ResponseEntity<Any>{
        return try {
            coloniasBusiness!!.saveColonia(colonias)
            val responseHeader = HttpHeaders()
            responseHeader.set("/", Constants.URL_BASE_COLONIAS + "/" + colonias.id)
            ResponseEntity(colonias, responseHeader, HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/addColonias")
    fun insert (@RequestBody colonias: List<Colonias>):ResponseEntity<Any>{
        return try {
            ResponseEntity(coloniasBusiness!!.saveColonias(colonias), HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody colonias:Colonias):ResponseEntity<Any>{
        return try {
            coloniasBusiness!!.updateColonia(colonias)
            ResponseEntity(colonias, HttpStatus.OK)
        }catch (e:BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:NotFoundException){
            val apiError = RestApiError(
                HttpStatus.NOT_FOUND,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}