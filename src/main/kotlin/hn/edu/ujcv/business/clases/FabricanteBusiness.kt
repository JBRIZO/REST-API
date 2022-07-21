package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.clases

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces.IFabricanteBusiness
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.dao.FabricanteRepository
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.FabricanteVacuna
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class FabricanteBusiness: IFabricanteBusiness {
    @Autowired
    val fabricanteRepository: FabricanteRepository?=null
    override fun getFabricanteVacuna(): List<FabricanteVacuna> {
        try {
            return fabricanteRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun getFabricanteVacunaById(idFabricanteVacuna: Int): FabricanteVacuna {
        val opt: Optional<FabricanteVacuna>
        try {
            opt=fabricanteRepository!!.findById(idFabricanteVacuna)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el fabricante con id: $idFabricanteVacuna")
        return opt.get()
    }
    @Throws(BusinessException::class)
    override fun saveFabricanteVacuna(fabricanteVacuna: FabricanteVacuna): FabricanteVacuna {
        try {
            fabricanteVacuna.nombre = fabricanteVacuna.nombre.trimEnd().lowercase()
            if (fabricanteVacuna.nombre.trimStart().isEmpty())
                throw BusinessException("El nombre del fabricante no puede ir en blanco.")
            if (fabricanteVacuna.nombre.trimStart().length < 4)
                throw BusinessException("El nombre del fabricante no puede ser tan corto.")
            if (fabricanteVacuna.nombre.trimStart().length > 40)
                throw BusinessException("El nombre del fabricante no puede contener tantos caracteres.")
            if (fabricanteVacuna.nombre.matches(Regex("^[\\d ]*\$")))
                throw BusinessException("El nombre del fabricante no puede contener solo numeros.")
            if (Regex("(.)\\1{3,}").find(fabricanteVacuna.nombre) != null)
                throw BusinessException("El nombre del fabricante no puede contener tantos caracteres repetidas.")
            fabricanteVacuna.nombre = fabricanteVacuna.nombre.replaceFirstChar { it.uppercase() }

            return fabricanteRepository!!.save(fabricanteVacuna)
        }catch (e:DataIntegrityViolationException){
            throw BusinessException("Ya existe un fabricante con ese nombre.")
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

    }
    @Throws(BusinessException::class)
    override fun saveFabricanteVacuna(fabricanteVacunas: List<FabricanteVacuna>): List<FabricanteVacuna> {
        try {
            for (fabricanteVacuna in fabricanteVacunas){
                fabricanteVacuna.nombre = fabricanteVacuna.nombre.trimEnd().lowercase()
                if (fabricanteVacuna.nombre.trimStart().isEmpty())
                    throw BusinessException("El nombre del fabricante no puede ir en blanco.")
                if (fabricanteVacuna.nombre.trimStart().length < 4)
                    throw BusinessException("El nombre del fabricante no puede ser tan corto.")
                if (fabricanteVacuna.nombre.trimStart().length > 40)
                    throw BusinessException("El nombre del fabricante no puede contener tantos caracteres.")
                if (fabricanteVacuna.nombre.matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("El nombre del fabricante no puede contener solo numeros.")
                if (Regex("(.)\\1{3,}").find(fabricanteVacuna.nombre) != null)
                    throw BusinessException("El nombre del fabricante no puede contener tantos caracteres repetidas.")
                fabricanteVacuna.nombre = fabricanteVacuna.nombre.replaceFirstChar { it.uppercase() }
            }
            return fabricanteRepository!!.saveAll(fabricanteVacunas)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }

    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun updateFabricanteVacuna(fabricanteVacuna: FabricanteVacuna): FabricanteVacuna {
        val opt: Optional<FabricanteVacuna>
        try {
            opt=fabricanteRepository!!.findById(fabricanteVacuna.id)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el fabricante con id: ${fabricanteVacuna.id}")
        else{
            try {
                fabricanteVacuna.nombre = fabricanteVacuna.nombre.trimEnd().lowercase()
                if (fabricanteVacuna.nombre.trimStart().isEmpty())
                    throw BusinessException("El nombre del fabricante no puede ir en blanco.")
                if (fabricanteVacuna.nombre.trimStart().length < 4)
                    throw BusinessException("El nombre del fabricante no puede ser tan corto.")
                if (fabricanteVacuna.nombre.trimStart().length > 40)
                    throw BusinessException("El nombre del fabricante no puede contener tantos caracteres.")
                if (fabricanteVacuna.nombre.matches(Regex("^[\\d ]*\$")))
                    throw BusinessException("El nombre del fabricante no puede contener solo numeros.")
                if (Regex("(.)\\1{3,}").find(fabricanteVacuna.nombre) != null)
                    throw BusinessException("El nombre del fabricante no puede contener tantos caracteres repetidas.")
                fabricanteVacuna.nombre = fabricanteVacuna.nombre.replaceFirstChar { it.uppercase() }
                fabricanteRepository!!.save(fabricanteVacuna)
            }catch (e1:DataIntegrityViolationException){
                throw BusinessException("Ya existe un fabricante con ese nombre.")
            }catch (e : Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()

    }
    @Throws(BusinessException::class,NotFoundException::class)
    override fun getFabricanteVacunaByNombre(nombre: String): FabricanteVacuna {
        val opt: Optional<FabricanteVacuna>
        try {
            opt=fabricanteRepository!!.findByNombre(nombre)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!opt.isPresent)
            throw NotFoundException("No se encontro el fabricante con nombre: $nombre")
        return opt.get()

    }

}