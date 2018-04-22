package com.samsonan.contacts;

import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

import com.fasterxml.jackson.annotation.JsonPropertyOrder

class Contact {

	String firstName
	String lastName
	String middleName
	String email
	String phoneNumber
	Date birthDate

	int getAge() {
        if (birthDate != null) {
			LocalDate birthDateTz = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            return Period.between(birthDateTz, LocalDate.now()).getYears()
        } else {
            return 0;
        }
	}
	
	static constraints = {
		firstName (blank:false, size:3..30)
		lastName (blank:false, size:3..30)
		middleName (blank:false, size:3..30)
		birthDate (blank:false, nullable:false)
		email (blank:true, nullable:true, email:true)
		phoneNumber (blank:true, nullable:true) // TODO: regexp check maybe? 
	}
	@Override
	String toString() {
		return "Contact [firstName=" + firstName + ", lastName=" + lastName	+ ", middleName=" + middleName + ", email=" + email	+ ", phoneNumber=" + phoneNumber + ", birthDate=" + birthDate + "]";
	}
}
