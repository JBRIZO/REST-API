package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IUsuarioBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.UsuarioRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.time.LocalDate
import java.util.Optional
import kotlin.text.Charsets.UTF_8

@Service
class UsuarioBusiness: IUsuarioBusiness {

    @Autowired
    val usuarioRepository:UsuarioRepository? = null

    @Throws(BusinessException::class)
    override fun getUsuarios(): List<Usuario> {
        try {
            return usuarioRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getUsuarioById(idUsuario: Int): Usuario {
        val opt:Optional<Usuario>
        try {
            opt=usuarioRepository!!.findById(idUsuario)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw BusinessException("No se encontro el Usuario $idUsuario")
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveUsuario(usuario: Usuario): Usuario {
        try {
            //nombre
            usuario.nombre = usuario.nombre.trimEnd().trimStart().lowercase()
            if (usuario.nombre.isEmpty())
                throw BusinessException("El nombre del paciente no puede estar vacio")
            if (usuario.nombre.trimStart().length < 2)
                throw BusinessException("Ingrese más de 2 caracteres en el nombre")
            if (usuario.nombre.trimStart().length >50)
                throw BusinessException("El nombre no puede contener tantos caracteres.")
            if (Regex("(.)\\1{2,}").find(usuario.nombre.trimStart()) != null)
                throw BusinessException("El nombre no puede contener tantas letras repetidas.")
            if (Regex("[^A-Za-zÁÉÍÓÚáéíóúñ\\s]").find(usuario.nombre.trimStart()) != null)
                throw BusinessException("El nombre solo puede contener letras.")
            //cada nombre en mayuscula
            var nombre = ""
            for (string in usuario.nombre.split(Regex("\\s"))){
                var nombreActual = ""
                nombreActual = string.replaceFirstChar { it.uppercase() }
                nombre += "$nombreActual "
            }
            usuario.nombre = nombre.trimEnd()
            //apellido
            usuario.apellido = usuario.apellido.trimEnd().trimStart().lowercase()
            if (usuario.apellido.isEmpty())
                throw BusinessException("El apellido del paciente no puede estar vacio")
            if (usuario.apellido.trimStart().length < 2)
                throw BusinessException("Ingrese más de 2 caracteres en el apellido")
            if (usuario.apellido.trimStart().length >50)
                throw BusinessException("El apellido no puede contener tantos caracteres.")
            if (Regex("[^A-Za-zÁÉÍÓÚáéíóúñ\\s]").find(usuario.apellido.trimStart()) != null)
                throw BusinessException("El apellido solo puede contener letras.")
            if (Regex("(.)\\1{2,}").find(usuario.apellido.trimStart()) != null)
                throw BusinessException("El apellido no puede contener tantas letras repetidas.")
            //cada nombre en mayuscula
            var apellido = ""
            for (string in usuario.apellido.split(Regex("\\s"))){
                var apellidoActual = ""
                apellidoActual = string.replaceFirstChar { it.uppercase() }
                apellido += "$apellidoActual "
            }
            usuario.apellido = apellido.trimEnd()
            //correo
            if (usuario.correo.isEmpty())
                throw BusinessException("El correo del paciente no puede estar vacio")
            if (usuario.correo.trimStart().length < 6)
                throw BusinessException("Ingrese más de 6 caracteres en el correo")
            if (usuario.correo.trimStart().length > 25)
                throw BusinessException("El correo no puede contener tantos caracteres.")
            if (!usuario.correo.matches(Regex("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})\$")))
                throw BusinessException("Ese es un correo invalido.")
            //direccion
            usuario.direccion = usuario.direccion.trimStart().trimEnd().lowercase()
            if (usuario.direccion.trimStart().isEmpty())
                throw BusinessException("La direccion del paciente no puede estar vacio.")
            if (usuario.direccion.trimStart().length < 10 )
                throw BusinessException("Ingrese más de 10 caracteres en la direccion.")
            if (usuario.direccion.trimStart().length > 150 )
                throw BusinessException("La direccion del paciente no puede tener mas de 150 caracteres.")
            if (usuario.direccion.matches(Regex("^[\\d ]*\$")))
                throw BusinessException("La direccion del paciente no puede contener solo numeros.")
            if (Regex("(.)\\1{2,}").find(usuario.direccion.trimStart()) != null)
                throw BusinessException("La direccion del paciente no puede contener tantos caracteres repetidos")
            usuario.direccion = usuario.direccion.replaceFirstChar { it.lowercase() }
            //telefono
            if (usuario.telefono.trimStart().isEmpty())
                throw BusinessException("El telefono del paciente no puede estar vacio")
            if (usuario.telefono.trimStart().length < 8)
                throw BusinessException("Ingrese más de 8 caracteres en el telefno")
            if (usuario.telefono.trimStart().length > 12)
                throw BusinessException("El telefono no puede contener tantos caracteres.")
            if (!usuario.telefono.matches(Regex("^[2398]\\d*\$")))
                throw BusinessException("Ese no es un numero de telefono valido.")
            if (Regex("(\\d)\\1{3,}").find(usuario.telefono.trimStart()) != null)
                throw BusinessException("No puedes repetir el mismo numero tantas veces en el telefono.")
            //fecha de nacimiento
            if (usuario.fechaNacimiento!!.isAfter(LocalDate.now()))
                throw BusinessException("Ingresa una fecha de nacimiento valida.")
            if (usuario.fechaNacimiento!!.isAfter(LocalDate.now().minusYears(18)))
                throw BusinessException("El usuario debe ser mayor de 18 años")
            //documento
            if (usuario.documento.trimStart().isEmpty())
                throw BusinessException("El documento del paciente no puede estar vacio")
            if (usuario.documento.trimStart().length < 6)
                throw BusinessException("Ingrese más de 6 caracteres en el documento")
            if (usuario.documento.trimStart().length > 25)
                throw BusinessException("El documento no puede contener tantos caracteres.")
            //nombre de usuario
            if (usuario.nombreUsuario.trimStart().length < 8)
                throw BusinessException("EL nombre de usuario debe contener minimo 8 caracteres.")
            if (usuario.nombreUsuario.trimStart().length > 20)
                throw BusinessException("El nombre de usuario no debe contener mas de 20 caracteres")
            if (!usuario.nombreUsuario.matches(Regex("^(?=.{8,20}\$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])\$")))
                throw BusinessException("El nombre de usuario proporcionado no es valido.")
            //contrasena
            if (usuario.contrasena.trimStart().length < 8)
                throw BusinessException("La contrasena debe tener al menos 8 caracteres.")
            if (usuario.contrasena.trimStart().isEmpty())
                throw BusinessException("Debes especificar una contrasena para este usuario.")
            if (!usuario.contrasena.matches(Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}\$")))
                throw BusinessException("La contrasena debe tener una letra minuscula, una mayuscula, un caracter especial y un numero para ser valida.")
            usuario.contrasena = String(sha256(usuario.contrasena))     //encriptar
            //fecha cambio contrasena
            if (usuario.fechaCambioContrasena!!.isAfter(LocalDate.now()))
                throw BusinessException("La fecha de cambio de contrasena no puede ocurrir despues de esta.")

            return usuarioRepository!!.save(usuario)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class)
    override fun saveUsuarios(usuarios: List<Usuario>): List<Usuario> {
        try {
            for (usuario in usuarios){
                //nombre
                usuario.nombre = usuario.nombre.trimEnd().trimStart().lowercase()
                if (usuario.nombre.isEmpty())
                    throw BusinessException("El nombre del paciente no puede estar vacio")
                if (usuario.nombre.trimStart().length < 2)
                    throw BusinessException("Ingrese más de 2 caracteres en el nombre")
                if (usuario.nombre.trimStart().length >50)
                    throw BusinessException("El nombre no puede contener tantos caracteres.")
                if (Regex("(.)\\1{2,}").find(usuario.nombre.trimStart()) != null)
                    throw BusinessException("El nombre no puede contener tantas letras repetidas.")
                if (Regex("[^A-Za-zÁÉÍÓÚáéíóúñ\\s]").find(usuario.nombre.trimStart()) != null)
                    throw BusinessException("El nombre solo puede contener letras.")
                //cada nombre en mayuscula
                var nombre = ""
                for (string in usuario.nombre.split(Regex("\\s"))){
                    var nombreActual = ""
                    nombreActual = string.replaceFirstChar { it.uppercase() }
                    nombre += "$nombreActual "
                }
                usuario.nombre = nombre.trimEnd()
                //apellido
                usuario.apellido = usuario.apellido.trimEnd().trimStart().lowercase()
                if (usuario.apellido.isEmpty())
                    throw BusinessException("El apellido del paciente no puede estar vacio")
                if (usuario.apellido.trimStart().length < 2)
                    throw BusinessException("Ingrese más de 2 caracteres en el apellido")
                if (usuario.apellido.trimStart().length >50)
                    throw BusinessException("El apellido no puede contener tantos caracteres.")
                if (Regex("[^A-Za-zÁÉÍÓÚáéíóúñ\\s]").find(usuario.apellido.trimStart()) != null)
                    throw BusinessException("El apellido solo puede contener letras.")
                if (Regex("(.)\\1{2,}").find(usuario.apellido.trimStart()) != null)
                    throw BusinessException("El apellido no puede contener tantas letras repetidas.")
                //cada nombre en mayuscula
                var apellido = ""
                for (string in usuario.apellido.split(Regex("\\s"))){
                    var apellidoActual = ""
                    apellidoActual = string.replaceFirstChar { it.uppercase() }
                    apellido += "$apellidoActual "
                }
                usuario.apellido = apellido.trimEnd()
                //correo
                if (usuario.correo.isEmpty())
                    throw BusinessException("El correo del paciente no puede estar vacio")
                if (usuario.correo.trimStart().length < 6)
                    throw BusinessException("Ingrese más de 6 caracteres en el correo")
                if (usuario.correo.trimStart().length > 25)
                    throw BusinessException("El correo no puede contener tantos caracteres.")
                if (!usuario.correo.matches(Regex("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})\$")))
                    throw BusinessException("Ese es un correo invalido.")
                //direccion
                usuario.direccion = usuario.direccion.trimStart().trimEnd().lowercase()
                if (usuario.direccion.trimStart().isEmpty())
                    throw BusinessException("La direccion del paciente no puede estar vacio.")
                if (usuario.direccion.trimStart().length < 10 )
                    throw BusinessException("Ingrese más de 10 caracteres en la direccion.")
                if (usuario.direccion.trimStart().length > 150 )
                    throw BusinessException("La direccion del paciente no puede tener mas de 150 caracteres.")
                if (usuario.direccion.matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("La direccion del paciente no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(usuario.direccion.trimStart()) != null)
                    throw BusinessException("La direccion del paciente no puede contener tantos caracteres repetidos")
                usuario.direccion = usuario.direccion.replaceFirstChar { it.lowercase() }
                //telefono
                if (usuario.telefono.trimStart().isEmpty())
                    throw BusinessException("El telefono del paciente no puede estar vacio")
                if (usuario.telefono.trimStart().length < 8)
                    throw BusinessException("Ingrese más de 8 caracteres en el telefno")
                if (usuario.telefono.trimStart().length > 12)
                    throw BusinessException("El telefono no puede contener tantos caracteres.")
                if (!usuario.telefono.matches(Regex("^[2398]\\d*\$")))
                    throw BusinessException("Ese no es un numero de telefono valido.")
                if (Regex("(\\d)\\1{3,}").find(usuario.telefono.trimStart()) != null)
                    throw BusinessException("No puedes repetir el mismo numero tantas veces en el telefono.")
                //fecha de nacimiento
                if (usuario.fechaNacimiento!!.isAfter(LocalDate.now()))
                    throw BusinessException("Ingresa una fecha de nacimiento valida.")
                if (usuario.fechaNacimiento!!.isAfter(LocalDate.now().minusYears(18)))
                    throw BusinessException("El usuario debe ser mayor de 18 años")
                //documento
                if (usuario.documento.trimStart().isEmpty())
                    throw BusinessException("El documento del paciente no puede estar vacio")
                if (usuario.documento.trimStart().length < 6)
                    throw BusinessException("Ingrese más de 6 caracteres en el documento")
                if (usuario.documento.trimStart().length > 25)
                    throw BusinessException("El documento no puede contener tantos caracteres.")
                //nombre de usuario
                if (usuario.nombreUsuario.trimStart().length < 8)
                    throw BusinessException("EL nombre de usuario debe contener minimo 8 caracteres.")
                if (usuario.nombreUsuario.trimStart().length > 20)
                    throw BusinessException("El nombre de usuario no debe contener mas de 20 caracteres")
                if (!usuario.nombreUsuario.matches(Regex("^(?=.{8,20}\$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])\$")))
                    throw BusinessException("El nombre de usuario proporcionado no es valido.")
                //contrasena
                if (usuario.contrasena.trimStart().length < 8)
                    throw BusinessException("La contrasena debe tener al menos 8 caracteres.")
                if (usuario.contrasena.trimStart().isEmpty())
                    throw BusinessException("Debes especificar una contrasena para este usuario.")
                if (!usuario.contrasena.matches(Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}\$")))
                    throw BusinessException("La contrasena debe tener una letra minuscula, una mayuscula, un caracter especial y un numero para ser valida.")
                usuario.contrasena = String(sha256(usuario.contrasena))     //encriptar
                //fecha cambio contrasena
                if (usuario.fechaCambioContrasena!!.isAfter(LocalDate.now()))
                    throw BusinessException("La fecha de cambio de contrasena no puede ocurrir despues de esta.")
            }
            return usuarioRepository!!.saveAll(usuarios)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateUsuario(usuario: Usuario): Usuario {
        val opt: Optional<Usuario>
        try {
            opt=usuarioRepository!!.findById(usuario.id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el usuario ${usuario.id}")
        else{
            try {
                //nombre
                usuario.nombre = usuario.nombre.trimEnd().trimStart().lowercase()
                if (usuario.nombre.isEmpty())
                    throw BusinessException("El nombre del paciente no puede estar vacio")
                if (usuario.nombre.trimStart().length < 2)
                    throw BusinessException("Ingrese más de 2 caracteres en el nombre")
                if (usuario.nombre.trimStart().length >50)
                    throw BusinessException("El nombre no puede contener tantos caracteres.")
                if (Regex("(.)\\1{2,}").find(usuario.nombre.trimStart()) != null)
                    throw BusinessException("El nombre no puede contener tantas letras repetidas.")
                if (Regex("[^A-Za-zÁÉÍÓÚáéíóúñ\\s]").find(usuario.nombre.trimStart()) != null)
                    throw BusinessException("El nombre solo puede contener letras.")
                //cada nombre en mayuscula
                var nombre = ""
                for (string in usuario.nombre.split(Regex("\\s"))){
                    var nombreActual = ""
                    nombreActual = string.replaceFirstChar { it.uppercase() }
                    nombre += "$nombreActual "
                }
                usuario.nombre = nombre.trimEnd()
                //apellido
                usuario.apellido = usuario.apellido.trimEnd().trimStart().lowercase()
                if (usuario.apellido.isEmpty())
                    throw BusinessException("El apellido del paciente no puede estar vacio")
                if (usuario.apellido.trimStart().length < 2)
                    throw BusinessException("Ingrese más de 2 caracteres en el apellido")
                if (usuario.apellido.trimStart().length >50)
                    throw BusinessException("El apellido no puede contener tantos caracteres.")
                if (Regex("[^A-Za-zÁÉÍÓÚáéíóúñ\\s]").find(usuario.apellido.trimStart()) != null)
                    throw BusinessException("El apellido solo puede contener letras.")
                if (Regex("(.)\\1{2,}").find(usuario.apellido.trimStart()) != null)
                    throw BusinessException("El apellido no puede contener tantas letras repetidas.")
                //cada nombre en mayuscula
                var apellido = ""
                for (string in usuario.apellido.split(Regex("\\s"))){
                    var apellidoActual = ""
                    apellidoActual = string.replaceFirstChar { it.uppercase() }
                    apellido += "$apellidoActual "
                }
                usuario.apellido = apellido.trimEnd()
                //correo
                if (usuario.correo.isEmpty())
                    throw BusinessException("El correo del paciente no puede estar vacio")
                if (usuario.correo.trimStart().length < 6)
                    throw BusinessException("Ingrese más de 6 caracteres en el correo")
                if (usuario.correo.trimStart().length > 25)
                    throw BusinessException("El correo no puede contener tantos caracteres.")
                if (!usuario.correo.matches(Regex("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})\$")))
                    throw BusinessException("Ese es un correo invalido.")
                //direccion
                usuario.direccion = usuario.direccion.trimStart().trimEnd().lowercase()
                if (usuario.direccion.trimStart().isEmpty())
                    throw BusinessException("La direccion del paciente no puede estar vacio.")
                if (usuario.direccion.trimStart().length < 10 )
                    throw BusinessException("Ingrese más de 10 caracteres en la direccion.")
                if (usuario.direccion.trimStart().length > 150 )
                    throw BusinessException("La direccion del paciente no puede tener mas de 150 caracteres.")
                if (usuario.direccion.matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("La direccion del paciente no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(usuario.direccion.trimStart()) != null)
                    throw BusinessException("La direccion del paciente no puede contener tantos caracteres repetidos")
                usuario.direccion = usuario.direccion.replaceFirstChar { it.lowercase() }
                //telefono
                if (usuario.telefono.trimStart().isEmpty())
                    throw BusinessException("El telefono del paciente no puede estar vacio")
                if (usuario.telefono.trimStart().length < 8)
                    throw BusinessException("Ingrese más de 8 caracteres en el telefno")
                if (usuario.telefono.trimStart().length > 12)
                    throw BusinessException("El telefono no puede contener tantos caracteres.")
                if (!usuario.telefono.matches(Regex("^[2398]\\d*\$")))
                    throw BusinessException("Ese no es un numero de telefono valido.")
                if (Regex("(\\d)\\1{3,}").find(usuario.telefono.trimStart()) != null)
                    throw BusinessException("No puedes repetir el mismo numero tantas veces en el telefono.")
                //fecha de nacimiento
                if (usuario.fechaNacimiento!!.isAfter(LocalDate.now()))
                    throw BusinessException("Ingresa una fecha de nacimiento valida.")
                if (usuario.fechaNacimiento!!.isAfter(LocalDate.now().minusYears(18)))
                    throw BusinessException("El usuario debe ser mayor de 18 años")
                //documento
                if (usuario.documento.trimStart().isEmpty())
                    throw BusinessException("El documento del paciente no puede estar vacio")
                if (usuario.documento.trimStart().length < 6)
                    throw BusinessException("Ingrese más de 6 caracteres en el documento")
                if (usuario.documento.trimStart().length > 25)
                    throw BusinessException("El documento no puede contener tantos caracteres.")
                //nombre de usuario
                if (usuario.nombreUsuario.trimStart().length < 8)
                    throw BusinessException("EL nombre de usuario debe contener minimo 8 caracteres.")
                if (usuario.nombreUsuario.trimStart().length > 20)
                    throw BusinessException("El nombre de usuario no debe contener mas de 20 caracteres")
                if (!usuario.nombreUsuario.matches(Regex("^(?=.{8,20}\$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])\$")))
                    throw BusinessException("El nombre de usuario proporcionado no es valido.")
                //contrasena
                if (usuario.contrasena.trimStart().length < 8)
                    throw BusinessException("La contrasena debe tener al menos 8 caracteres.")
                if (usuario.contrasena.trimStart().isEmpty())
                    throw BusinessException("Debes especificar una contrasena para este usuario.")
                if (!usuario.contrasena.matches(Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}\$")))
                    throw BusinessException("La contrasena debe tener una letra minuscula, una mayuscula, un caracter especial y un numero para ser valida.")
                usuario.contrasena = String(sha256(usuario.contrasena))     //encriptar
                //fecha cambio contrasena
                if (usuario.fechaCambioContrasena!!.isAfter(LocalDate.now()))
                    throw BusinessException("La fecha de cambio de contrasena no puede ocurrir despues de esta.")

                usuarioRepository!!.save(usuario)
            }catch (e1:Exception){
                throw BusinessException(e1.message)
            }
        }
        return opt.get()
    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun getUsuarioByNombre(nombreUsuario: String): Usuario {
        val opt: Optional<Usuario>
        try {
            opt=usuarioRepository!!.findByNombre(nombreUsuario)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro usuario con el nombre del empleado: $nombreUsuario")
        return opt.get()
    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun getUsuarioByApellido(apellidoUsuario: String): Usuario {
        val opt: Optional<Usuario>
        try {
            opt=usuarioRepository!!.findByApellido(apellidoUsuario)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro usuario con el apellido: $apellidoUsuario")
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getUsuarioByNombreUsuario(nombreUsuario: String): Usuario {
        val opt: Optional<Usuario>
        try {
            opt=usuarioRepository!!.findByNombreUsuario(nombreUsuario)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro usuario con el nombre de usuario: $nombreUsuario")
        return opt.get()
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun getUsuarioByDocumento(documentoUsuario: String): Usuario {
        val opt: Optional<Usuario>
        try{
            opt=usuarioRepository!!.findByDocumento(documentoUsuario)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el usuario con numero de documento: $documentoUsuario")
        return opt.get()
    }


    @Throws(BusinessException::class, NotFoundException::class)
    override fun getUsuarioByRol(rolUsuario: Int): List<Usuario> {
      val opt: Optional<List<Usuario>>
      try {
          opt=usuarioRepository!!.findByRol(rolUsuario)
      }catch (e:Exception){
          throw BusinessException(e.message)
      }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro el usuario con el id de rol: $rolUsuario")
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getUsuarioByTelefono(telefonoUsuario: String): Usuario {
        val opt: Optional<Usuario>
        try {
            opt= usuarioRepository!!.findByTelefono(telefonoUsuario)
        }catch (e:Exception){
            throw NotFoundException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el telefono $telefonoUsuario")
        return opt.get()
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getUsuarioByCorreo(correoUsuario: String): Usuario {
        val opt: Optional<Usuario>
        try {
            opt= usuarioRepository!!.findByCorreo(correoUsuario)
        }catch (e:Exception){
            throw NotFoundException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el usuario con correo: $correoUsuario")
        return opt.get()
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getUsuarioByFechaNacimiento(fechaNacimientoUsuario: LocalDate?): Usuario {
        val opt: Optional<Usuario>
        try {
            opt= usuarioRepository!!.findByFechaNacimiento(fechaNacimientoUsuario)
        }catch (e:Exception){
            throw NotFoundException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encotro el usuario con fecha de Nacimiento: $fechaNacimientoUsuario")
        return opt.get()
    }

    private fun sha256(str: String): ByteArray = MessageDigest.getInstance("SHA-256").digest(str.toByteArray(UTF_8))
}