webappApp.service('MasterDataService', ['$http', MasterDataService_fn]);

function MasterDataService_fn($http){
	
	var service = this;
	
	service.fetchAll= function(url){
		return $http.get(url);
	}
	
	service.fetch = function(url, value){
		return $http.get(url+value);
	}
	
	service.update = function(url, value){
		return $http.put(url, value);
	}
	
	service.save= function(url, value){
		return $http.post(url, value);
	};
	
	service.remove = function(url, value){
		return $http({method: 'delete', url:(url+value)});
	}
	
}

