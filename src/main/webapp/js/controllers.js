'use strict';

/* Controllers */

angular.module('carpoolBuddyApp.controllers', []).
  controller('BuddyController', ["$scope", "cityService", "buddyService", function($scope, cityService, buddyService) {
  	$scope.cities = cityService.query({});
  	$scope.searchBuddy = function(from, to) {
  		buddyService.query({from: from, to: to}, function(result) {
  			$scope.buddies = result
  		});
  	};
  }]);