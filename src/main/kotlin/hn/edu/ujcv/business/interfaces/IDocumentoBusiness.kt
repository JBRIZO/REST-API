package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.TipoDocumento

interface IDocumentoBusiness {
    fun getTipoDocumento(): List<TipoDocumento>
    fun getTipoDocumentoById(idTipoDocumento: Int): TipoDocumento
    fun saveTipoDocumento(tipoDocumento: TipoDocumento): TipoDocumento
    fun saveTipoDocumento(tipoDocumento: List<TipoDocumento>): List<TipoDocumento>
    fun updateTipoDocumento(tipoDocumento: TipoDocumento):TipoDocumento
    fun getTipoDocumentoByNombre(nombre: String):TipoDocumento
}