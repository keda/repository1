/**
 *系统框架 
 */

Ext.onReady(function(){
	
	var store = Ext.create('Ext.data.TreeStore', {
		root: {
			expanded: true
		},
		proxy: {
			type: 'ajax',
			url: Ext.get('ctxPath').getValue()+'/resources/appjs/tree-data.json'
		}
	});
	
	var treePanel = Ext.create('Ext.tree.Panel', {
		id: 'tree-panel',
		title: '',
		region: 'north',
		split: true,
		height: 360,
		minSize: 150,
		rootVisible: false,
		autoScroll: true,
		store: store
	});
	
	treePanel.getSelectionModel().on('select', function(selModel, node){
		if(node.get('leaf')){
			Ext.Message.alert('Msg', node.attributes.url);
			var url = '<iframe id="cntIfram" src="'+node.attributes.url+'" frameborder=0 width=100% height=100% scrolling="no"></iframe>';
			Ext.getCmp('content-panel').body.update(url);
		}
	});
	
	var detailPanel = {
		id: 'details-panel',
		title: 'Detail',
		region: 'center',
		bodyStyle: 'padding-bottom:15px;background:#eee;',
		autoScroll: true,
		html: '<p class="details-info">When you select a layout from the tree, additional details will display here.</p>'
	};
	
	Ext.create('Ext.Viewport', {
		layout: 'border',
		title: 'Ext layout border',
		items: [{
			xtype: 'box',
			id: 'header',
			region: 'north',
			html: '<h1> Ext.Layout.Browser</h1>',
			height: 30
		},{
			layout: 'border',
			id: 'layout-browser',
			region: 'west',
			border: false,
			split: true,
			margins: '2 0 5 5',
			width: 275,
			minSize: 100,
			maxSize: 500,
			items: [
			    treePanel,detailPanel
			]
		},{
			id: 'content-panel',
			region: 'center',
			layout: 'card',
			margins: '2 5 5 0',
			border: false,
			html: '<iframe src="home.jsp" frameborder=0 width=100% height=100% scrolling="no"></iframe>'
		}],
		renderTo: Ext.getBody()
	});
});

