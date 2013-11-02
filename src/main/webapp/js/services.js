'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
var services = angular.module('carpoolBuddyApp.services', []).
value('version', '0.1').
factory('cityService', function() {
    return {
        cities: [
            "Kuala Lumpur",
            "Kajang",
            "Klang",
            "Subang Jaya",
            "George Town",
            "Ipoh",
            "Petaling Jaya",
            "Selayang",
            "Johor Bahru",
            "Ampang Jaya",
            "Kota Kinabalu",
            "Sungai Petani",
            "Kuantan",
            "Alor Setar",
            "Tawau",
            "Sandakan",
            "Kuala Terengganu",
            "Kota Bharu",
            "Malacca City",
            "Seremban"
        ]
    };
}).
factory('buddyService', function() {
    return {
        buddies: [
            "Buddy 1",
            "Buddy 2",
            "Buddy 3"
        ]
    }
})