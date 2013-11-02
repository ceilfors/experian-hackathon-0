'use strict';

/* Directives */


angular.module('carpoolBuddyApp.directives', []).
  directive('appVersion', ['version', function(version) {
    return function(scope, elm, attrs) {
      elm.text(version);
    };
  }]);
