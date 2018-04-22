package com.samsonan.contacts

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(ContactImportService)
@Mock(Contact)
class ContactImportServiceSpec extends Specification {

    void "Test valid CSV" () {
		
		when:"correct CSV is given"
			def csvString = "2;Papanin Algorithm Vumnovich;09.05.1945;79112221111;papanin@mailservice.edu"
			def errors = service.parse(csvString)

		then:"contact is created"
			errors.size() == 0
			Contact.count() == 1
			Contact contact = Contact.first()
			contact.lastName == "Papanin"
			contact.firstName == "Algorithm"
			contact.middleName == "Vumnovich"
			contact.email == "papanin@mailservice.edu"
			contact.phoneNumber == "79112221111"
			contact.birthDate.format( 'MM-dd-yyyy' ) == "05-09-1945" 
    }
	
	void "Test valid multiline CSV" () {
		
		when:"correct multiline CSV is given"
			def csvString = "2;Papanin Algorithm Vumnovich;09.05.1945;79112221111;papanin@mailservice.edu\n" +
				"3;Pokalechu Vsehovod Frodovich;22.06.1941;37222211111;vsepokale@mailservice.edu"
			def errors = service.parse(csvString)

		then:"contact is created"
			errors.size() == 0
			Contact.count() == 2
			// field check is skipped
    }
	
	void "Test valid CSV wrong data" () {
		
		when:"valid CSV with wrong data is given"
			def csvString = "2;Papanin Algorithm"
			def errors = service.parse(csvString)

		then:"data errors are expected"
			errors.size() == 1
			errors[0].id == "2" //id
			errors[0].fieldErrors.size() == 2 //
			errors[0].fieldErrors[0].getField() == "middleName"
			errors[0].fieldErrors[1].getField() == "birthDate"
			Contact.count() == 0
	}
	
	void "Test valid CSV with wrong and correct data" () {
		
		when:"valid CSV with wrong wrong and correct is given"
			def csvString = "2;Papanin Vumnovich;09.05.1945;79112221111;papanin@mailservice.edu\n" +
				"3;Pokalechu Vsehovod Frodovich;22.06.1941;37222211111;vsepokale@mailservice.edu"
			def errors = service.parse(csvString)

		then:"data errors are expected, but correct contact is created"
			errors.size() == 1
			errors[0].id == "2" //id
			errors[0].fieldErrors.size() == 1 //
			errors[0].fieldErrors[0].getField() == "middleName"
			Contact.count() == 1
			Contact contact = Contact.first()
			contact.lastName == "Pokalechu"
			// other fields checks are skipped
	}

}
