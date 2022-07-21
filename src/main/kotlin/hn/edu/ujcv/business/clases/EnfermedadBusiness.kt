package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IEnfermedadBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.EnfermedadRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Enfermedad
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class EnfermedadBusiness: IEnfermedadBusiness {
        @Autowired
        val enfermedadRepository: EnfermedadRepository? = null

        @Throws(BusinessException::class)
    override fun getEnfermedades(): List<Enfermedad> {
        try {
            return enfermedadRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
        override fun getEnfermedadById(idEnfermedad: Int): Enfermedad {
            val opt: Optional<Enfermedad>
            try {
                opt=enfermedadRepository!!.findById(idEnfermedad)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
            if (!opt.isPresent)
                throw NotFoundException("No se encontro la enfermedad base con Id $idEnfermedad")
            return opt.get()
        }
        @Throws(BusinessException::class)
        override fun saveEnfermedad(enfermedad: Enfermedad): Enfermedad {
            try {
                enfermedad.nombre = enfermedad.nombre.trimStart().trimEnd().lowercase()
                if (enfermedad.nombre.isEmpty())
                    throw BusinessException("El nombre no puede ir en blanco.")
                if (enfermedad.nombre.trimStart().length < 5)
                    throw BusinessException("El nombre no puede ser tan corto.")
                if (enfermedad.nombre.trimStart().length >50)
                    throw BusinessException("El nombre no puede contener tantos caracteres.")
                if (enfermedad.nombre.trimStart().matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("El nombre de la enfermedad no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(enfermedad.nombre) != null)
                    throw BusinessException("El nombre no puede contener tantos caracteres repetidos")
                enfermedad.nombre = enfermedad.nombre.replaceFirstChar { it.uppercase() }
                return enfermedadRepository!!.save(enfermedad)
            }catch (e:DataIntegrityViolationException){
                throw BusinessException("Ya existe una enfermedad base con ese nombre.")
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        @Throws(BusinessException::class)
        override fun saveEnfermedades(enfermedades: List<Enfermedad>): List<Enfermedad> {
            try {
                for (enfermedad in enfermedades){
                    enfermedad.nombre = enfermedad.nombre.trimStart().trimEnd().lowercase()
                    if (enfermedad.nombre.isEmpty())
                        throw BusinessException("El nombre no puede ir en blanco.")
                    if (enfermedad.nombre.trimStart().length < 5)
                        throw BusinessException("El nombre no puede ser tan corto.")
                    if (enfermedad.nombre.trimStart().length >50)
                        throw BusinessException("El nombre no puede contener tantos caracteres.")
                    if (enfermedad.nombre.trimStart().matches(Regex("^[\\d ]*\$")))
                        throw BusinessException("El nombre de la enfermedad no puede contener solo numeros.")
                    if (Regex("(.)\\1{2,}").find(enfermedad.nombre) != null)
                        throw BusinessException("El nombre no puede contener tantos caracteres repetidos")
                    enfermedad.nombre = enfermedad.nombre.replaceFirstChar { it.uppercase() }
                }
                return enfermedadRepository!!.saveAll(enfermedades)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }


        @Throws(BusinessException::class, NotFoundException::class)
        override fun updateEnfermedad(enfermedad: Enfermedad): Enfermedad {
            val opt: Optional<Enfermedad>
            try {
                opt=enfermedadRepository!!.findById(enfermedad.id)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
            if (!opt.isPresent)
                throw NotFoundException("No se encontro la enfermedad ${enfermedad.id}")
            else{
                try {
                    enfermedad.nombre = enfermedad.nombre.trimStart().trimEnd().lowercase()
                    if (enfermedad.nombre.isEmpty())
                        throw BusinessException("El nombre no puede ir en blanco.")
                    if (enfermedad.nombre.trimStart().length < 5)
                        throw BusinessException("El nombre no puede ser tan corto.")
                    if (enfermedad.nombre.trimStart().length >50)
                        throw BusinessException("El nombre no puede contener tantos caracteres.")
                    if (enfermedad.nombre.trimStart().matches(Regex("^[\\d ]*\$")))
                        throw BusinessException("El nombre de la enfermedad no puede contener solo numeros.")
                    if (Regex("(.)\\1{2,}").find(enfermedad.nombre) != null)
                        throw BusinessException("El nombre no puede contener tantos caracteres repetidos")
                    enfermedad.nombre = enfermedad.nombre.replaceFirstChar { it.uppercase() }
                    enfermedadRepository!!.save(enfermedad)
                }catch (e1:DataIntegrityViolationException){
                    throw BusinessException("Ya existe una enfermedad base con ese nombre.")
                }catch (e:Exception){
                    throw BusinessException(e.message)
                }
            }
            return opt.get()
        }
        @Throws(BusinessException::class, NotFoundException::class)
        override fun getEnfermedadByNombre(nombreEnfermedad: String): Enfermedad {
            val opt: Optional<Enfermedad>
            try {
                opt=enfermedadRepository!!.findByNombre(nombreEnfermedad)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
            if (!opt.isPresent)
                throw NotFoundException("No se encontro la enfermedad $nombreEnfermedad")
            return opt.get()
        }
    }
