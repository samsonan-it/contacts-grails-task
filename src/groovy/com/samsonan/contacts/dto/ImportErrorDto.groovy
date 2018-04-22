package com.samsonan.contacts.dto

import org.springframework.validation.FieldError

class ImportErrorDto {

	def id;
	String errorMsg		  		 // general error
	def fieldErrors = []  // Field Errors
	
	ImportErrorDto(id, String errorMsg) {
		this.id = id
		this.errorMsg = errorMsg
	}
	
	ImportErrorDto(id, List<FieldError> fieldErrors) {
		this.id = id
		this.fieldErrors = fieldErrors
	}

	@Override
	String toString() {
		return "ImportErrorDto [id=" + id + ", errorMsg = " + errorMsg + ", fieldErrors count=" + fieldErrors.size() + "]"
	}
		
}
