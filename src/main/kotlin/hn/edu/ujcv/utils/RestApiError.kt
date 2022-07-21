package hn.edu.ujcv.PDM_2022_I_EQUIPO3.utils

import org.springframework.http.HttpStatus

class RestApiError(val httpStatus: HttpStatus, val errorMessage:String, val errorDetails:String) {
}