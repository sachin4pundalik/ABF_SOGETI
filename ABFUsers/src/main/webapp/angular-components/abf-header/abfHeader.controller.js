webappApp.controller('abfHeaderCtrl', abfHeader_fn);

function abfHeader_fn($scope){
	
	$scope.active={ 
				amHours: 'active',
				ktHours: '',
				fixedHours:'',
				report: ''
			};
	
	
	$scope.setHeaderActive= function(headerItem){
		console.log('Header Item : ' + headerItem);
		
		angular.forEach($scope.active, function(value, key) {
				if(key === headerItem){
				  $scope.active[key] ='active';
			  }else{
				  $scope.active[key] ='';
			  }
			}, $scope.active);
		
	};
}
