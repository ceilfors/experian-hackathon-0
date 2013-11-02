'use strict';

/* Controllers */

angular.module('carpoolBuddyApp.controllers', []).
  controller('BuddyController', ["$scope", "cityService", "buddyService", function($scope, cityService, buddyService) {
  	$scope.greeting = "Hellow";
  	$scope.cities = cityService.cities;
  	$scope.searchBuddy = function() {
  		$scope.buddies = buddyService.buddies;
  	};
  }]);
  // .controller('MyCtrl2', [function() {

  // }]);