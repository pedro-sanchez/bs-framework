function toolTipStart() {
	jQuery(".btn").tooltip('destroy');
	jQuery(".form-control").tooltip('destroy');
	jQuery(".selectpicker").tooltip('destroy');
	jQuery(".outputText").tooltip('destroy');
		
	jQuery(".btn").tooltip({
		container : 'body'
	});
	jQuery(".form-control").tooltip();
	jQuery(".selectpicker").tooltip();
	jQuery(".outputText").tooltip();

}

function selectPicker() {
	jQuery('.selectpicker').selectpicker();
}

function datePicker() {
	jQuery('.datepicker').datetimepicker({
		pickDate: true,
		pickTime: true,
		dateFormat: 'DD/MM/YYYY HH:mm',
        language: 'pt-br'
    });
}

function fullReady(){
	readyTable();
	basicReady();
	return false;
}


function basicReady(){
	readyMessage();
	toolTipStart();
	selectPicker();
	datePicker();
	loadValidation();
	return false;
}

