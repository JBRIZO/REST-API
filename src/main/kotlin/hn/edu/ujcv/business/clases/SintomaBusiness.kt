package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.ISintomaBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.SintomaRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Sintoma
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class SintomaBusiness: ISintomaBusiness {

    @Autowired
    val sintomaRepository:SintomaRepository? = null

    @Throws(BusinessException::class)
    override fun getSintoma(): List<Sintoma> {
        try {
            return sintomaRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getSintomaById(idSintoma: Int): Sintoma {
        val opt: Optional<Sintoma>
        try {
            opt = sintomaRepository!!.findById(idSintoma)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encotro el sintoma con el id: $idSintoma")
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveSintoma(sintoma: Sintoma): Sintoma {
        try {
            //nombre
            sintoma.nombre = sintoma.nombre.trimStart().trimEnd().lowercase()
            if (sintoma.nombre.isEmpty())
                throw BusinessException("El nombre no puede ir en blanco.")
            if (sintoma.nombre.trimStart().length < 4)
                throw BusinessException("El nombre no puede ser tan corto.")
            if (sintoma.nombre.trimStart().length > 30)
                throw BusinessException("El nombre no puede contener tantos caracteres.")
            if (sintoma.nombre.trimStart().matches(Regex("^[\\d ]*\$")))
                throw BusinessException("El nombre del sintoma no puede contener solo numeros.")
            if (Regex("(.)\\1{2,}").find(sintoma.nombre) != null)
                throw BusinessException("El nombre no puede contener tantos caracteres repetidos")
            sintoma.nombre = sintoma.nombre.replaceFirstChar { it.uppercase() }

            return sintomaRepository!!.save(sintoma)
        }catch (e:DataIntegrityViolationException){
            throw BusinessException("Ya existe un sintoma con ese nombre.")
        }catch (e : Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class)
    override fun saveSintomas(sintomas: List<Sintoma>): List<Sintoma> {
        try {
            for (sintoma in sintomas){
                //nombre
                sintoma.nombre = sintoma.nombre.trimStart().trimEnd().lowercase()
                if (sintoma.nombre.isEmpty())
                    throw BusinessException("El nombre no puede ir en blanco.")
                if (sintoma.nombre.trimStart().length < 4)
                    throw BusinessException("El nombre no puede ser tan corto.")
                if (sintoma.nombre.trimStart().length > 30)
                    throw BusinessException("El nombre no puede contener tantos caracteres.")
                if (sintoma.nombre.trimStart().matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("El nombre del sintoma no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(sintoma.nombre) != null)
                    throw BusinessException("El nombre no puede contener tantos caracteres repetidos")
                sintoma.nombre = sintoma.nombre.replaceFirstChar { it.uppercase() }
            }
            return sintomaRepository!!.saveAll(sintomas)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateSintoma(sintoma: Sintoma): Sintoma {
        val opt: Optional<Sintoma>
        try {
            opt=sintomaRepository!!.findById(sintoma.id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el sintoma con id: ${sintoma.id}")
        else{
            try {
                //nombre
                sintoma.nombre = sintoma.nombre.trimStart().trimEnd().lowercase()
                if (sintoma.nombre.isEmpty())
                    throw BusinessException("El nombre no puede ir en blanco.")
                if (sintoma.nombre.trimStart().length < 4)
                    throw BusinessException("El nombre no puede ser tan corto.")
                if (sintoma.nombre.trimStart().length > 30)
                    throw BusinessException("El nombre no puede contener tantos caracteres.")
                if (sintoma.nombre.trimStart().matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("El nombre del sintoma no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(sintoma.nombre) != null)
                    throw BusinessException("El nombre no puede contener tantos caracteres repetidos")
                sintoma.nombre = sintoma.nombre.replaceFirstChar { it.uppercase() }

                sintomaRepository!!.save(sintoma)
            }catch (e1:DataIntegrityViolationException){
                throw BusinessException("Ya existe un sintoma con ese nombre.")
            }catch (e : Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getSintomaByNombre(nombreSintoma: String): Sintoma {
        val opt: Optional<Sintoma>
        try {
            opt=sintomaRepository!!.findByNombre(nombreSintoma)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro sintoma con nombre: $nombreSintoma")
        return opt.get()
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getSintomasByIdGravedadSintoma(idGravedadSintoma: Int): List<Sintoma> {
        val opt: Optional<List<Sintoma>>
        try {
            opt=sintomaRepository!!.findByGravedad(idGravedadSintoma)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro sintoma con id de gravedad sintoma: $idGravedadSintoma")
        return opt.get()
    }
}