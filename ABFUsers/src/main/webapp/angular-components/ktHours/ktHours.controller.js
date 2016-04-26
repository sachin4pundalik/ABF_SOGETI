webappApp.controller('ktHoursCtrl', [ '$scope', '$http', 'ktHoursService',
		ktHoursCtrl_fn ]);

function ktHoursCtrl_fn($scope, $http, ktHoursService) {
	$scope.hours = [ {
		id : 1,
		projectType : 'On-site',
		role : 'P5',
		price : 65,
		w1 : 5,
		w2 : 5,
		w3 : 5,
		w4 : 5,
		w5 : 5,
		w6 : 5,
		w7 : 5,
		w8 : 5,
		w9 : 5,
		w10 : 5,
		w11 : 5,
		w12 : 5
	} ];

	$scope.totalCost = totalcost();

	$scope.addNewRow = function() {
		var newRow = {
			id : ($scope.hours.length) + 1,
			projectType : $scope.newprojectType,
			role : $scope.newRole,
			price : validateNumber($scope.newPrice),
			w1 : validateNumber($scope.newW1),
			w2 : validateNumber($scope.newW2),
			w3 : validateNumber($scope.newW3),
			w4 : validateNumber($scope.newW4),
			w5 : validateNumber($scope.newW5),
			w6 : validateNumber($scope.newW6),
			w7 : validateNumber($scope.newW7),
			w8 : validateNumber($scope.newW8),
			w9 : validateNumber($scope.newW9),
			w10 : validateNumber($scope.newW10),
			w11 : validateNumber($scope.newW11),
			w12 : validateNumber($scope.newW12)
		};
		$scope.hours.push(newRow);
		reset();
		console.log("json" + JSON.stringify($scope.hours));
		$scope.totalCost = totalcost();
	};
	
	function reset() {

		$scope.flag = false;
		$scope.id = '';
		$scope.newprojectType = '';
		$scope.newRole = '';
		$scope.newPrice = '';
		$scope.newW1 = '';
		$scope.newW2 = '';
		$scope.newW3 = '';
		$scope.newW4 = '';
		$scope.newW5 = '';
		$scope.newW6 = '';
		$scope.newW7 = '';
		$scope.newW8 = '';
		$scope.newW9 = '';
		$scope.newW10 = '';
		$scope.newW11 = '';
		$scope.newW12 = '';
	}

	$scope.remove = function(id) {
		$scope.hours.pop(id);
		$scope.totalCost = totalcost();
	};
	
	function validateNumber( value ){
		if(angular.isNumber(value)){
			return value;
		}else {
			return 0;
		}
	}
	$scope.saveHours = function(id, projectType, role, price,
			w1, w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12) {
		for (var i = 0; i < $scope.hours.length; i++) {
			if ($scope.hours[i].id == id) {
				$scope.hours[i].projectType = projectType,
						$scope.hours[i].role = role,
						$scope.hours[i].price = validateNumber(price),
						$scope.hours[i].w1 = validateNumber(w1),
						$scope.hours[i].w2 = validateNumber(w2),
						$scope.hours[i].w3 = validateNumber(w3),
						$scope.hours[i].w4 = validateNumber(w4),
						$scope.hours[i].w5 = validateNumber(w5),
						$scope.hours[i].w6 = validateNumber(w6),
						$scope.hours[i].w7 = validateNumber(w7),
						$scope.hours[i].w8 = validateNumber(w8),
						$scope.hours[i].w9 = validateNumber(w9),
						$scope.hours[i].w10 = validateNumber(w10),
						$scope.hours[i].w11 = validateNumber(w11),
						$scope.hours[i].w12 = validateNumber(w12);
			}
		}
		$scope.totalCost = totalcost();
		ktHoursService.setktHours($scope.hours);
	};

	function totalcost() {
		var total = 0;
		for (var i = 0; i < $scope.hours.length; i++) {
			total = total
					+ (($scope.hours[i].price) * ($scope.hours[i].w1
							+ $scope.hours[i].w2
							+ $scope.hours[i].w3
							+ $scope.hours[i].w4
							+ $scope.hours[i].w5
							+ $scope.hours[i].w6
							+ $scope.hours[i].w7
							+ $scope.hours[i].w8
							+ $scope.hours[i].w9
							+ $scope.hours[i].w10
							+ $scope.hours[i].w11 + $scope.hours[i].w12));

			console.log(" inside total" + total);

		}
		console.log(" outside total" + total);
		return total;
	}

	// add new row test
	$scope.addNewAssignmentTest = function() {
		$scope.flag = true;

	};
}