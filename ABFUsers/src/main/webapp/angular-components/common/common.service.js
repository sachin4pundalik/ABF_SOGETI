webappApp.service('dataSetService', dataSetService_Fn);

function dataSetService_Fn(){
	
	this.currContract = {};
	this.loggedInUser = {};
	this.startContract = moment();
	this.endContract = moment().add(24, 'month');
	
	//Master data set
	this.rTypes = [{key:"Onshore", value:"1"}, {key:"Offshore",value:"2"}];
	this.bands=[];
	this.roles=[];
	this.grades=[];
	this.stayTypes=[];
	this.blines=[];
	this.skills=[];
	this.userRoles=[];
	this.fixedHours=[];
	this.statuses=[];
	this.onshoreprices=[];
	this.offshoreprices=[];
	
	//Toaster alerts
	this.errorAlertOptions= {title: "title", body:"Error Text",positionClass:'toast-top-full-width',progressBar: true};
	this.infoAlertOptions= {title: "title", body:"Info Text",positionClass:'toast-top-right',progressBar: true};
	this.warningAlertOptions= {title: "title", body:"Warning Text",positionClass:'toast-top-right',progressBar: true};
	this.successAlertOptions= {title: "title", body:"Success Text",positionClass:'toast-bottom-full-width',progressBar: true};
	
	
}