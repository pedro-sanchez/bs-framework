
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

function orderGrid() {
	jQuery('.table-header').click(function() {

		var sortDirection = "ASC";
		var fieldSort = "";
		var currentIcon = jQuery(this).children('.iconSort').children('span').attr("class");
		
		if(currentIcon != undefined){
			jQuery('.iconSort').children('span').attr("class", "glyphicon glyphicon-sort");
			
			if (currentIcon == 'glyphicon glyphicon-sort') {
				jQuery(this).children('.iconSort').children('span').attr("class", "glyphicon glyphicon-chevron-down");
			} else if (currentIcon == 'glyphicon glyphicon-chevron-up') {
				jQuery(this).children('.iconSort').children('span').attr("class", "glyphicon glyphicon-chevron-down");
			} else {
				jQuery(this).children('.iconSort').children('span').attr("class", "glyphicon glyphicon-chevron-up");
				sortDirection= "DESC";
			}
			fieldSort =jQuery(this).children('.sortField').text();
			
			//TODO passar dados para variaveis de ordenação;
			//força buscar
			//exibir current icon de acordo com o sort;
			
		}
	});
}

function selectRowGrid() {
	var oldRow = null;
	var oldBorderColor = null;
	var oldId = null;
	var entityId = null;
	jQuery('tr').click(function() {

		if (oldRow != null) {
			if (oldId % 2 == 0) {
				oldRow.css({
					'background-color' : '#f9f9f9'
				});
			} else {
				oldRow.css({
					'background-color' : '#ffffff'
				});
			}

			oldRow.css({
				'border-color' : oldBorderColor
			});
		}
		oldRow = jQuery('td', this);

		oldId = jQuery(this).index();

		entityId = jQuery(this).closest('tr').children('td:first').text();
		oldBorderColor = jQuery('td', this).css('border-color');

		jQuery('td', this).css({
			'background-color' : '#5bc0de'
		});
		jQuery('td', this).css({
			'border-color' : '#5bc0de'
		});

		setSelectedID(entityId);

	});

	jQuery('tr').dblclick(function() {
		jQuery(".btn-edit").click();
	});
	return false;
}

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

jQuery(document).ready(function(jQuery) {
	onOpenWindow();
	return false;
});

function onOpenWindow(){
	selectRowGrid();
	orderGrid();
	readyMessage();
	toolTipStart();
	selectPicker();
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

function changePage() {
	var currentPage = jQuery('[id*="txtCurrentPage"]').val();
	setCurrentIndex(currentPage - 1);
	return false;
}

function forcePagination() {
	jQuery('[id*="btnNextPage"]').click();
	return false;
}

function forceSelectID() {
	jQuery('[id*="btnUpdateSelectedID"]').click();
	return false;
}

function setCurrentIndex(currentIndex) {
	jQuery('[id*="txtCurrentIndex"]').val(currentIndex);
	forcePagination();
	return false;
}

function setSelectedID(selectedId) {
	jQuery('[id*="selectedID"]').val(selectedId);
	forceSelectID();
	return false;
}

function onSelectedIDComplete(data) {
	if (data.status == "success") {
		toolTipStart();
		return false;
	}
}
function onPaginationComplete(data) {
	if (data.status == "success") {
		selectRowGrid();
		orderGrid();
		toolTipStart();
		return false;
	}
}

function onCompletConfirm(data) {
	if (data.status == "success") {
		selectRowGrid();
		orderGrid();
		toolTipStart();
		return false;
	}
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

function errorMessage(msg){
	message(msg, 'error');
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
