package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.ILaboratorioBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.LaboratorioRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Laboratorio
import org.aspectj.apache.bcel.classfile.ExceptionTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class LaboratorioBusiness: ILaboratorioBusiness {

    @Autowired
    val laboratorioRepository:LaboratorioRepository? = null

    @Throws(BusinessException::class)
    override fun getLaboratorio(): List<Laboratorio> {
        try {
            return laboratorioRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getLaboratorioById(idLaboratorio: Int): Laboratorio {
        val opt: Optional<Laboratorio>
        try {
            opt = laboratorioRepository!!.findById(idLaboratorio)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encotro el Laboratorio $idLaboratorio")
        return opt.get()
    }

    @Throws(BusinessException::class)
    override fun saveLaboratorio(laboratorio: Laboratorio): Laboratorio {
        try {
            //nombre
            laboratorio.nombre = laboratorio.nombre.trimEnd().lowercase()
            if (laboratorio.nombre.trimStart().isEmpty())
                throw BusinessException("El nombre no puede ir en blanco.")
            if (laboratorio.nombre.trimStart().length < 3)
                throw BusinessException("El nombre no puede ser tan corto.")
            if (laboratorio.nombre.trimStart().length >40)
                throw BusinessException("El nombre no puede contener tantos caracteres.")
            if (laboratorio.nombre.matches(Regex("^[\\d ]*\$")))
                throw BusinessException("El nombre no puede contener solo numeros.")
            if (Regex("(.)\\1{2,}").find(laboratorio.nombre) != null)
                throw BusinessException("El nombre no puede contener tantos caracteres repetidos.")
            laboratorio.nombre = laboratorio.nombre.replaceFirstChar { it.uppercase() }
            //direccion
            laboratorio.direccion = laboratorio.direccion.trimEnd().lowercase()
            if (laboratorio.direccion.trimStart().isEmpty())
                throw BusinessException("La direccion no puede ir vacia.")
            if (laboratorio.direccion.trimStart().length < 10)
                throw BusinessException("La direccion no puede ser tan corta.")
            if (laboratorio.direccion.trimStart().length >150)
                throw BusinessException("La direccion no puede ser tan larga.")
            if (Regex("(.)\\1{4,}").find(laboratorio.direccion.trimStart()) != null)
                throw BusinessException("La direccion no puede contener tantas letras repetidas.")
            laboratorio.direccion = laboratorio.direccion.replaceFirstChar { it.uppercase() }

            return laboratorioRepository!!.save(laboratorio)
        }catch (e:DataIntegrityViolationException){
            throw BusinessException("Ya existe un laboratorio con ese nombre.")
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class)
    override fun saveLaboratorios(laboratorios: List<Laboratorio>): List<Laboratorio> {
        try {
            for (laboratorio in laboratorios){
                //nombre
                laboratorio.nombre = laboratorio.nombre.trimEnd().lowercase()
                if (laboratorio.nombre.trimStart().isEmpty())
                    throw BusinessException("El nombre no puede ir en blanco.")
                if (laboratorio.nombre.trimStart().length < 3)
                    throw BusinessException("El nombre no puede ser tan corto.")
                if (laboratorio.nombre.trimStart().length >40)
                    throw BusinessException("El nombre no puede contener tantos caracteres.")
                if (laboratorio.nombre.matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("El nombre no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(laboratorio.nombre) != null)
                    throw BusinessException("El nombre no puede contener tantos caracteres repetidos.")
                laboratorio.nombre = laboratorio.nombre.replaceFirstChar { it.uppercase() }
                //direccion
                laboratorio.direccion = laboratorio.direccion.trimEnd().lowercase()
                if (laboratorio.direccion.trimStart().isEmpty())
                    throw BusinessException("La direccion no puede ir vacia.")
                if (laboratorio.direccion.trimStart().length < 10)
                    throw BusinessException("La direccion no puede ser tan corta.")
                if (laboratorio.direccion.trimStart().length >150)
                    throw BusinessException("La direccion no puede ser tan larga.")
                if (Regex("(.)\\1{4,}").find(laboratorio.direccion.trimStart()) != null)
                    throw BusinessException("La direccion no puede contener tantas letras repetidas.")
                laboratorio.direccion = laboratorio.direccion.replaceFirstChar { it.uppercase() }
            }
            return laboratorioRepository!!.saveAll(laboratorios)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateLaboratorio(laboratorio: Laboratorio): Laboratorio {
        val opt: Optional<Laboratorio>
        try {
            opt=laboratorioRepository!!.findById(laboratorio.id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el Laboratorio ${laboratorio.id}")
        else{
            try {
                //nombre
                laboratorio.nombre = laboratorio.nombre.trimEnd().lowercase()
                if (laboratorio.nombre.trimStart().isEmpty())
                    throw BusinessException("El nombre no puede ir en blanco.")
                if (laboratorio.nombre.trimStart().length < 3)
                    throw BusinessException("El nombre no puede ser tan corto.")
                if (laboratorio.nombre.trimStart().length >40)
                    throw BusinessException("El nombre no puede contener tantos caracteres.")
                if (laboratorio.nombre.matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("El nombre no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(laboratorio.nombre) != null)
                    throw BusinessException("El nombre no puede contener tantos caracteres repetidos.")
                laboratorio.nombre = laboratorio.nombre.replaceFirstChar { it.uppercase() }
                //direccion
                laboratorio.direccion = laboratorio.direccion.trimEnd().lowercase()
                if (laboratorio.direccion.trimStart().isEmpty())
                    throw BusinessException("La direccion no puede ir vacia.")
                if (laboratorio.direccion.trimStart().length < 10)
                    throw BusinessException("La direccion no puede ser tan corta.")
                if (laboratorio.direccion.trimStart().length >150)
                    throw BusinessException("La direccion no puede ser tan larga.")
                if (Regex("(.)\\1{4,}").find(laboratorio.direccion.trimStart()) != null)
                    throw BusinessException("La direccion no puede contener tantas letras repetidas.")
                laboratorio.direccion = laboratorio.direccion.replaceFirstChar { it.uppercase() }
            }catch (e1:DataIntegrityViolationException){
                throw BusinessException("Ya existe un laboratorio con ese nombre.")
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getLaboratorioByNombre(nombreLaboratorio: String): Laboratorio {
        val opt: Optional<Laboratorio>
        try {
            opt=laboratorioRepository!!.findByNombre(nombreLaboratorio)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se laboratorio con nombre: $nombreLaboratorio")
        return opt.get()

    }

}