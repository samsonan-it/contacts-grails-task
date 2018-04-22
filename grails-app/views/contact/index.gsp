
<%@ page import="com.samsonan.contacts.Contact"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'contact.label', default: 'Contact')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>

	<div class="container">

		<div id="list-contact" class="content scaffold-list" role="main">
			<h1>
				<g:message code="default.list.label" args="[entityName]" />
			</h1>

			<div class="list-header-row">
				<div class="pull-left input-group form-group list-add-buttons">
					<g:link class="btn btn-large btn-default create" action="create">Add Contact</g:link>
				</div>
			</div>

			<g:uploadForm action="doUpload" class="form-inline">
				<div class="form-group upload-file-form-group">

					<input type="file" name="file" id="file-input" class="file"
						style="display: none">
					<div class="input-group col-xs-12">
						<input type="text" class="form-control" id="uploaded-file-name"
							disabled placeholder="Upload contacts from file"> <span
							class="input-group-btn">
							<button class="browse btn btn-primary" id="browse-script-file"
								type="button">
								<i class="glyphicon glyphicon-search"></i> Browse
							</button>
						</span>
					</div>

				</div>
				<g:submitButton name="doUpload" value="Upload"
					class="browse btn btn-primary" disabled="true"
					id="upload-script-btn" />

			</g:uploadForm>

			<g:if test="${flash.message}">
			    <br/>
				<div class="alert alert-success message" role="status">
					${flash.message}
				</div>
			</g:if>

			<g:if test="${flash.errors}">
			    <br/>
				<div class="alert alert-danger message" role="status">
					${flash.errors}
				</div>
			</g:if>

			<table class="table table-striped">
				<thead>
					<tr>

						<g:sortableColumn property="firstName"
							title="${message(code: 'contact.firstName.label', default: 'First Name')}" />

						<g:sortableColumn property="lastName"
							title="${message(code: 'contact.lastName.label', default: 'Last Name')}" />

						<g:sortableColumn property="middleName"
							title="${message(code: 'contact.middleName.label', default: 'Middle Name')}" />

						<g:sortableColumn property="age"
							title="${message(code: 'contact.age.label', default: 'Age')}" />

						<th></th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${contactInstanceList}" status="i"
						var="contactInstance">
						<tr>

							<td>
								${fieldValue(bean: contactInstance, field: "firstName")}
							</td>

							<td>
								${fieldValue(bean: contactInstance, field: "lastName")}
							</td>

							<td>
								${fieldValue(bean: contactInstance, field: "middleName")}
							</td>

							<td>
								${fieldValue(bean: contactInstance, field: "age")}
							</td>

							<td><g:form
									url="[resource:contactInstance, action:'delete']"
									method="DELETE">
									<g:link class="btn btn-default btn-sm" action="show"
										id="${contactInstance.id}">Details</g:link>
									<g:link class="btn btn-default btn-sm" action="edit"
										resource="${contactInstance}">
										<g:message code="default.button.edit.label" default="Edit" />
									</g:link>
									<g:actionSubmit class="btn btn-danger btn-sm" action="delete"
										value="${message(code: 'default.button.delete.label', default: 'Delete')}"
										onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
								</g:form></td>

						</tr>
					</g:each>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
