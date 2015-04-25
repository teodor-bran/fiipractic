var selectedId = 0;
$(document).ready(function() {
	$("#listPersons").click(function() {
		loadPersons();
	});

	$("#deletePerson").click(function(id) {
		deletePerson(selectedId);
	});
});

/**
 * This function loads the existing persons from the database. Sends a REST
 * request (using ajax) to /person/list request handler.
 */
var loadPersons = function() {
	$.ajax({
		url : window.location.href + '/person/list',
		dataType : 'json',
		cache : false,
		success : function(data) {
			//clear the existing rows
			$("#persons tr:first").siblings("tr").remove();
			// update the HTML by adding a table>row for each person
			for (var i = 0; i < data.length; i++) {
				var person = data[i];
				var $row = "<tr id=" + person.id + ">" + "<td>" + person.id
						+ "</td>" + "<td>" + person.firstName + "</td>"
						+ "<td>" + person.lastName + "</td>" + "<td>"
						+ person.cnp + "</td>" + "</tr>";
				$("#persons tr:last").after($row);
				addRowHandlers("persons");
			}
		}
	});
};

/**
 * This function deletes an existing person from the database. Sends a REST
 * request (using ajax) to /person/delete/{id} request handler.
 */
var deletePerson = function(id) {
	$.ajax({
		url : window.location.href + '/person/delete/' + id,
		dataType : 'json',
		type : 'PUT',
		success : function(result) {
			if (result.Status === "Success") {
				$("#persons").find("#" + id).remove();
			} else {
				alert(result.Message);
			}
		}
	});
};


function addRowHandlers(tableId) {
	var table = document.getElementById(tableId);
	var rows = table.getElementsByTagName("tr");
	for (i = 1; i < rows.length; i++) {
		var currentRow = table.rows[i];
		var createClickHandler = function(row) {
			return function() {
				var id = row.id;
				row.classList.add("selected")
				$(row).addClass("selected").siblings("tr").removeClass("selected");
				selectedId = id;
			};
		};

		currentRow.onclick = createClickHandler(currentRow);
	}
}

