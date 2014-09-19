function openWindow(uri) {
	jQuery('[id*="selectedPage"]').val(uri);
	jQuery('[id*="btnUpdateStage"]').click();
	return false;
}

function onStageComplete(data) {
	if (data.status == "success") {
		onOpenWindow();
		return false;
	}
}

function onOpenWindow(){
	selectRowGrid();
	orderGrid();
	readyMessage();
	toolTipStart();
	selectPicker();
	return false;
}

function openModal(modalName) {
	jQuery('#' + modalName).modal('show');
	toolTipStart();
	readyMessage();
	loadValidation();
	return false;
}

function closeModal(modalName) {
	jQuery('#' + modalName).modal('hide');
	selectRowGrid();
	orderGrid();
	return false;
}

function confirmDialog(title, message, yes, yesTitle, no, noTitle, yesLinkID,
		noLinkID) {

	bootbox.dialog({
		message : message,
		title : title,
		buttons : {
			success : {
				label : yes,
				title : yesTitle,
				className : "btn-success",
				callback : function() {
					if (yesLinkID != '') {
						jQuery('[id*="' + yesLinkID + '"]').click();
					}
				}
			},
			danger : {
				label : no,
				title : noTitle,
				className : "btn-danger",
				callback : function() {
					if (noLinkID != '') {
						alert(noLinkID);
					}
				}
			},

		}
	});

	toolTipStart();
	return false;
}

function onCompletConfirm(data) {
	if (data.status == "success") {
		selectRowGrid();
		orderGrid();
		toolTipStart();
		return false;
	}
}
