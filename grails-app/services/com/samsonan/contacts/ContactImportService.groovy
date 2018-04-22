package com.samsonan.contacts

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.databind.MappingIterator
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.databind.ObjectReader

import com.samsonan.contacts.dto.ContactDto
import com.samsonan.contacts.dto.ImportErrorDto

import grails.transaction.Transactional

@Transactional
class ContactImportService {

	MessageSource messageSource
	
	/**
	 * Upload give Multipart File
	 */
    def upload(file) {
		
		MappingIterator<ContactDto> iterator = initReader()
			.readValues(convert(file))
		
		return processDto(iterator);
			
    }

	/**
	 * Used to test parsing logic 
	 */
	def parse(csvString) {
		
		MappingIterator<ContactDto> iterator = initReader()
			.readValues(csvString)
		
		return processDto(iterator);
			
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
			def errorMsg = []
			
			try {
				contact = ContactBuilder.buildContact(value)
			} catch (Exception e) {
				errorMsg.add(e.message)
				errorList.add(new ImportErrorDto(value.id, errorMsg))
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
	
	/**
	 * Convert MultipartFile -> File
	 */
	private File convert(file)
	{
		File convFile = new File(file.originalFilename)
		convFile.withOutputStream { stream -> stream.write(file.bytes) }
		return convFile
	}
	
}
