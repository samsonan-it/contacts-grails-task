
<%@ page import="com.samsonan.contacts.Contact" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contact.label', default: 'Contact')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
	
		<div class="container">

			<div id="show-contact" class="form-horizontal content scaffold-show" role="main">
		
				<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				
				<g:if test="${flash.message}">
				<div class="alert alert-success message" role="status">${flash.message}</div>
				</g:if>
			
				<g:if test="${contactInstance?.firstName}">
				<div class="fieldcontain form-group">
					<span id="firstName-label" class="property-label col-sm-3"><g:message code="contact.firstName.label" default="First Name" /></span>
					<span class="property-value col-sm-9" aria-labelledby="firstName-label"><g:fieldValue bean="${contactInstance}" field="firstName"/></span>
				</div>
				</g:if>
			
			
				<g:if test="${contactInstance?.lastName}">
				<div class="fieldcontain form-group">
					<span id="lastName-label" class="property-label col-sm-3"><g:message code="contact.lastName.label" default="Last Name" /></span>
					<span class="property-value col-sm-9" aria-labelledby="lastName-label"><g:fieldValue bean="${contactInstance}" field="lastName"/></span>
				</div>
				</g:if>
			
				<g:if test="${contactInstance?.middleName}">
				<div class="fieldcontain form-group">
					<span id="middleName-label" class="property-label col-sm-3"><g:message code="contact.middleName.label" default="Middle Name" /></span>
					<span class="property-value col-sm-9" aria-labelledby="middleName-label"><g:fieldValue bean="${contactInstance}" field="middleName"/></span>
				</div>
				</g:if>
			
				<g:if test="${contactInstance?.birthDate}">
				<div class="fieldcontain form-group">
					<span id="birthDate-label" class="property-label col-sm-3"><g:message code="contact.birthDate.label" default="Birth Date" /></span>
					<span class="property-value col-sm-9" aria-labelledby="birthDate-label"><g:formatDate format="dd.MM.yyyy" date="${contactInstance?.birthDate}" /></span>
				</div>
				</g:if>
			
				<g:if test="${contactInstance?.email}">
				<div class="fieldcontain form-group">
					<span id="email-label" class="property-label col-sm-3"><g:message code="contact.email.label" default="Email" /></span>
					<span class="property-value col-sm-9" aria-labelledby="email-label"><g:fieldValue bean="${contactInstance}" field="email"/></span>
				</div>
				</g:if>
			
				<g:if test="${contactInstance?.phoneNumber}">
				<div class="fieldcontain form-group">
					<span id="phoneNumber-label" class="property-label col-sm-3"><g:message code="contact.phoneNumber.label" default="Phone Number" /></span>
					<span class="property-value col-sm-9" aria-labelledby="phoneNumber-label"><g:fieldValue bean="${contactInstance}" field="phoneNumber"/></span>
				</div>
				</g:if>
			
				<g:form url="[resource:contactInstance, action:'delete']" method="DELETE">
					<fieldset class="buttons form-group">
					<div class="col-sm-offset-3 col-sm-10">
						<g:link class="btn btn-default edit" action="edit" resource="${contactInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:link class="list btn btn-large btn-default" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link>
						<g:actionSubmit class="btn btn-danger delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</div>
					</fieldset>
				</g:form>
				
			</div>
		</div>
	</body>
</html>
