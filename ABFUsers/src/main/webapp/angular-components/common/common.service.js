webappApp.service('DataSetService', [ 'masterDataService', 'toastr',
		'ABF_CONSTANTS', DataSetService_Fn ]);

function DataSetService_Fn(masterDataService, toastr, ABF_CONSTANTS) {

	var self = this;
	self.currContract = {};
	
	self.startContract;
	self.endContract;

	// Master data set
	self.rTypes = [];
	self.bands = [];
	self.roles = [];
	self.grades = [];
	self.stayTypes = [];
	self.blines = [];
	self.skills = [];
	self.userRoles = [];
	self.fixedHours = [];
	self.statuses = [];
	self.onshoreprices = [];
	self.offshoreprices = [];

	self.offshorePrice = null;
	self.onshorePrice = null;

	self.getResourceTypes = function() {
		return self.rTypes;
	};

	self.getBands = function() {
		return self.bands;
	};

	self.getRoles = function() {
		return self.roles;
	};

	self.getGrades = function() {
		return self.grades;
	};

	self.getStayTypes = function() {
		return self.stayTypes;
	};

	self.getBusinessLines = function() {
		return self.blines;
	};

	self.getSkills = function() {
		return self.skills;
	};

	self.getUserRoles = function() {
		return self.userRoles;
	};

	self.getFixedCosts = function() {
		return self.fixedHours;
	};

	self.getStatuses = function() {
		return self.statuses;
	};

	self.getOnshorePrices = function() {
		return self.onshoreprices;
	};

	self.getOffshorePrices = function() {
		return self.offshoreprices;
	};

	self.getOffshorePrice = function() {
		return self.offshorePrice;
	};

	self.getOnshorePrice = function() {
		return self.onshorePrice;
	};

	self.fetchResourceTypes = function() {

		if (angular.isArray(self.rTypes) && self.rTypes.length == 0) {
			// Resource Types
			let url = './resourcetype/all';
			masterDataService.fetchAll(url).then(
					function(response) {
						if (angular.equals(response.data.status,
								ABF_CONSTANTS.SUCCESS)) {
							self.rTypes = response.data.successResponse;
						}

					});
		}
	};

	self.fetchBands = function() {
		// Bands
		if (angular.isArray(self.bands) && self.bands.length == 0) {
			let url = './band/all';
			masterDataService.fetchAll(url).then(
					function(response) {
						if (angular.equals(response.data.status,
								ABF_CONSTANTS.SUCCESS)) {
							self.bands = response.data.successResponse;
						}
					});
		}

	};

	self.fetchBusinessLines = function(url) {

		// Business Lines /resourceType/{resourceTypeId}/skill/{skillId}
		return masterDataService.fetch(url, '').then(function(response) {
			if (angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)) {
				self.blines = response.data.successResponse;
				return self.blines;
			}
		});
	};

	self.fetchGrades = function() {

		if (angular.isArray(self.grades) && self.grades.length == 0) {
			// Grades
			let url = './grade/all';
			masterDataService.fetchAll(url).then(
					function(response) {
						if (angular.equals(response.data.status,
								ABF_CONSTANTS.SUCCESS)) {
							self.grades = response.data.successResponse;
						}
					});
		}
	};

	self.fetchAllOffshorePrices = function() {
		// Offshore prices
		url = './offshorePrice/all';
		masterDataService.fetchAll(url).then(function(response) {
			if (angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)) {
				self.offshoreprices = response.data.successResponse;
			}
		});

	};

	self.fetchAllOnshorePrices = function() {
		// Onshore prices
		url = './onshorePrice/all';
		masterDataService.fetchAll(url).then(function(response) {
			if (angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)) {
				self.onshoreprices = response.data.successResponse;

			}
		});
	};

	self.fetchOnshorePrice = function(params) {

		url = './onshorePrice/all';
		masterDataService.fetch(url, params).then(function(response) {
			if (angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)) {
				self.onshorePrice = response.data.successResponse;

			}
		});
	};

	self.fetchOffshorePrice = function(params) {

		url = './offshorePrice/all';
		masterDataService.fetchAll(url).then(function(response) {
			if (angular.equals(response.data.status, ABF_CONSTANTS.SUCCESS)) {
				self.offshorePrice = response.data.successResponse;
			}
		});
	};

	self.fetchRoles = function() {
		if (angular.isArray(self.roles) && self.roles.length == 0) {
			// Roles
			url = './role/all'; // './json/roles.json';
			masterDataService.fetchAll(url).then(
					function(response) {
						if (angular.equals(response.data.status,
								ABF_CONSTANTS.SUCCESS)) {
							self.roles = response.data.successResponse;
						}
					});
		}

	};

	self.fetchSkills = function() {
		if (angular.isArray(self.skills) && self.skills.length == 0) {
			// Skills
			url = './skill/all';
			masterDataService.fetchAll(url).then(
					function(response) {
						if (angular.equals(response.data.status,
								ABF_CONSTANTS.SUCCESS)) {
							self.skills = response.data.successResponse;
						}
					});
		}

	};

	self.fetchStayTypes = function() {
		if (angular.isArray(self.stayTypes) && self.stayTypes.length == 0) {
			//Stay types
			url = './stayType/all';
			masterDataService.fetchAll(url).then(function(response) {
				self.stayTypes = response.data.successResponse;
			});
		}

	};

}