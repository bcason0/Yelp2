angular.module('starter.controllers', [])

.controller('VenueCtrl', function($scope, $http){
  $http.get('http://192.168.1.172:8080/business').
    success(function(response) {
        $scope.theVenues = response;
        console.log(response);
        });
})
