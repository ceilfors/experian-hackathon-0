'use strict';

/* Controllers */

angular.module('carpoolBuddyApp.controllers', []).
controller('HomeController', [
    "$scope", "Facebook", "$log", "$timeout",
    function($scope, Facebook, $log, $timeout) {

    $scope.user = {};
    $scope.logged = false;

    $scope.$watch(
        function() {
            return Facebook.isReady();
        }, function(newVal) {
            if (newVal) {
                $scope.facebookReady = true;
            }
        }
    );
    $scope.IntentLogin = function() {
        $log.log("IntentLogin");
        Facebook.getLoginStatus(function(response) {
            if (response.status == 'connected') {
                $scope.logged = true;
                $scope.me(); 
            } else {
                $scope.login();

            }
        });
    };
    $scope.login = function() {
        Facebook.login(function(response) {
            if (response.status == 'connected') {
                $scope.logged = true;
                $scope.me();
            }
        });
    };
    $scope.me = function() {
        Facebook.api('/me', function(response) {
            $scope.$apply(function() {
                $scope.user = response;
            });
        });
    };
    $scope.logout = function() {
        Facebook.logout(function() {
            $scope.$apply(function() {
                $scope.user   = {};
                $scope.logged = false;  
            });
        });
    }
}]).
controller('BuddyController', [
    "$scope", "cityService", "buddyService",
    function($scope, cityService, buddyService) {
        $scope.cities = cityService.query({});
        $scope.searchBuddy = function(from, to) {
            buddyService.query({from: from, to: to}, function(result) {
                $scope.buddies = result
            });
        };
}]).
controller('NewBuddyController', [
    "$scope", "buddyService", "Facebook", "$log",
    function($scope, buddyService, Facebook, $log) {
    $scope.me = function() {
        Facebook.api('/me', function(response) {
            $scope.$apply(function() {
                $scope.user = response;
            });
        });
    };
}]);