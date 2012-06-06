/**
 * @Author:ShaoJinzhou
 * @Time:2012-6-5
 */

/**
 * 系统导航栏
 * @param:navData []:导航文字
 * @param:className {}:样式
 * @param:panelId {}:父容器ID
 * */
function Navigator(navData, className, panelId){
	var ol = $('<ol/>').attr({
		'class': 'nav_ol'
	});
	
	$.each(navData, function(index, value){
		$('<li/>').attr({
			'class': index == 0 ? 'atv_nav_ol_li' : 'atv_nav_ol_li_hover'
		}).text(value).appendTo(ol);
	});
	
	ol.appendTo($(panelId));
}