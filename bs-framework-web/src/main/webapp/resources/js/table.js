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

function forcePagination() {
	jQuery('[id*="btnNextPage"]').click();
	return false;
}

function changePage() {
	var currentPage = jQuery('[id*="txtCurrentPage"]').val();
	setCurrentIndex(currentPage - 1);
	return false;
}

function setCurrentIndex(currentIndex) {
	jQuery('[id*="txtCurrentIndex"]').val(currentIndex);
	forcePagination();
	return false;
}

function onPaginationComplete(data) {
	if (data.status == "success") {
		selectRowGrid();
		orderGrid();
		toolTipStart();
		return false;
	}
}

function forceSelectID() {
	jQuery('[id*="btnUpdateSelectedID"]').click();
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