function readyMessage() {	
	jQuery('.messagesUL li').show(function() {
		showMessage();
	});

	return false;
}


function showMessage() {	
	if(jQuery('.messagesUL').text() != ''){
		if(jQuery('.messagesUL li').hasClass('success')){
			successMessage(jQuery('.messagesUL').text());
		}
		else if(jQuery('.messagesUL li').hasClass('error')){
			errorMessage(jQuery('.messagesUL').text());
		}
		else if(jQuery('.messagesUL li').hasClass('warning')){
			warningMessage(jQuery('.messagesUL').text());
		}
		
    	jQuery('.messagesUL').text('');
	}

	return false;
}

function errorMessage(msg){
	message(msg, 'danger');
}

function successMessage(msg){
	message(msg, 'success');
}

function infoMessage(msg){
	message(msg, 'info');
}

function warningMessage(msg){
	jQuery.bootstrapGrowl(msg, {
	    align: 'center',
	    offset: {
	        from: "top",
	        amount: 100
	    },
	    width: 'auto'
	});
}

function message(msg, type){
	jQuery.bootstrapGrowl(msg, {
	    type: type,
	    align: 'center',
	    offset: {
	        from: "top",
	        amount: 100
	    },
	    width: 'auto'
	});
}

function attachValidationMessage(input){
    var valid =true;    
    if(jQuery(input).attr('required') == 'required'){
        valid = false;
        if(jQuery(input).val() == '' || ((jQuery(input).prop("tagName")=='SELECT') && jQuery(input).val() == 'NONE')){
			input.setCustomValidity(jQuery(input).attr('data-requiredMessage'));
		}else if(jQuery(input).is(':invalid') && (input.validationMessage == jQuery(input).attr('data-requiredMessage'))){			
			valid = true;
		}		
	}
    
    if(valid){
		input.setCustomValidity('');
	}
	return false;
}

function loadValidation(){

	jQuery(document).bind('input', function(e){ 
		attachValidationMessage(e.target);
		return false;
	});

	jQuery("select").bind('change', function(e){ 
		attachValidationMessage(e.target);
		return false;
	});

	jQuery("input").ready(function() {
		jQuery("input").trigger("input");	
		return false;
	});

	jQuery("select").ready(function() {
		jQuery("select").trigger("change");	
		return false;
	});	
}