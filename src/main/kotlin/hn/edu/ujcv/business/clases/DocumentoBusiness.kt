package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IDocumentoBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.DocumentoRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.TipoDocumento
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class DocumentoBusiness: IDocumentoBusiness {
    @Autowired
    val documentoRepository: DocumentoRepository?=null
    @Throws(BusinessException::class)
    override fun getTipoDocumento(): List<TipoDocumento> {
        try {
            return documentoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }    }
    @Throws(BusinessException::class, NotFoundException::class)
    override fun getTipoDocumentoById(idTipoDocumento: Int): TipoDocumento {
        val opt: Optional<TipoDocumento>
        try {
            opt=documentoRepository!!.findById(idTipoDocumento)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el tipo de documento con id $idTipoDocumento")
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveTipoDocumento(tipoDocumento: TipoDocumento): TipoDocumento {
        try {
            tipoDocumento.nombre = tipoDocumento.nombre.trimStart().trimEnd().lowercase()
            if (tipoDocumento.nombre.isEmpty())
                throw BusinessException("El nombre no puede ir en blanco.")
            if (tipoDocumento.nombre.length < 3)
                throw BusinessException("El nombre no puede ser tan corto.")
            if (tipoDocumento.nombre.length >50)
                throw BusinessException("El nombre no puede contener tantos caracteres.")
            if (!tipoDocumento.nombre.matches(Regex("^[^\\d]*\$")))
                throw BusinessException("El nombre no puede contener numeros.")
            if (Regex("(.)\\1{2,}").find(tipoDocumento.nombre) != null)
                throw BusinessException("El nombre no puede contener tantas letras repetidas.")
            tipoDocumento.nombre = tipoDocumento.nombre.replaceFirstChar { it.uppercase() }

            return documentoRepository!!.save(tipoDocumento)
        }catch (e:DataIntegrityViolationException){
            throw BusinessException("Ya existe un tipo de documento con ese nombre.")
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

    }
    @Throws(BusinessException::class)
    override fun saveTipoDocumento(tipoDocumentoList: List<TipoDocumento>): List<TipoDocumento> {
        try {
            for (tipoDocumento in tipoDocumentoList){
                tipoDocumento.nombre = tipoDocumento.nombre.trimStart().trimEnd().lowercase()
                if (tipoDocumento.nombre.isEmpty())
                    throw BusinessException("El nombre no puede ir en blanco.")
                if (tipoDocumento.nombre.length < 3)
                    throw BusinessException("El nombre no puede ser tan corto.")
                if (tipoDocumento.nombre.length >50)
                    throw BusinessException("El nombre no puede contener tantos caracteres.")
                if (!tipoDocumento.nombre.matches(Regex("^[^\\d]*\$")))
                    throw BusinessException("El nombre no puede contener numeros.")
                if (Regex("(.)\\1{2,}").find(tipoDocumento.nombre) != null)
                    throw BusinessException("El nombre no puede contener tantas letras repetidas.")
                tipoDocumento.nombre = tipoDocumento.nombre.replaceFirstChar { it.uppercase() }
            }
            return documentoRepository!!.saveAll(tipoDocumentoList)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateTipoDocumento(tipoDocumento: TipoDocumento): TipoDocumento {
        val opt: Optional<TipoDocumento>
        try {
            opt=documentoRepository!!.findById(tipoDocumento.id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el tipo de documento con id ${tipoDocumento.id}")
        else{
            try {
                tipoDocumento.nombre = tipoDocumento.nombre.trimStart().trimEnd().lowercase()
                if (tipoDocumento.nombre.isEmpty())
                    throw BusinessException("El nombre no puede ir en blanco.")
                if (tipoDocumento.nombre.length < 3)
                    throw BusinessException("El nombre no puede ser tan corto.")
                if (tipoDocumento.nombre.length >50)
                    throw BusinessException("El nombre no puede contener tantos caracteres.")
                if (!tipoDocumento.nombre.matches(Regex("^[^\\d]*\$")))
                    throw BusinessException("El nombre no puede contener numeros.")
                if (Regex("(.)\\1{2,}").find(tipoDocumento.nombre) != null)
                    throw BusinessException("El nombre no puede contener tantas letras repetidas.")
                tipoDocumento.nombre = tipoDocumento.nombre.replaceFirstChar { it.uppercase() }
                documentoRepository!!.save(tipoDocumento)
            }catch (e:DataIntegrityViolationException){
                throw BusinessException("Ya existe un tipo de documento con ese nombre.")
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun getTipoDocumentoByNombre(nombre: String): TipoDocumento {
        val opt: Optional<TipoDocumento>
        try {
            opt=documentoRepository!!.findByNombre(nombre)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el tipo de documento con nombre: $nombre")
        return opt.get()
    }
}