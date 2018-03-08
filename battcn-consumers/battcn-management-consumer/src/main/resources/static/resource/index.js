$(function() {
	// 首页框架布局代码
	initUI();
	var $index = $("div#content-main div.index");
	var o = layer.load();
	var href = $index.attr("data-id");
	$index.html('<div class="loading">加载中,请稍等...<div>');
	$index.loadUrl(href, 'get', {}, function(data) {
		layer.close(o);
		$index.find("div.loading").remove();
	});
});
function queryParams(params) {
	var pageSize = params.limit;
	var sort = params.sort;
	var offset = params.offset;
	var order = params.order;
	var pageNum = offset / pageSize + 1;
	return {
		pageSize : pageSize,
		pageNum : pageNum,
		sort : sort,
		order : order
	}
}
function responseHandler(res) {
	return {
		"rows" : res.list,
		"total" : res.total
	}
}
function onloadurl() {
	$("[data-url]").each(function() {
		var tb = $(this);
		tb.html(CommnUtil.loadingImg());
		tb.load(rootPath + tb.attr("data-url"));
	});
}