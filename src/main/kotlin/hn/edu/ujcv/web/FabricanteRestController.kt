package hn.edu.ujcv.PDM_2022_I_EQUIPO3.web

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IFabricanteBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.FabricanteVacuna
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils.Constants
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_FABRICANTE_VACUNA)
class FabricanteRestController {

    @Autowired
    val fabricanteBusiness: IFabricanteBusiness?=null
    @GetMapping("")
    fun list(): ResponseEntity<List<FabricanteVacuna>> {
        return try {
            ResponseEntity(fabricanteBusiness!!.getFabricanteVacuna(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idFabricanteVacuna: Int): ResponseEntity<Any> {
        return try {
            ResponseEntity(fabricanteBusiness!!.getFabricanteVacunaById(idFabricanteVacuna), HttpStatus.OK)
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
    fun loadByNombre(@PathVariable("nombre") nombreFabricanteVacuna:String):ResponseEntity<Any>{
        return try {
            ResponseEntity(fabricanteBusiness!!.getFabricanteVacunaByNombre(nombreFabricanteVacuna), HttpStatus.OK)
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
    @PostMapping("/addFabricanteVacuna")
    fun insert(@RequestBody fabricanteVacuna: FabricanteVacuna):ResponseEntity<Any>{
        return try {
            fabricanteBusiness!!.saveFabricanteVacuna(fabricanteVacuna)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_FABRICANTE_VACUNA + "/" + fabricanteVacuna.id)
            ResponseEntity(fabricanteVacuna,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/addFabricantesVacuna")
    fun insert(@RequestBody fabricanteVacuna:  List<FabricanteVacuna>):ResponseEntity<Any>{
        return try {
            ResponseEntity(fabricanteBusiness!!.saveFabricanteVacuna(fabricanteVacuna),HttpStatus.CREATED)
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
    fun update(@RequestBody fabricanteVacuna: FabricanteVacuna):ResponseEntity<Any>{
        return try {
            fabricanteBusiness!!.updateFabricanteVacuna(fabricanteVacuna)
            ResponseEntity(fabricanteVacuna, HttpStatus.OK)
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