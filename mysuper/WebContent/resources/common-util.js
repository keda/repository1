/**
 * 设置Iframe自动适应高度
 * 适合浏览器IE/FF/CHROME
 * @param:string iframeId
 */
function autoSetIframeHight(iframeId){
	var ifrm = document.getElementById(iframeId);
	var ifrmPage = document.frames ? document.frames[iframeId].document : ifrm.contentDocument;
	
	if(ifrm != null && ifrmPage != null)
		ifrm.height = ifrmPage.body.scrollHeight;
	
}