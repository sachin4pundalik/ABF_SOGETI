webappApp.controller('MasterSkillCtrl', [ '$scope', '$location',
                                         'toastr','DataSetService', 'MasterDataService', 'ABF_CONSTANTS', MasterSkillCtrl_Fn ]);

function MasterSkillCtrl_Fn($scope, $location,toastr, DataSetService, MasterDataService, ABF_CONSTANTS){
	
	$scope.skills = DataSetService.skills;
	
	$scope.skill= {
			skillId:'',
			skillName:''
	};
	$scope.currentView='';
	
	$scope.getskills = function (){
		var url = './skill/all'; //'./masterdata/skills';
		
		MasterDataService.fetchAll(url)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.skills = DataSetService.skills = response.data.successResponse;
				toastr.info("skills updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.SKILLS);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
			
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.newSkill = function(){
		$scope.currentView ="new";
		$scope.remove();
	}
	
	$scope.update=function(){
		MasterDataService.update('./skill/update', $scope.skill)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.getskills()
				$scope.goBack();
				toastr.info("Skills updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.SKILLS);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	
	$scope.save= function(){
		
		MasterDataService.save('./skill/create', $scope.skill)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.getskills();
				$scope.goBack();
				toastr.info("skills updated from server.", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.SKILLS);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
			
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.edit= function(skillId){
		$scope.currentView = 'edit';
		$scope.getskill(skillId);
	};
	
	$scope.getskill = function ( skillId ){
		MasterDataService.fetch('./skill/find/', skillId)
		.then(function(response){
			if(angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)){
				$scope.skill=response.data.successResponse;
				toastr.info("skill is loaded!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.SKILLS);
			}else{
				toastr.error(response.data.failureResponse, ABF_CONSTANTS.FAILURE_HEADER);
			}
			
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	}
	
	$scope.remove=function(skillId){
		MasterDataService.remove('./skill/delete/', skillId)
		.then(function(response){
			$scope.getskills();
			toastr.success("Skill is deleted!!", ABF_CONSTANTS.MASTER_DATA+ ABF_CONSTANTS.SKILLS);
		}, function(error){
			toastr.error("Unable to perform operation!!", ABF_CONSTANTS.FAILURE_HEADER);
			console.log(JSON.stringify(error));
		});
	};
	
	$scope.goBack = function(){
		
		$scope.currentView ='';
		$scope.reset();
	}
	$scope.reset=function(){
		$scope.skill= {
				skillId:'',
				skillName:''
		};
	};
	$scope.getskills();
}