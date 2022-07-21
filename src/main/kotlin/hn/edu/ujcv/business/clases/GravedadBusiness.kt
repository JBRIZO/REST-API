package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IGravedadBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.GravedadRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.Gravedad
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service

class GravedadBusiness: IGravedadBusiness {
        @Autowired
        val gravedadRepository: GravedadRepository? = null

        @Throws(BusinessException::class)
        override fun getGravedades(): List<Gravedad> {
            try {
                return gravedadRepository!!.findAll()
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }

        @Throws(BusinessException::class, NotFoundException::class)
        override fun getGravedadById(idGravedad: Int): Gravedad {
            val opt: Optional<Gravedad>
            try {
                opt=gravedadRepository!!.findById(idGravedad)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
            if (!opt.isPresent)
                throw NotFoundException("No se encontro la gravedad del sintoma $idGravedad")
            return opt.get()
        }
        @Throws(BusinessException::class)
        override fun saveGravedad(gravedad: Gravedad): Gravedad {
            try {
                //nombre
                gravedad.nombre = gravedad.nombre.trimEnd().lowercase()
                if (gravedad.nombre.trimStart().isEmpty())
                    throw BusinessException("El nombre de la gravedad no puede ir en blanco.")
                if (gravedad.nombre.trimStart().length < 4)
                    throw BusinessException("El nombre de la gravedad  no puede ser tan corto.")
                if (gravedad.nombre.trimStart().length > 40)
                    throw BusinessException("El nombre de la gravedad  no puede contener tantos caracteres.")
                if (!gravedad.nombre.matches(Regex("^[^\\d]*\$")))
                    throw BusinessException("El nombre de la gravedad no puede contener numeros.")
                if (Regex("(.)\\1{2,}").find(gravedad.nombre) != null)
                    throw BusinessException("El nombre de la gravedad no puede contener tantos caracteres repetidas.")
                gravedad.nombre = gravedad.nombre.replaceFirstChar { it.uppercase() }
                //descripcion
                gravedad.descripcion = gravedad.descripcion.trimEnd().lowercase()
                if (gravedad.descripcion.trimStart().isEmpty())
                    throw BusinessException("La descripcion de la gravedad no puede ir en blanco.")
                if (gravedad.descripcion.trimStart().length < 10)
                    throw BusinessException("La descripcion de la gravedad no puede ser tan corta.")
                if (gravedad.descripcion.trimStart().length > 150)
                    throw BusinessException("La descripcion de la gravedad no puede contener tantos caracteres.")
                if (gravedad.descripcion.matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("La descripcion de la gravedad no puede contener solo numeros.")
                if (Regex("(.)\\1{2,}").find(gravedad.descripcion) != null)
                    throw BusinessException("La descripcion de la gravedad no puede contener tantos caracteres repetidos.")
                gravedad.descripcion = gravedad.descripcion.replaceFirstChar { it.uppercase() }

                return gravedadRepository!!.save(gravedad)
            }catch (e:DataIntegrityViolationException){
                throw BusinessException("Ya existe una gravedad con ese nombre")
            }
        }
        @Throws(BusinessException::class)
        override fun saveGravedades(gravedades: List<Gravedad>): List<Gravedad> {
            try {
                for (gravedad in gravedades){
                    //nombre
                    gravedad.nombre = gravedad.nombre.trimEnd().lowercase()
                    if (gravedad.nombre.trimStart().isEmpty())
                        throw BusinessException("El nombre de la gravedad no puede ir en blanco.")
                    if (gravedad.nombre.trimStart().length < 4)
                        throw BusinessException("El nombre de la gravedad  no puede ser tan corto.")
                    if (gravedad.nombre.trimStart().length > 40)
                        throw BusinessException("El nombre de la gravedad  no puede contener tantos caracteres.")
                    if (!gravedad.nombre.matches(Regex("^[^\\d]*\$")))
                        throw BusinessException("El nombre de la gravedad no puede contener numeros.")
                    if (Regex("(.)\\1{2,}").find(gravedad.nombre) != null)
                        throw BusinessException("El nombre de la gravedad no puede contener tantos caracteres repetidas.")
                    gravedad.nombre = gravedad.nombre.replaceFirstChar { it.uppercase() }
                    //descripcion
                    gravedad.descripcion = gravedad.descripcion.trimEnd().lowercase()
                    if (gravedad.descripcion.trimStart().isEmpty())
                        throw BusinessException("La descripcion de la gravedad no puede ir en blanco.")
                    if (gravedad.descripcion.trimStart().length < 10)
                        throw BusinessException("La descripcion de la gravedad no puede ser tan corta.")
                    if (gravedad.descripcion.trimStart().length > 150)
                        throw BusinessException("La descripcion de la gravedad no puede contener tantos caracteres.")
                    if (gravedad.descripcion.matches(Regex("^[\\d ]*\$")))
                        throw BusinessException("La descripcion de la gravedad no puede contener solo numeros.")
                    if (Regex("(.)\\1{2,}").find(gravedad.descripcion) != null)
                        throw BusinessException("La descripcion de la gravedad no puede contener tantos caracteres repetidos.")
                    gravedad.descripcion = gravedad.descripcion.replaceFirstChar { it.uppercase() }
                }
                return gravedadRepository!!.saveAll(gravedades)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }



        @Throws(BusinessException::class, NotFoundException::class)
        override fun updateGravedad(gravedad: Gravedad): Gravedad {
            val opt: Optional<Gravedad>
            try {
                opt=gravedadRepository!!.findById(gravedad.id)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
            if (!opt.isPresent)
                throw NotFoundException("No se encontro la gravedad del sintoma ${gravedad.id}")
            else{
                try {
                    //nombre
                    gravedad.nombre = gravedad.nombre.trimEnd().lowercase()
                    if (gravedad.nombre.trimStart().isEmpty())
                        throw BusinessException("El nombre de la gravedad no puede ir en blanco.")
                    if (gravedad.nombre.trimStart().length < 4)
                        throw BusinessException("El nombre de la gravedad  no puede ser tan corto.")
                    if (gravedad.nombre.trimStart().length > 40)
                        throw BusinessException("El nombre de la gravedad  no puede contener tantos caracteres.")
                    if (!gravedad.nombre.matches(Regex("^[^\\d]*\$")))
                        throw BusinessException("El nombre de la gravedad no puede contener numeros.")
                    if (Regex("(.)\\1{2,}").find(gravedad.nombre) != null)
                        throw BusinessException("El nombre de la gravedad no puede contener tantos caracteres repetidas.")
                    gravedad.nombre = gravedad.nombre.replaceFirstChar { it.uppercase() }
                    //descripcion
                    gravedad.descripcion = gravedad.descripcion.trimEnd().lowercase()
                    if (gravedad.descripcion.trimStart().isEmpty())
                        throw BusinessException("La descripcion de la gravedad no puede ir en blanco.")
                    if (gravedad.descripcion.trimStart().length < 10)
                        throw BusinessException("La descripcion de la gravedad no puede ser tan corta.")
                    if (gravedad.descripcion.trimStart().length > 150)
                        throw BusinessException("La descripcion de la gravedad no puede contener tantos caracteres.")
                    if (gravedad.descripcion.matches(Regex("^[\\d ]*\$")))
                        throw BusinessException("La descripcion de la gravedad no puede contener solo numeros.")
                    if (Regex("(.)\\1{2,}").find(gravedad.descripcion) != null)
                        throw BusinessException("La descripcion de la gravedad no puede contener tantos caracteres repetidos.")
                    gravedad.descripcion = gravedad.descripcion.replaceFirstChar { it.uppercase() }
                    gravedadRepository!!.save(gravedad)
                }catch (e1:DataIntegrityViolationException){
                    throw BusinessException("Ya existe una gravedad con ese nombre.")
                }catch (e : Exception){
                    throw BusinessException(e.message)
                }
            }
            return opt.get()
        }

        @Throws(BusinessException::class, NotFoundException::class)
        override fun getGravedadByNombre(nombreGravedad: String): Gravedad {
            val opt: Optional<Gravedad>
            try {
                opt=gravedadRepository!!.findByNombre(nombreGravedad)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
            if (!opt.isPresent)
                throw NotFoundException("No se encontro la gravedad del sintoma con nombre: $nombreGravedad")
            return opt.get()
        }
    }
