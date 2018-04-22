<%@ page import="com.samsonan.contacts.Contact" %>



<div class="form-group fieldcontain ${hasErrors(bean: contactInstance, field: 'firstName', 'error')} required">
	<label class="control-label col-sm-3" for="firstName">
		<g:message code="contact.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-6">
		<g:textField class="form-control" name="firstName" maxlength="30" required="" value="${contactInstance?.firstName}"/>
	</div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: contactInstance, field: 'lastName', 'error')} required">
	<label class="control-label col-sm-3" for="lastName">
		<g:message code="contact.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-6">
		<g:textField class="form-control" name="lastName" maxlength="30" required="" value="${contactInstance?.lastName}"/>
	</div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: contactInstance, field: 'middleName', 'error')} required">
	<label class="control-label col-sm-3" for="middleName">
		<g:message code="contact.middleName.label" default="Middle Name" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-6">
		<g:textField class="form-control" name="middleName" maxlength="30" required="" value="${contactInstance?.middleName}"/>
	</div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: contactInstance, field: 'birthDate', 'error')} required">
	<label class="control-label col-sm-3" for="birthDate">
		<g:message code="contact.birthDate.label" default="Birth Date" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-9">
		<g:datePicker name="birthDate" precision="day"  value="${contactInstance?.birthDate}"  />
	</div>

</div>

<div class="form-group fieldcontain ${hasErrors(bean: contactInstance, field: 'email', 'error')}">
	<label class="control-label col-sm-3" for="email">
		<g:message code="contact.email.label" default="Email" />
	</label>
	<div class="col-sm-6">
		<g:field class="form-control" type="email" name="email" required="false" value="${contactInstance?.email}"/>
	</div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: contactInstance, field: 'phoneNumber', 'error')}">
	<label class="control-label col-sm-3" for="phoneNumber">
		<g:message code="contact.phoneNumber.label" default="Phone Number" />
	</label>
	<div class="col-sm-6">
		<g:textField class="form-control" name="phoneNumber" required="false" value="${contactInstance?.phoneNumber}"/>
	</div>
</div>

