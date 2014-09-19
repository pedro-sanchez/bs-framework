
function toolTipStart() {
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
		selectRowGrid();
		orderGrid();
		toolTipStart();
		showMessage();
		return false;
	}
}

