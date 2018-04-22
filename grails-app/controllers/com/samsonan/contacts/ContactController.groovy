package com.samsonan.contacts

import static org.springframework.http.HttpStatus.*

import org.apache.commons.lang.StringUtils
import org.springframework.context.MessageSource
import org.springframework.web.multipart.MultipartFile

import com.fasterxml.jackson.databind.MappingIterator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema

import grails.transaction.Transactional

@Transactional(readOnly = true)
class ContactController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def contactImportService
	
	MessageSource messageSource
	
	def index() {
		respond Contact.list(params), model:[contactInstanceCount: Contact.count()]
	}

	def show(Contact contactInstance) {
		respond contactInstance
	}

	def create() {
		respond new Contact(params)
	}

	@Transactional
	def save(Contact contactInstance) {

		if (contactInstance == null) {
			notFound()
			return
		}

		if (contactInstance.hasErrors()) {
			respond contactInstance.errors, view:'create'
			return
		}

		if (contactInstance.getAge() <= 0) {
			contactInstance.errors.rejectValue('birthDate', 'contact.birthDate.wrong')
			respond contactInstance.errors, view:'create'
			return
		}

		contactInstance.save flush:true
		
		
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [
					message(code: 'contact.label', default: 'Contact')
				])
				redirect action:"index", method:"GET"
			}
			'*' { respond contactInstance, [status: CREATED] }
		}
	}

	def edit(Contact contactInstance) {
		respond contactInstance
	}

	@Transactional
	def update(Contact contactInstance) {
		if (contactInstance == null) {
			notFound()
			return
		}

		if (contactInstance.hasErrors()) {
			respond contactInstance.errors, view:'edit'
			return
		}

		if (contactInstance.getAge() <= 0) {
			contactInstance.errors.rejectValue('birthDate', 'contact.birthDate.wrong')
			respond contactInstance.errors, view:'edit'
			return
		}
		
		contactInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [
					message(code: 'Contact.label', default: 'Contact')
				])
				redirect contactInstance // show by default
			}
			'*'{ respond contactInstance, [status: OK] }
		}
	}

	def doUpload() {
		MultipartFile file = request.getFile('file')

		def errorList = []
		def errorMsg = ""
		
		try {
			errorList = contactImportService.upload(file)
		} catch (Exception e) {
			errorMsg = messageSource.getMessage('parse.file.fail', [file.getOriginalFilename()] as Object[], 'Failed to parse the file', request.locale)
		}
		
		errorList.each {
			
			if (!StringUtils.isEmpty(it.errorMsg)) {
				errorMsg += messageSource.getMessage('parse.file.row.error', [it.id, it.errorMsg] as Object[], 'Failed to import contact row', request.locale)
			} else if (!it.fieldErrors.isEmpty()) {

				def fieldErrorMsg = ""				
				
				it.fieldErrors.each {
					fieldErrorMsg += messageSource.getMessage('parse.file.field.error', [it.getField(), it.getRejectedValue()] as Object[], 'Field {0} has wrong value {1}', request.locale)
				}

				errorMsg += messageSource.getMessage('parse.file.row.error', [it.id, fieldErrorMsg] as Object[], 'Failed to import contact row', request.locale)
			}
		}
		
		request.withFormat {
			form multipartForm {
				if (StringUtils.isEmpty(errorMsg)) {
					flash.message = message(code: 'parse.file.success', default: 'File was uploaded successfully')
				} else {
					flash.errors = errorMsg
				}
				redirect (action:'index')
			}
		}
	}
		
	@Transactional
	def delete(Contact contactInstance) {

		if (contactInstance == null) {
			notFound()
			return
		}

		contactInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [
					message(code: 'Contact.label', default: 'Contact')
				])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'contact.label', default: 'Contact'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
