package hn.edu.ujcv.PDM_2022_I_EQUIPO3.web


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IPacienteBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Paciente
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
import java.time.LocalDate

@RestController
@RequestMapping(Constants.URL_BASE_PACIENTE)
class PacienteRestController {
    @Autowired
    val pacienteBusiness: IPacienteBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Paciente>> {
        return try {
            ResponseEntity(pacienteBusiness!!.getPacientes(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idPaciente: Int): ResponseEntity<Any> {
        return try {
            ResponseEntity(pacienteBusiness!!.getPacienteById(idPaciente), HttpStatus.OK)
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
    fun loadByNombre(@PathVariable("nombre") nombrePaciente:String):ResponseEntity<Any>{
        return try {
            ResponseEntity(pacienteBusiness!!.getPacienteByNombre(nombrePaciente), HttpStatus.OK)
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
    @GetMapping("/idColonia/{idColonia}")
    fun loadById_colonia(@PathVariable("idColonia") idColonia:Int):ResponseEntity<Any>{
        return try {
            ResponseEntity(pacienteBusiness!!.getPacienteByIdColonia(idColonia), HttpStatus.OK)
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
   
    @GetMapping("/telefono/{telefono}")
    fun loadByTelefono(@PathVariable("telefono") telefono:String):ResponseEntity<Any>{
        return try {
            ResponseEntity(pacienteBusiness!!.getPacienteByTelefono(telefono), HttpStatus.OK)
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


    @GetMapping("/documento/{documento}")
    fun loadByDocumento(@PathVariable("documento") documento:String):ResponseEntity<Any>{
        return try {
            ResponseEntity(pacienteBusiness!!.getPacienteByDocumento(documento), HttpStatus.OK)
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

    @GetMapping("/correo/{correo}")
    fun loadByCorreo(@PathVariable("correo") correo:String):ResponseEntity<Any>{
        return try {
            ResponseEntity(pacienteBusiness!!.getPacienteByCorreo(correo), HttpStatus.OK)
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
    @PostMapping("/addPaciente")
    fun insert(@RequestBody paciente: Paciente):ResponseEntity<Any>{
        return try {
            pacienteBusiness!!.savePaciente(paciente)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_PACIENTE + "/" + paciente.id)
            ResponseEntity(paciente,responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/addPacientes")
    fun insert(@RequestBody paciente: List<Paciente>):ResponseEntity<Any>{
        return try {
            ResponseEntity(pacienteBusiness!!.savePacientes(paciente),HttpStatus.CREATED)
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
    fun update(@RequestBody paciente: Paciente):ResponseEntity<Any>{
        return try {
            pacienteBusiness!!.updatePaciente(paciente)
            ResponseEntity(paciente, HttpStatus.OK)
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