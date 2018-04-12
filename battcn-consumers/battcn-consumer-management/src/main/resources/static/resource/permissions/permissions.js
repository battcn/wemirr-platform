function clickAll(obj, id) {
	$("input[_key='menu_1_" + id + "']").each(function() {
		$(this).prop("checked", obj.checked);
	});
	$("input[_key='menu_1_1_" + id + "']").each(function() {
		$(this).prop("checked", obj.checked);
	});
};
function clickByMenuId(obj, id, pid) {
	$("input[_key_2='menu_1_1_" + id + "']").each(function() {
		$(this).prop("checked", obj.checked);
	});
	if (obj.checked == true) {
		$("input[_key='menu_" + pid + "']").each(function() {
			$(this).prop("checked", obj.checked);
		});
	}
};
function clickButton(obj, id, pid) {
	if (obj.checked == true) {
		$("input[_key_1='menu_1_1_" + id + "']").each(function() {
			$(this).prop("checked", obj.checked);
		});
		$("input[_key='menu_" + pid + "']").each(function() {
			$(this).prop("checked", obj.checked);
		});
	}
}