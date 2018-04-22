package com.samsonan.contacts.dto;

import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

import com.fasterxml.jackson.annotation.JsonPropertyOrder

/**
 * DTO class for importing contacts from CSV
 *
 */
@JsonPropertyOrder([ "id", "fullName", "birthDate", "phoneNumber", "email" ])
class ContactDto {

	String id;
	String fullName
	String birthDate
	String email
	String phoneNumber
	@Override
	String toString() {
		return "ContactDto [id=" + id + ", fullName=" + fullName + ", birthDate=" + birthDate + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
}
