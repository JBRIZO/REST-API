package hn.edu.ujcv.PDM_2022_I_EQUIPO3.web

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IDosisBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.DosisPaciente
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils.Constants
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_DOSIS_PACIENTE)
class DosisRestController {
    @Autowired
    val dosisBusiness: IDosisBusiness?=null
    @GetMapping("")
    fun list(): ResponseEntity<List<DosisPaciente>> {
        return try {
            ResponseEntity(dosisBusiness!!.getDosisPaciente(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idDosis:Int):ResponseEntity<Any>{
        return try {
            ResponseEntity(dosisBusiness!!.getDosisPacienteById(idDosis), HttpStatus.OK)
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

    @GetMapping("/idPaciente/{idPaciente}")
    fun loadByIdPaciente(@PathVariable("idPaciente") idPaciente:Int):ResponseEntity<Any>{
        return try {
            ResponseEntity(dosisBusiness!!.getDosisPacienteByPaciente(idPaciente), HttpStatus.OK)
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
    @PostMapping("/addDosisPaciente")
    fun insert(@RequestBody dosisPaciente: DosisPaciente):ResponseEntity<Any>{
        return try {
            dosisBusiness!!.saveDosisPaciente(dosisPaciente)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_DOSIS_PACIENTE + "/" + dosisPaciente.id)
            ResponseEntity(dosisPaciente,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/addDosisPacienteList")
    fun insert(@RequestBody dosisPaciente: List<DosisPaciente>):ResponseEntity<Any>{
        return try {
            ResponseEntity(dosisBusiness!!.saveDosisPaciente(dosisPaciente),HttpStatus.CREATED)
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
    fun update(@RequestBody dosisPaciente: DosisPaciente):ResponseEntity<Any>{
        return try {
            dosisBusiness!!.updateDosisPaciente(dosisPaciente)
            ResponseEntity(dosisPaciente, HttpStatus.OK)
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