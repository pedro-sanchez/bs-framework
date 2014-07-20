function openModal(modalName) {
	$('#' + modalName).modal('show');
	toolTipStart();
	readyMessage();
	loadValidation();
	return false;
}

function closeModal(modalName) {

	$('#' + modalName).modal('hide');
	selectRowGrid();
	orderGrid();
	return false;
}

function orderGrid() {
	$('.table-header').click(function() {

		var sortDirection = "ASC";
		var fieldSort = "";
		var currentIcon = $(this).children('.iconSort').children('span').attr("class");
		
		if(currentIcon != undefined){
			$('.iconSort').children('span').attr("class", "glyphicon glyphicon-sort");
			
			if (currentIcon == 'glyphicon glyphicon-sort') {
				$(this).children('.iconSort').children('span').attr("class", "glyphicon glyphicon-chevron-down");
			} else if (currentIcon == 'glyphicon glyphicon-chevron-up') {
				$(this).children('.iconSort').children('span').attr("class", "glyphicon glyphicon-chevron-down");
			} else {
				$(this).children('.iconSort').children('span').attr("class", "glyphicon glyphicon-chevron-up");
				sortDirection= "DESC";
			}
			fieldSort =$(this).children('.sortField').text();
			
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
	$('tr').click(function() {

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
		oldRow = $('td', this);

		oldId = $(this).index();

		entityId = $(this).closest('tr').children('td:first').text();
		oldBorderColor = $('td', this).css('border-color');

		$('td', this).css({
			'background-color' : '#5bc0de'
		});
		$('td', this).css({
			'border-color' : '#5bc0de'
		});

		setSelectedID(entityId);

	});

	$('tr').dblclick(function() {
		$(".btn-edit").click();
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
	$(".btn").tooltip({
		container : 'body'
	});
	$(".form-control").tooltip();
	$(".selectpicker").tooltip();
	$(".outputText").tooltip();

}

function selectPicker() {
	$('.selectpicker').selectpicker();
}

$(document).ready(function($) {
	selectRowGrid();
	orderGrid();
	readyMessage();
	toolTipStart();
	selectPicker();
	return false;
});

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
						$('[id*="' + yesLinkID + '"]').click();
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
	var currentPage = $('[id*="txtCurrentPage"]').val();
	setCurrentIndex(currentPage - 1);
	return false;
}

function forcePagination() {
	$('[id*="btnNextPage"]').click();
	return false;
}

function forceSelectID() {
	$('[id*="btnUpdateSelectedID"]').click();
	return false;
}

function setCurrentIndex(currentIndex) {
	$('[id*="txtCurrentIndex"]').val(currentIndex);
	forcePagination();
	return false;
}

function setSelectedID(selectedId) {
	$('[id*="selectedID"]').val(selectedId);
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
	$.bootstrapGrowl(msg, {
	    align: 'center',
	    offset: {
	        from: "top",
	        amount: 100
	    },
	    width: 'auto'
	});
}

function message(msg, type){
	$.bootstrapGrowl(msg, {
	    type: type,
	    align: 'center',
	    offset: {
	        from: "top",
	        amount: 100
	    },
	    width: 'auto'
	});
}