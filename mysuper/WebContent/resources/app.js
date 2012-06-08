/**
 * @Author:ShaoJinzhou
 * @Time:2012-6-5
 */

/**
 * 系统导航栏
 * @param:navData []:导航文字
 * @param:className {}:样式
 * @param:panelId {}:父容器ID
 * @param:function :点击事件函数
 * */
function Navigator(navData, className, panelId, onClickFn){
	
	selected = function(){
		var had = $('ol[class="nav_ol"] li[class="atv_nav_ol_li"]');
		if(had && had != this) $(had).removeClass('atv_nav_ol_li').addClass('atv_nav_ol_li_hover'); 
		$(this).toggleClass('atv_nav_ol_li atv_nav_ol_li_hover');
		
		if(onClickFn != null && typeof(onClickFn)=='function') {
			onClickFn();
		}
	};
	
	var ol = $('<ol/>').attr({
		'class': 'nav_ol'
	});
	
	$.each(navData, function(index, value){
		$('<li/>').attr({
			'class': index == 0 ? 'atv_nav_ol_li' : 'atv_nav_ol_li_hover'
		}).text(value).bind('click', selected).appendTo(ol);
	});
	
	ol.appendTo($(panelId));
}