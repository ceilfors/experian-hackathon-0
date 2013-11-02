'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
var services = angular.module('carpoolBuddyApp.services', ['ngResource']).
factory('cityService', function($resource) {
    return $resource('/rest/cities', {}, {}); 
}).
factory('buddyService', function($resource) {
    return $resource('/rest/buddies', {}, {});
})