package hn.edu.ujcv.PDM_2022_I_EQUIPO3.web


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IUsuarioBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Usuario
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils.Constants
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_USUARIOS)
class UsuarioRestController {
    @Autowired
    val usuarioBusiness: IUsuarioBusiness? = null

    @GetMapping("")
    fun list():ResponseEntity<List<Usuario>>{
        return try {
            ResponseEntity(usuarioBusiness!!.getUsuarios(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id")idUsuario: Int):ResponseEntity<Any>{
        return try {
            ResponseEntity(usuarioBusiness!!.getUsuarioById(idUsuario), HttpStatus.OK)
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
    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre")nombre: String):ResponseEntity<Any>{
        return try {
            ResponseEntity(usuarioBusiness!!.getUsuarioByNombre(nombre), HttpStatus.OK)
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
            ResponseEntity(apiError, HttpStatus.NOT_FOUND)
        }
    }
    @GetMapping("/nombreUsuario/{nombreUsuario}")
    fun loadByNombre_usuario(@PathVariable("nombreUsuario")nombreUsuario: String):ResponseEntity<Any>{
        return try {
            ResponseEntity(usuarioBusiness!!.getUsuarioByNombreUsuario(nombreUsuario), HttpStatus.OK)
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
            ResponseEntity(apiError, HttpStatus.NOT_FOUND)
        }
    }
    @PostMapping("/addUsuario")
    fun insert(@RequestBody usuario: Usuario):ResponseEntity<Any>{
        return try {
            usuarioBusiness!!.saveUsuario(usuario)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_USUARIOS + "/" + usuario.id)
            ResponseEntity(usuario, responseHeader, HttpStatus.CREATED)
        }catch (e:BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PostMapping("/addUsuarios")
    fun insert (@RequestBody usuario: List<Usuario>):ResponseEntity<Any>{
        return try {
            ResponseEntity(usuarioBusiness!!.saveUsuarios(usuario), HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody usuario: Usuario):ResponseEntity<Any>{
        return try {
            usuarioBusiness!!.updateUsuario(usuario)
            ResponseEntity(usuario, HttpStatus.OK)
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
            ResponseEntity(apiError, HttpStatus.NOT_FOUND)
        }
    }

}