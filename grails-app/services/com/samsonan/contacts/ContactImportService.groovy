package com.samsonan.contacts

import com.fasterxml.jackson.databind.MappingIterator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.databind.ObjectReader
import com.samsonan.contacts.dto.ContactDto
import com.samsonan.contacts.dto.ImportErrorDto

import grails.transaction.Transactional

/**
 * All import and parsing logic 
 */
@Transactional
class ContactImportService {

	/**
	 * Upload give Multipart File
	 */
    def upload(file) {

		MappingIterator<ContactDto> iterator = initReader()
			.readValues(file.bytes)
	
		return processDto(iterator)
    }

	/**
	 * Method is for testing, to see how our parsing logic works 
	 */
	def parse(csvString) {
		
		MappingIterator<ContactDto> iterator = initReader()
			.readValues(csvString)
		
		return processDto(iterator)
	}
		
	private ObjectReader initReader() {
		CsvMapper mapper = new CsvMapper()
		CsvSchema schema = mapper.schemaFor(ContactDto.class)

		char separatorChar = ';'
				
		schema = schema.withColumnSeparator(separatorChar)
			.withNullValue("NULL")
			.withoutEscapeChar()

		return mapper
			.readerFor(ContactDto.class)
			.with(schema)
	}
	
	private processDto(iterator) {
		def errorList = [] // ImportErrorDto
		
		while (iterator.hasNextValue()) {
			ContactDto value = iterator.nextValue()
	
			log.debug 'going to import CSV contact: ' + value
			
			Contact contact
			
			try {
				contact = ContactBuilder.buildContact(value)
			} catch (Exception e) {
				errorList.add(new ImportErrorDto(value.id, e.message))
				continue
			}
			
			log.debug 'contact instance created: ' + contact
			
			if (contact.validate()) {
				contact.save flush:true
			} else {
				errorList.add(new ImportErrorDto(value.id, contact.errors.allErrors))
			}
		}
		
		return errorList
	}

}
