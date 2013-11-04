'use strict';

var services = angular.module('carpoolBuddyApp.services', ['ngResource']).
factory('cityService', function($resource) {
    return $resource('/rest/cities', {}, {}); 
}).
factory('buddyService', function($resource) {
    return $resource('/rest/buddies', {}, {});
});