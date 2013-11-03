'use strict';

/* Controllers */
var watchFacebook = function($scope, Facebook, delegate) {
    $scope.$watch(
        function() {
            return Facebook.isReady();
        }, function(newVal) {
            if (newVal) {
                delegate();
            }
        }
    );
}
angular.module('carpoolBuddyApp.controllers', []).
controller('HomeController', [
        "$scope", "Facebook", "$log", "$location", "$resource",
        function($scope, Facebook, $log, $location, $resource) {

    $scope.user = {};
    $scope.logged = false;

    watchFacebook($scope, Facebook, function(){
        $scope.facebookReady = true;
    });
    $scope.IntentLogin = function() {
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
                Facebook.api('/me', function(response) {
                    $resource("/rest/buddies/fbid/:fbid").get({fbid: "1234"}, function(user) {
                        $scope.logged = true;
                        $scope.me();
                    }, function(response) {
                        if (response.status == 404) {
                            $location.path("/buddies/new");
                        } else {
                            $log.error("Failed to get buddy");
                        }
                    });
                });            
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
        "$scope", "$location", "buddyService", "Facebook", "$log", "$http",
        function($scope, $location, buddyService, Facebook, $log, $http) {
    $scope.user = {};
    $scope.logged = false;
    watchFacebook($scope, Facebook, function() {
        $scope.facebookReady = true;
        $scope.IntentLogin();
    });
    $scope.IntentLogin = function() {
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
                $scope.user.fbid = response.id;
                $scope.user.name = response.name;
            });
        });
    };

    $scope.save = function(user) {
        $log.log("save");
        buddyService.save(user, function() {
            $location.path("/");
        });    
    }
}]);