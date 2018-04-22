<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contact.label', default: 'Contact')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
	
	  <div class="container">
	
		<div id="create-contact" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			
			<g:if test="${flash.message}">
			<div class="alert alert-success message" role="status">${flash.message}</div>
			</g:if>
			
			<g:hasErrors bean="${contactInstance}">
			<ul class="errors alert alert-danger" role="alert">
				<g:eachError bean="${contactInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			
			<g:form class="form-horizontal" url="[resource:contactInstance, action:'save']" novalidate="novalidate">
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons form-group">
				  <div class="col-sm-offset-3 col-sm-10">
				  
					<g:submitButton name="create" class="btn btn-success save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
					<g:link class="list btn btn-large btn-default" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link>
					
				  </div>
				</fieldset>
			</g:form>
		</div>
		
      </div>
		
	</body>
</html>
