'use strict';

// Declare app level module which depends on filters, and services
angular.module('carpoolBuddyApp', [
    'ngRoute',
    'carpoolBuddyApp.filters',
    'carpoolBuddyApp.services',
    'carpoolBuddyApp.directives',
    'carpoolBuddyApp.controllers',
    'facebook'
    ]).
config(['$routeProvider', 'FacebookProvider', function($routeProvider, FacebookProvider) {
    $routeProvider.when('/', {templateUrl: 'partials/home.html', controller: 'HomeController'});
    $routeProvider.when('/buddies/new', {templateUrl: 'partials/buddies/new.html', controller: 'NewBuddyController'});
    $routeProvider.otherwise({redirectTo: '/'});
    FacebookProvider.init('197032780481530');
}]);