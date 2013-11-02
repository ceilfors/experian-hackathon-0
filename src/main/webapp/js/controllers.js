'use strict';

/* Controllers */

angular.module('carpoolBuddyApp.controllers', []).
  controller('BuddyController', ["$scope", "cityService", function($scope, cityService) {
  	$scope.greeting = "Hellow";
  	$scope.cities = cityService.cities;
  }])
  .controller('MyCtrl2', [function() {

  }]);