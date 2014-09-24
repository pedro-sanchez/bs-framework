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
	jQuery('[id*="modalUrl"]').val(uri);
	/*jQuery('[id*="modalMB"]').val(mb);*/
	jQuery('[id*="btnUpdateModal"]').click();
	return false;
}


function onModalComplete(data) {
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

function closeModal(modalName) {
	jQuery('#myModal').modal('hide');
	fullReady();
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

	basicReady();
	return false;
}

function onCompletConfirm(data) {
	if (data.status == "success") {
		fullReady();
		return false;
	}
}
