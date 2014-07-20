function attachValidationMessage(input){
    var valid =true;    
    if($(input).attr('required') == 'required'){
        valid = false;
        if($(input).val() == '' || (($(input).prop("tagName")=='SELECT') && $(input).val() == 'NONE')){
			input.setCustomValidity($(input).attr('data-requiredMessage'));
		}else if($(input).is(':invalid') && (input.validationMessage == $(input).attr('data-requiredMessage'))){			
			valid = true;
		}		
	}
    
    if(valid){
		input.setCustomValidity('');
	}
	return false;
}

function loadValidation(){

	$(document).bind('input', function(e){ 
		attachValidationMessage(e.target);
		return false;
	});

	$("select").bind('change', function(e){ 
		attachValidationMessage(e.target);
		return false;
	});

	$("input").ready(function() {
		$("input").trigger("input");	
		return false;
	});

	$("select").ready(function() {
		$("select").trigger("change");	
		return false;
	});

	
}
