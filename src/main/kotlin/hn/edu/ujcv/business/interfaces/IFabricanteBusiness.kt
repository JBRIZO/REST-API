package hn.edu.ujcv.PDM_2022_I_EQUIPO3.business.interfaces

import hn.edu.ujcv.PDM_2022_I_EQUIPO3.model.FabricanteVacuna


interface IFabricanteBusiness {

    fun getFabricanteVacuna(): List<FabricanteVacuna>
    fun getFabricanteVacunaById(idFabricanteVacuna: Int): FabricanteVacuna
    fun saveFabricanteVacuna(fabricanteVacuna: FabricanteVacuna): FabricanteVacuna
    fun saveFabricanteVacuna(fabricanteVacuna: List<FabricanteVacuna>): List<FabricanteVacuna>
    fun updateFabricanteVacuna(fabricanteVacuna: FabricanteVacuna): FabricanteVacuna
    fun getFabricanteVacunaByNombre(nombre: String): FabricanteVacuna

}