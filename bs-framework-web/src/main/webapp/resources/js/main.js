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
		pickTime: false,
		format: 'dd/MM/yyyy',
        language: 'pt-br'
    });
}

function datePickerTime() {
	jQuery('.datePickerTime').datetimepicker({
		pickDate: true,
		pickTime: true,
		format: 'dd/MM/yyyy hh:mm:ss',
        language: 'pt-br'
    });
}

function updateUserOptions(){
	jQuery('[id*="btnUserInfo"]').click();
	$('.popover-user-a').popover('destroy');
	return false;
}


function onUpdateUserOptionsComplete(data) {
	if (data.status == "success") {
		userOptions();
		$('.popover-user-a').popover('show');
		return false;
	}
}


function onLogin(data) {
	if (data.status == "success") {
		if(jQuery('[id*="firstAcess"]').val() == "true"){
			openModalJSF('changePassword.xhtml');
		}
		return false;
	}
}


function userOptions(){
	var contentId = $('.popover-user-a').attr('data-content-id');
	$('.popover-user-a').popover({
		html : true,
		content : function() {
			return $('#' + contentId).html();
		}
	});
}


function fullReady(){
	readyTable();
	basicReady();
	return false;
}


function basicReady(){
	userOptions();
	readyMessage();
	toolTipStart();
	selectPicker();
	datePicker();
	loadValidation();
	return false;
}

$(function () {
	fullReady();
});


