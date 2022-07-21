package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IPacienteBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.PacienteRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Paciente
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class PacienteBusiness: IPacienteBusiness {

    @Autowired
    val pacienteRepository: PacienteRepository? = null

    @Throws(BusinessException::class)
    override fun getPacientes(): List<Paciente> {
        try {
            return pacienteRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getPacienteById(idPaciente: Int): Paciente {
        val opt: Optional<Paciente>
        try {
            opt=pacienteRepository!!.findById(idPaciente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el paciente $idPaciente")
        return opt.get()
    }


    @Throws(BusinessException::class)
    override fun savePaciente(paciente: Paciente): Paciente {
        try {
            //nombre
            paciente.nombre = paciente.nombre.trimStart().trimEnd().lowercase()
            if (paciente.nombre.isEmpty())
                throw BusinessException("El nombre del paciente no puede estar vacio")
            if (paciente.nombre.trimStart().length < 2)
                throw BusinessException("Ingrese más de 2 caracteres en el nombre")
            if (paciente.nombre.trimStart().length >50)
                throw BusinessException("El nombre no puede contener tantos caracteres.")
            if (Regex("(.)\\1{2,}").find(paciente.nombre.trimStart()) != null)
                throw BusinessException("El nombre no puede contener tantas letras repetidas.")
            if (Regex("[^A-Za-zÁÉÍÓÚáéíóúñ\\s]").find(paciente.nombre.trimStart()) != null)
                throw BusinessException("El nombre solo puede contener letras.")
            //cada nombre en mayuscula
            var nombre = ""
            for (string in paciente.nombre.split(Regex("\\s"))){
                var nombreActual = ""
                nombreActual = string.replaceFirstChar { it.uppercase() }
                nombre += "$nombreActual "
            }
            paciente.nombre = nombre.trimEnd()
            //apellido
            paciente.apellido = paciente.apellido.trimStart().trimEnd().lowercase()
            if (paciente.apellido.isEmpty())
                throw BusinessException("El apellido del paciente no puede estar vacio")
            if (paciente.apellido.trimStart().length < 2)
                throw BusinessException("Ingrese más de 2 caracteres en el apellido")
            if (paciente.apellido.trimStart().length >50)
                throw BusinessException("El apellido no puede contener tantos caracteres.")
            if (Regex("[^A-Za-zÁÉÍÓÚáéíóúñ\\s]").find(paciente.apellido.trimStart()) != null)
                throw BusinessException("El apellido solo puede contener letras.")
            if (Regex("(.)\\1{2,}").find(paciente.apellido.trimStart()) != null)
                throw BusinessException("El apellido no puede contener tantas letras repetidas.")
            //cada apellido en mayuscula
            var apellido = ""
            for (string in paciente.apellido.split(Regex("\\s"))){
                var apellidoActual = ""
                apellidoActual = string.replaceFirstChar { it.uppercase() }
                apellido += "$apellidoActual "
            }
            paciente.apellido = apellido.trimEnd()
            //documento
            if (paciente.documento.isEmpty())
                throw BusinessException("El documento del paciente no puede estar vacio")
            if (paciente.documento.trimStart().length < 6)
                throw BusinessException("Ingrese más de 6 caracteres en el documento")
            if (paciente.documento.trimStart().length > 25)
                throw BusinessException("El documento no puede contener tantos caracteres.")
            //correo
            if (paciente.correo.isEmpty())
                throw BusinessException("El correo del paciente no puede estar vacio")
            if (paciente.correo.trimStart().length < 6)
                throw BusinessException("Ingrese más de 6 caracteres en el correo")
            if (paciente.correo.trimStart().length > 25)
                throw BusinessException("El correo no puede contener tantos caracteres.")
            if (!paciente.correo.matches(Regex("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})\$")))
                throw BusinessException("Ese es un correo invalido.")
            //direccion
            paciente.direccion = paciente.direccion.trimStart().trimEnd().lowercase()
            if (paciente.direccion.trimStart().isEmpty())
                throw BusinessException("La direccion del paciente no puede estar vacio.")
            if (paciente.direccion.trimStart().length < 10 )
                throw BusinessException("Ingrese más de 10 caracteres en la direccion.")
            if (paciente.direccion.trimStart().length > 150 )
                throw BusinessException("La direccion del paciente no puede tener mas de 150 caracteres.")
            if (paciente.direccion.matches(Regex("^[\\d ]*\$")))
                throw BusinessException("La direccion del paciente no puede contener solo numeros.")
            if (Regex("(.)\\1{2,}").find(paciente.direccion.trimStart()) != null)
                throw BusinessException("La direccion del paciente no puede contener tantos caracteres repetidos")
            paciente.direccion = paciente.direccion.replaceFirstChar { it.uppercase() }
            //telefono
            if (paciente.telefono.isEmpty())
                throw BusinessException("El telefono del paciente no puede estar vacio")
            if (paciente.telefono.trimStart().length < 8)
                throw BusinessException("Ingrese más de 8 caracteres en el telefno")
            if (paciente.telefono.trimStart().length > 12)
                throw BusinessException("El telefono no puede contener tantos caracteres.")
            if (!paciente.telefono.matches(Regex("^[2398]\\d*\$")))
                throw BusinessException("Ese no es un numero de telefono valido.")
            if (Regex("(\\d)\\1{3,}").find(paciente.telefono.trimStart()) != null)
                throw BusinessException("No puedes repetir el mismo numero tantas veces en el telefono.")
            //fecha de nacimiento
            if (paciente.fechaNacimiento!!.isAfter(LocalDate.now()))
                throw BusinessException("Ingresa una fecha de nacimiento valida.")

            return pacienteRepository!!.save(paciente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class)
    override fun savePacientes(pacientes: List<Paciente>): List<Paciente> {
        try {
            for (paciente in pacientes){
                //nombre
                paciente.nombre = paciente.nombre.trimStart().trimEnd().lowercase()
                if (paciente.nombre.isEmpty())
                    throw BusinessException("El nombre del paciente no puede estar vacio")
                if (paciente.nombre.trimStart().length < 2)
                    throw BusinessException("Ingrese más de 2 caracteres en el nombre")
                if (paciente.nombre.trimStart().length >50)
                    throw BusinessException("El nombre no puede contener tantos caracteres.")
                if (Regex("(.)\\1{2,}").find(paciente.nombre.trimStart()) != null)
                    throw BusinessException("El nombre no puede contener tantas letras repetidas.")
                if (Regex("[^A-Za-zÁÉÍÓÚáéíóúñ\\s]").find(paciente.nombre.trimStart()) != null)
                    throw BusinessException("El nombre solo puede contener letras.")
                //cada nombre en mayuscula
                var nombre = ""
                for (string in paciente.nombre.split(Regex("\\s"))){
                    var nombreActual = ""
                    nombreActual = string.replaceFirstChar { it.uppercase() }
                    nombre += "$nombreActual "
                }
                paciente.nombre = nombre.trimEnd()
                //apellido
                paciente.apellido = paciente.apellido.trimStart().trimEnd().lowercase()
                if (paciente.apellido.isEmpty())
                    throw BusinessException("El apellido del paciente no puede estar vacio")
                if (paciente.apellido.trimStart().length < 2)
                    throw BusinessException("Ingrese más de 2 caracteres en el apellido")
                if (paciente.apellido.trimStart().length >50)
                    throw BusinessException("El apellido no puede contener tantos caracteres.")
                if (Regex("[^A-Za-zÁÉÍÓÚáéíóúñ\\s]").find(paciente.apellido.trimStart()) != null)
                    throw BusinessException("El apellido solo puede contener letras.")
                if (Regex("(.)\\1{2,}").find(paciente.apellido.trimStart()) != null)
                    throw BusinessException("El apellido no puede contener tantas letras repetidas.")
                //cada apellido en mayuscula
                var apellido = ""
                for (string in paciente.apellido.split(Regex("\\s"))){
                    var apellidoActual = ""
                    apellidoActual = string.replaceFirstChar { it.uppercase() }
                    apellido += "$apellidoActual "
                }
                paciente.apellido = apellido.trimEnd()
                //documento
                if (paciente.documento.isEmpty())
                    throw BusinessException("El documento del paciente no puede estar vacio")
                if (paciente.documento.trimStart().length < 6)
                    throw BusinessException("Ingrese más de 6 caracteres en el documento")
                if (paciente.documento.trimStart().length > 25)
                    throw BusinessException("El documento no puede contener tantos caracteres.")
                //correo
                if (paciente.correo.isEmpty())
                    throw BusinessException("El correo del paciente no puede estar vacio")
                if (paciente.correo.trimStart().length < 6)
                    throw BusinessException("Ingrese más de 6 caracteres en el correo")
                if (paciente.correo.trimStart().length > 25)
                    throw BusinessException("El correo no puede contener tantos caracteres.")
                if (!paciente.correo.matches(Regex("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})\$")))
                    throw BusinessException("Ese es un correo invalido.")
                //direccion
                paciente.direccion = paciente.direccion.trimStart().trimEnd().lowercase()
                if (paciente.direccion.trimStart().isEmpty())
                    throw BusinessException("La direccion del paciente no puede estar vacio.")
                if (paciente.direccion.trimStart().length < 10 )
                    throw BusinessException("Ingrese más de 10 caracteres en la direccion.")
                if (paciente.direccion.trimStart().length > 150 )
                    throw BusinessException("La direccion del paciente no puede tener mas de 150 caracteres.")
                if (paciente.direccion.matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("La direccion del paciente no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(paciente.direccion.trimStart()) != null)
                    throw BusinessException("La direccion del paciente no puede contener tantos caracteres repetidos")
                paciente.direccion = paciente.direccion.replaceFirstChar { it.uppercase() }
                //telefono
                if (paciente.telefono.isEmpty())
                    throw BusinessException("El telefono del paciente no puede estar vacio")
                if (paciente.telefono.trimStart().length < 8)
                    throw BusinessException("Ingrese más de 8 caracteres en el telefno")
                if (paciente.telefono.trimStart().length > 12)
                    throw BusinessException("El telefono no puede contener tantos caracteres.")
                if (!paciente.telefono.matches(Regex("^[2398]\\d*\$")))
                    throw BusinessException("Ese no es un numero de telefono valido.")
                if (Regex("(\\d)\\1{3,}").find(paciente.telefono.trimStart()) != null)
                    throw BusinessException("No puedes repetir el mismo numero tantas veces en el telefono.")
                //fecha de nacimiento
                if (paciente.fechaNacimiento!!.isAfter(LocalDate.now()))
                    throw BusinessException("Ingresa una fecha de nacimiento valida.")
            }
            return pacienteRepository!!.saveAll(pacientes)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }


    @Throws(BusinessException::class, NotFoundException::class)
    override fun updatePaciente(paciente: Paciente): Paciente {
        val opt: Optional<Paciente>
        try {
            opt=pacienteRepository!!.findById(paciente.id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el paciente con id ${paciente.id}")
        else{
            try {
                //nombre
                paciente.nombre = paciente.nombre.trimStart().trimEnd().lowercase()
                if (paciente.nombre.isEmpty())
                    throw BusinessException("El nombre del paciente no puede estar vacio")
                if (paciente.nombre.trimStart().length < 2)
                    throw BusinessException("Ingrese más de 2 caracteres en el nombre")
                if (paciente.nombre.trimStart().length >50)
                    throw BusinessException("El nombre no puede contener tantos caracteres.")
                if (Regex("(.)\\1{2,}").find(paciente.nombre.trimStart()) != null)
                    throw BusinessException("El nombre no puede contener tantas letras repetidas.")
                if (Regex("[^A-Za-zÁÉÍÓÚáéíóúñ\\s]").find(paciente.nombre.trimStart()) != null)
                    throw BusinessException("El nombre solo puede contener letras.")
                //cada nombre en mayuscula
                var nombre = ""
                for (string in paciente.nombre.split(Regex("\\s"))){
                    var nombreActual = ""
                    nombreActual = string.replaceFirstChar { it.uppercase() }
                    nombre += "$nombreActual "
                }
                paciente.nombre = nombre.trimEnd()
                //apellido
                paciente.apellido = paciente.apellido.trimStart().trimEnd().lowercase()
                if (paciente.apellido.isEmpty())
                    throw BusinessException("El apellido del paciente no puede estar vacio")
                if (paciente.apellido.trimStart().length < 2)
                    throw BusinessException("Ingrese más de 2 caracteres en el apellido")
                if (paciente.apellido.trimStart().length >50)
                    throw BusinessException("El apellido no puede contener tantos caracteres.")
                if (Regex("[^A-Za-zÁÉÍÓÚáéíóúñ\\s]").find(paciente.apellido.trimStart()) != null)
                    throw BusinessException("El apellido solo puede contener letras.")
                if (Regex("(.)\\1{2,}").find(paciente.apellido.trimStart()) != null)
                    throw BusinessException("El apellido no puede contener tantas letras repetidas.")
                //cada apellido en mayuscula
                var apellido = ""
                for (string in paciente.apellido.split(Regex("\\s"))){
                    var apellidoActual = ""
                    apellidoActual = string.replaceFirstChar { it.uppercase() }
                    apellido += "$apellidoActual "
                }
                paciente.apellido = apellido.trimEnd()
                //documento
                if (paciente.documento.isEmpty())
                    throw BusinessException("El documento del paciente no puede estar vacio")
                if (paciente.documento.trimStart().length < 6)
                    throw BusinessException("Ingrese más de 6 caracteres en el documento")
                if (paciente.documento.trimStart().length > 25)
                    throw BusinessException("El documento no puede contener tantos caracteres.")
                //correo
                if (paciente.correo.isEmpty())
                    throw BusinessException("El correo del paciente no puede estar vacio")
                if (paciente.correo.trimStart().length < 6)
                    throw BusinessException("Ingrese más de 6 caracteres en el correo")
                if (paciente.correo.trimStart().length > 25)
                    throw BusinessException("El correo no puede contener tantos caracteres.")
                if (!paciente.correo.matches(Regex("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})\$")))
                    throw BusinessException("Ese es un correo invalido.")
                //direccion
                paciente.direccion = paciente.direccion.trimStart().trimEnd().lowercase()
                if (paciente.direccion.trimStart().isEmpty())
                    throw BusinessException("La direccion del paciente no puede estar vacio.")
                if (paciente.direccion.trimStart().length < 10 )
                    throw BusinessException("Ingrese más de 10 caracteres en la direccion.")
                if (paciente.direccion.trimStart().length > 150 )
                    throw BusinessException("La direccion del paciente no puede tener mas de 150 caracteres.")
                if (paciente.direccion.matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("La direccion del paciente no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(paciente.direccion.trimStart()) != null)
                    throw BusinessException("La direccion del paciente no puede contener tantos caracteres repetidos")
                paciente.direccion = paciente.direccion.replaceFirstChar { it.uppercase() }
                //telefono
                if (paciente.telefono.isEmpty())
                    throw BusinessException("El telefono del paciente no puede estar vacio")
                if (paciente.telefono.trimStart().length < 8)
                    throw BusinessException("Ingrese más de 8 caracteres en el telefno")
                if (paciente.telefono.trimStart().length > 12)
                    throw BusinessException("El telefono no puede contener tantos caracteres.")
                if (!paciente.telefono.matches(Regex("^[2398]\\d*\$")))
                    throw BusinessException("Ese no es un numero de telefono valido.")
                if (Regex("(\\d)\\1{3,}").find(paciente.telefono.trimStart()) != null)
                    throw BusinessException("No puedes repetir el mismo numero tantas veces en el telefono.")
                //fecha de nacimiento
                if (paciente.fechaNacimiento!!.isAfter(LocalDate.now()))
                    throw BusinessException("Ingresa una fecha de nacimiento valida.")

                pacienteRepository!!.save(paciente)
            }catch (e1:Exception){
                throw BusinessException(e1.message)
            }
        }
        return opt.get()
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getPacienteByNombre(nombrePaciente: String): Paciente {
        val opt: Optional<Paciente>
        try {
            opt=pacienteRepository!!.findByNombre(nombrePaciente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el paciente con el nombre: $nombrePaciente")
        return opt.get()
    }


    override fun getPacienteByIdColonia(idColoniaPaciente: Int): List<Paciente> {
        val opt: Optional<List<Paciente>>
        try {
            opt=pacienteRepository!!.findByColonia(idColoniaPaciente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro paciente en la colonia con id $idColoniaPaciente")
        return opt.get()
    }


    override fun getPacienteByTelefono(telefonoPaciente: String): Paciente {
        val opt: Optional<Paciente>
        try {
            opt=pacienteRepository!!.findByTelefono(telefonoPaciente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el paciente con numero de telefono: $telefonoPaciente")
        return opt.get()
    }

    override fun getPacienteByDocumento(documentoPaciente: String): Paciente {
        val opt: Optional<Paciente>
        try {
            opt=pacienteRepository!!.findByDocumento(documentoPaciente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el paciente con numero de documento: $documentoPaciente")
        return opt.get()
    }

    override fun getPacienteByCorreo(correoPaciente: String): Paciente {
        val opt: Optional<Paciente>
        try {
            opt=pacienteRepository!!.findByCorreo(correoPaciente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el paciente con el correo: $correoPaciente")
        return opt.get()
    }
}