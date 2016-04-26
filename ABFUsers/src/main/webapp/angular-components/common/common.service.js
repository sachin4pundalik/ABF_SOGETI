webappApp.service('dataSetService', dataSetService_Fn);

function dataSetService_Fn(){
	
	this.currContract = {};
	this.startContract = moment();
	this.endContract = moment().add(24, 'month');
	
	this.resourceType = [{key:"Onshore", value:"1"}, {key:"Offshore",value:"2"}];
	this.projectTypes = [{projType:"Onshore", id:"1"}, {projType:"Offshore",id:"2"}];
	
	
}