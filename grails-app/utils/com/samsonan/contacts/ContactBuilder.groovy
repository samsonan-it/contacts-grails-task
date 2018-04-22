package com.samsonan.contacts

import com.samsonan.contacts.dto.ContactDto

/**
 * Build domain object from DTO 
 */
class ContactBuilder {

	final static DATE_PATTERN = "dd.MM.yyyy"
	
	static def buildContact(ContactDto contactDto) {
		def contact = new Contact()

		def fullNameSplit = contactDto.fullName.split()

		switch (fullNameSplit.length) {
			case 1:
				contact.firstName = fullNameSplit[0]
				break
			case 2:
				contact.firstName = fullNameSplit[0]
				contact.lastName = fullNameSplit[1]
				break
			default: // 3+
				contact.firstName = fullNameSplit[1]
				contact.middleName = fullNameSplit[2]
				contact.lastName = fullNameSplit[0]
				break;
		}

		contact.email = contactDto.email
		contact.phoneNumber = contactDto.phoneNumber
		if (contactDto.birthDate != null) {
			contact.birthDate = Date.parse(DATE_PATTERN, contactDto.birthDate)
		}

		return contact
	}
}
