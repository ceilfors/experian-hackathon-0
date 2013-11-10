'use strict';

angular.module('carpoolBuddyApp.controllers', ['ui.bootstrap']).
controller('IndexController', [
    "$scope", "Facebook", "$log", "$location", "$resource",
    function($scope, Facebook, $log, $location, $resource) {

        $scope.user = {};
        $scope.facebookUser = {}
        $scope.logged = false;
        $scope.$watch(function() {
            return Facebook.isReady();
        }, function(newVal) {
            $scope.facebookReady = true;
            // Grab user if already logged in to facebook
            Facebook.getLoginStatus(function(response) {
                if (response.status === 'connected') {
                    $scope.logged = true;
                    $scope.me(); 
                }
            });
        });

        $scope.IntentLogin = function() {
            Facebook.getLoginStatus(function(response) {
                if (response.status === 'connected') {
                    $scope.logged = true;
                    $scope.me(); 
                } else {    
                    $scope.login();
                }
            });
        };
        $scope.login = function() {
            Facebook.login(function(response) {
                if (response.status === 'connected') {
                    $scope.logged = true;
                    $scope.me();           
                }
            });
        };
        $scope.me = function() {
            Facebook.api('/me', function(response) {
                $scope.$apply(function() {
                    $scope.facebookUser = response;
                    // Checks if the connected user has been registered before, if no go to registration page
                    $resource("/rest/buddies/fbid/:fbid").get({fbid: $scope.facebookUser.id}, function(user) {
                        // KLUDGE: inconsistencies in structure of from & to
                        $scope.user = user;
                        $scope.user.from = user.from.name;
                        $scope.user.to = user.to.name;
                    }, function(response) {
                        $scope.user.name = $scope.facebookUser.name;
                        $scope.user.fbid = $scope.facebookUser.id;
                        if (response.status === 404) {
                            if ($location.path() !== "/buddies/new") {
                                $location.path("/buddies/new");
                            }
                        } else {
                            $log.error("Failed to get buddy with the specified facebook id");
                        }
                    });
                   
                });
            });

        };
        $scope.logout = function() {
            Facebook.logout(function() {
                $scope.$apply(function() {
                    $scope.facebookUser = {};
                    $scope.logged = false;  
                });
            });
        }
    }]).
controller('HomeController', [
    "$scope", "cityService", "buddyService",
    function($scope, cityService, buddyService) {
        $scope.cities = cityService.query({});
        $scope.searchBuddy = function(from, to) {
            buddyService.query({from: from, to: to}, function(buddies) {
                $scope.buddies = buddies.filter(function(buddy) {
                    return buddy.id !== $scope.user.id;
                })
            });
        };
    }]).
controller('NewBuddyController', [
    "$scope", "$location", "buddyService", "Facebook", "$log",
    function($scope, $location, buddyService, Facebook, $log) {
        $scope.save = function(user) {
            buddyService.save(user, function() {
                $location.path("/");
            });    
        }
    }]);