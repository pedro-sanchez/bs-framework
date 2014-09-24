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


function onSaveComplete(data) {
	if (data.status == "success") {
		fullReady();
		return false;
	}
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
	loadValidation();
	return false;
}

