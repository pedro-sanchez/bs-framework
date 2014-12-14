function openWindow(uri) {
	jQuery('[id*="selectedPage"]').val(uri);
	jQuery('[id*="btnUpdateStage"]').click();
	return false;
}

function onStageComplete(data) {
	if (data.status == "success") {
		fullReady();
		return false;
	}
}

function openModalJSF(uri) {
	openModalJSFStack(uri, null, null);
	return false;
}

function openModalJSFStack(uri, data, update) {
	jQuery('[id*="modalUrl"]').val(uri);
	jQuery('[id*="data"]').val(data);
	jQuery('[id*="field"]').val(update);
	jQuery('[id*="btnOpenModal"]').click();
	return false;
}

function onModalOpenComplete(data) {
	if (data.status == "success") {
		openModal();
		return false;
	}
}

function openModal() {
	jQuery('#myModal').modal('show');
	fullReady();
	return false;
}

function onSaveComplete(data) {
	onSuccessCloseModal(data);
}


function onSuccessCloseModal(data) {
	if (data.status == "success") {
		closeModalJSF();
		return false;
	}
}

function onSavePlusComplete(data) {
	if (data.status == "success") {
		fullReady();
		return false;
	}
}

function closeModalJSF(){
	closeModal();
	jQuery('[id*="btnCloseModal"]').click();
}

function onModalCloseComplete(data) {
	if (data.status == "success") {
		//closeModal();
		return false;
	}
}

function closeModal() {
	jQuery('#myModal').modal('hide');
	fullReady();
	return false;
}

function confirmDialog(title, message, yes, yesTitle, no, noTitle, yesLinkID,
		noLinkID, id) {

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
						
						if(yesLinkID == 'ConfirmLinkYes'){
							yesLinkID = yesLinkID + id;
						}
							
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
						if(noLinkID == 'ConfirmLinkNo'){
							noLinkID = noLinkID + id;
						}
						alert(noLinkID);
					}
				}
			},

		}
	});

	basicReady();
	return false;
}

function onCompletConfirm(data) {
	if (data.status == "success") {
		fullReady();
		return false;
	}
}
