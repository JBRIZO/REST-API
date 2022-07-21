package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases


import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IEstadoBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.EstadoRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Estado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class EstadoBusiness: IEstadoBusiness {

    @Autowired
    val estadoRepository:EstadoRepository? = null

    @Throws(BusinessException::class)
    override fun getEstado(): List<Estado> {
        try {
            return estadoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getEstadoById(idEstado: Int): Estado {
        val opt: Optional<Estado>
        try {
            opt = estadoRepository!!.findById(idEstado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el Estado $idEstado")
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveEstado(estado: Estado): Estado {
       try {
           //nombre validaciones
           estado.nombre = estado.nombre.trimStart().trimEnd().lowercase()
           if (estado.nombre.trimStart().isEmpty())
               throw BusinessException("El nombre del estado no puede estar vacio.")
           if (estado.nombre.trimStart().length < 4 )
               throw BusinessException("Ingrese más de 4 caracteres en el nombre del estado.")
           if (estado.nombre.trimStart().length > 30 )
               throw BusinessException("El nombre del estado no puede tener mas de 30 caracteres.")
           if (!estado.nombre.matches(Regex("^[^\\d]*\$")))
               throw BusinessException("El nombre del estado no puede contener numeros.")
           if (Regex("(.)\\1{2,}").find(estado.nombre.trimStart()) != null)
               throw BusinessException("El nombre del estado no puede contener tantos caracteres repetidos")
           estado.nombre = estado.nombre.replaceFirstChar { it.uppercase() }
           //descripcion validaciones
           estado.descripcion = estado.descripcion.trimStart().trimEnd().lowercase()
           if (estado.descripcion.trimStart().isEmpty())
               throw BusinessException("La descripcion del estado no puede estar vacio.")
           if (estado.descripcion.trimStart().length < 10 )
               throw BusinessException("Ingrese más de 10 caracteres en la descripcion del estado.")
           if (estado.descripcion.trimStart().length > 150 )
               throw BusinessException("La descripcion del estado no puede tener mas de 25 caracteres.")
           if (estado.descripcion.matches(Regex("^[\\d ]*\$")))
               throw BusinessException("La descripcion del estado no puede contener solo numeros.")
           if (Regex("(.)\\1{2,}").find(estado.descripcion.trimStart()) != null)
               throw BusinessException("La descripcion del estado no puede contener tantos caracteres repetidos")
           estado.descripcion = estado.descripcion.replaceFirstChar { it.uppercase() }

           return estadoRepository!!.save(estado)
       }catch (e:DataIntegrityViolationException){
           throw BusinessException("Ya existe un estado con ese nombre.")
       }catch (e:Exception){
           throw BusinessException(e.message)
       }
    }
    @Throws(BusinessException::class)
    override fun saveEstados(estados: List<Estado>): List<Estado> {
        try {
            for (estado in estados){
                //nombre validaciones
                estado.nombre = estado.nombre.trimStart().trimEnd().lowercase()
                if (estado.nombre.trimStart().isEmpty())
                    throw BusinessException("El nombre del estado no puede estar vacio.")
                if (estado.nombre.trimStart().length < 4 )
                    throw BusinessException("Ingrese más de 4 caracteres en el nombre del estado.")
                if (estado.nombre.trimStart().length > 30 )
                    throw BusinessException("El nombre del estado no puede tener mas de 30 caracteres.")
                if (!estado.nombre.matches(Regex("^[^\\d]*\$")))
                    throw BusinessException("El nombre del estado no puede contener numeros.")
                if (Regex("(.)\\1{2,}").find(estado.nombre.trimStart()) != null)
                    throw BusinessException("El nombre del estado no puede contener tantos caracteres repetidos")
                estado.nombre = estado.nombre.replaceFirstChar { it.uppercase() }
                //descripcion validaciones
                estado.descripcion = estado.descripcion.trimStart().trimEnd().lowercase()
                if (estado.descripcion.trimStart().isEmpty())
                    throw BusinessException("La descripcion del estado no puede estar vacio.")
                if (estado.descripcion.trimStart().length < 10 )
                    throw BusinessException("Ingrese más de 10 caracteres en la descripcion del estado.")
                if (estado.descripcion.trimStart().length > 150 )
                    throw BusinessException("La descripcion del estado no puede tener mas de 25 caracteres.")
                if (estado.descripcion.matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("La descripcion del estado no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(estado.descripcion.trimStart()) != null)
                    throw BusinessException("La descripcion del estado no puede contener tantos caracteres repetidos")
                estado.descripcion = estado.descripcion.replaceFirstChar { it.uppercase() }
            }
            return estadoRepository!!.saveAll(estados)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateEstado(estado: Estado): Estado {
        val opt: Optional<Estado>
        try {
            opt=estadoRepository!!.findById(estado.id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el estado ${estado.id}")
        else{
            try {
                //nombre validaciones
                estado.nombre = estado.nombre.trimStart().trimEnd().lowercase()
                if (estado.nombre.trimStart().isEmpty())
                    throw BusinessException("El nombre del estado no puede estar vacio.")
                if (estado.nombre.trimStart().length < 4 )
                    throw BusinessException("Ingrese más de 4 caracteres en el nombre del estado.")
                if (estado.nombre.trimStart().length > 30 )
                    throw BusinessException("El nombre del estado no puede tener mas de 30 caracteres.")
                if (!estado.nombre.matches(Regex("^[^\\d]*\$")))
                    throw BusinessException("El nombre del estado no puede contener numeros.")
                if (Regex("(.)\\1{2,}").find(estado.nombre.trimStart()) != null)
                    throw BusinessException("El nombre del estado no puede contener tantos caracteres repetidos")
                estado.nombre = estado.nombre.replaceFirstChar { it.uppercase() }
                //descripcion validaciones
                estado.descripcion = estado.descripcion.trimStart().trimEnd().lowercase()
                if (estado.descripcion.trimStart().isEmpty())
                    throw BusinessException("La descripcion del estado no puede estar vacio.")
                if (estado.descripcion.trimStart().length < 10 )
                    throw BusinessException("Ingrese más de 10 caracteres en la descripcion del estado.")
                if (estado.descripcion.trimStart().length > 150 )
                    throw BusinessException("La descripcion del estado no puede tener mas de 25 caracteres.")
                if (estado.descripcion.matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("La descripcion del estado no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(estado.descripcion.trimStart()) != null)
                    throw BusinessException("La descripcion del estado no puede contener tantos caracteres repetidos")
                estado.descripcion = estado.descripcion.replaceFirstChar { it.uppercase() }
            }catch (e1:DataIntegrityViolationException){
                throw BusinessException("Ya existe un estado con ese nombre.")
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getEstadoByNombre(nombreEstado: String): Estado {
        val opt: Optional<Estado>
        try{
            opt = estadoRepository!!.findByNombre(nombreEstado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro estado con el nombre $nombreEstado")
        return opt.get()
    }
}