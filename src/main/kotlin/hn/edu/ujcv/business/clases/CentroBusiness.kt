package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.ICentroBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.CentroRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Centro
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class CentroBusiness: ICentroBusiness {
    @Autowired
    val centroRepository: CentroRepository? = null

    @Throws(BusinessException::class)
    override fun getCentros(): List<Centro> {
        try {
            return centroRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getCentroById(idCentro: Int): Centro {
        val opt: Optional<Centro>
        try {
            opt=centroRepository!!.findById(idCentro)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el centro de vacunacion $idCentro")
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveCentro(centro: Centro): Centro {
        try {
            //nombre
            centro.nombre = centro.nombre.trimStart().trimEnd().lowercase()
            if (centro.nombre.isEmpty())
                throw BusinessException("El nombre no puede estar en blanco.")
            if (centro.nombre.length < 4)
                throw BusinessException("El nombre no puede ser tan corto.")
            if (centro.nombre.length >50)
                throw BusinessException("El nombre no puede ser tan largo.")
            if (centro.nombre.matches(Regex("^[\\d ]*\$")))
                throw BusinessException("El nombre del centro no puede contener solo numeros.")
            if (Regex("(.)\\1{2,}").find(centro.nombre) != null)
                throw BusinessException("El nombre no puede contener tantas letras repetidas.")
            centro.nombre = centro.nombre.replaceFirstChar { it.uppercase() }
            //direccion
            centro.direccion = centro.direccion.trimStart().trimEnd().lowercase()
            if (centro.direccion.isEmpty())
                throw BusinessException("La direccion no puede estar en blanco.")
            if (centro.direccion.length < 10)
                throw BusinessException("La direccion no puede ser tan corta.")
            if (centro.direccion.length >150)
                throw BusinessException("La direccion no puede ser tan larga.")
            if (Regex("(.)\\1{4,}").find(centro.direccion) != null)
                throw BusinessException("La direccion no puede contener tantas letras repetidas.")
            centro.direccion = centro.direccion.replaceFirstChar { it.uppercase() }


            return centroRepository!!.save(centro)
        }catch (e:DataIntegrityViolationException){
            throw BusinessException("Ya existe un centro de vacunacion con ese nombre.")
        }catch (e : Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class)
    override fun saveCentros(centros: List<Centro>): List<Centro> {
        try {
           for (centro in centros){
               //nombre
               centro.nombre = centro.nombre.trimStart().trimEnd().lowercase()
               if (centro.nombre.isEmpty())
                   throw BusinessException("El nombre no puede estar en blanco.")
               if (centro.nombre.length < 4)
                   throw BusinessException("El nombre no puede ser tan corto.")
               if (centro.nombre.length >50)
                   throw BusinessException("El nombre no puede ser tan largo.")
               if (centro.nombre.matches(Regex("^[\\d ]*\$")))
                   throw BusinessException("El nombre del centro no puede contener solo numeros.")
               if (Regex("(.)\\1{2,}").find(centro.nombre) != null)
                   throw BusinessException("El nombre no puede contener tantas letras repetidas.")
               centro.nombre = centro.nombre.replaceFirstChar { it.uppercase() }
               //direccion
               centro.direccion = centro.direccion.trimStart().trimEnd().lowercase()
               if (centro.direccion.isEmpty())
                   throw BusinessException("La direccion no puede estar en blanco.")
               if (centro.direccion.length < 10)
                   throw BusinessException("La direccion no puede ser tan corta.")
               if (centro.direccion.length >150)
                   throw BusinessException("La direccion no puede ser tan larga.")
               if (Regex("(.)\\1{4,}").find(centro.direccion) != null)
                   throw BusinessException("La direccion no puede contener tantas letras repetidas.")
               centro.direccion = centro.direccion.replaceFirstChar { it.uppercase() }
           }
            return centroRepository!!.saveAll(centros)
        }catch (e:DataIntegrityViolationException){
            throw BusinessException("Ya existe un centro de vacunacion con ese nombre.")
        }catch (e : Exception){
        throw BusinessException(e.message)
        }
    }


    @Throws(BusinessException::class, NotFoundException::class)
    override fun updateCentro(centro: Centro): Centro {
        val opt: Optional<Centro>
        try {
            opt=centroRepository!!.findById(centro.id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el centro con id: ${centro.id}")
        else{
            try {
                //nombre
                centro.nombre = centro.nombre.trimStart().trimEnd().lowercase()
                if (centro.nombre.isEmpty())
                    throw BusinessException("El nombre no puede estar en blanco.")
                if (centro.nombre.length < 4)
                    throw BusinessException("El nombre no puede ser tan corto.")
                if (centro.nombre.length >50)
                    throw BusinessException("El nombre no puede ser tan largo.")
                if (centro.nombre.matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("El nombre del centro no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(centro.nombre) != null)
                    throw BusinessException("El nombre no puede contener tantas letras repetidas.")
                centro.nombre = centro.nombre.replaceFirstChar { it.uppercase() }
                //direccion
                centro.direccion = centro.direccion.trimStart().trimEnd().lowercase()
                if (centro.direccion.isEmpty())
                    throw BusinessException("La direccion no puede estar en blanco.")
                if (centro.direccion.length < 10)
                    throw BusinessException("La direccion no puede ser tan corta.")
                if (centro.direccion.length >150)
                    throw BusinessException("La direccion no puede ser tan larga.")
                if (Regex("(.)\\1{4,}").find(centro.direccion) != null)
                    throw BusinessException("La direccion no puede contener tantas letras repetidas.")
                centro.direccion = centro.direccion.replaceFirstChar { it.uppercase() }
                centroRepository!!.save(centro)
            }catch (e1:DataIntegrityViolationException){
                throw BusinessException("Ya existe un centro de vacunacion con ese nombre.")
            }catch (e : Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getCentroByNombre(nombreCentro: String): Centro {
        val opt: Optional<Centro>
        try {
            opt=centroRepository!!.findByNombre(nombreCentro)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el centro con nombre: $nombreCentro")
        return opt.get()
    }

}
