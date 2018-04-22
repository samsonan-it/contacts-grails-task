// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//
//= require jquery
//= require_tree .
//= require_self
//= require bootstrap

if (typeof jQuery !== 'undefined') {
	(function($) {
		$(document).ajaxStart(function(){
			$('#spinner').fadeIn();
		}).ajaxStop(function(){
			$('#spinner').fadeOut();
		});
	})(jQuery);
}

$(function() {

	  $("#browse-script-file").on('click', function(){
	    $("#file-input").trigger('click');
	  });

	  $("#file-input").on('change', function(){
	    var fileName = document.getElementById("file-input").files[0].name;
	    console.log('file selected:' + fileName);
	    $("#uploaded-file-name").val(fileName);
	    $("#upload-script-btn").prop('disabled', false);
	  });

	  if ($("#last-updated-info").length == 0) {
	    $('#download-script-btn').addClass('disabled');
	  }

});
