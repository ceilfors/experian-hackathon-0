'use strict';

angular.module('carpoolBuddyApp.filters', []).
filter('interpolate', ['version', function(version) {
    return function(text) {
        return String(text).replace(/\%VERSION\%/mg, version);
    }
}]);
