/**
 * Created by ryan.zhu on 21/01/2017.
 */

var mainApp = angular.module("cuaTest", []);

mainApp.controller('wordCryptographyController', ['$scope', '$http', function ($scope, $http) {

    $scope.encodeString = function () {
        var encodeUrl = 'http://localhost:8080/encode';
        var inputStrRep = {
            wordsString: $scope.encodeInput
        };

        $http.post(encodeUrl, inputStrRep)
            .then(function (response) {
                console.log("response is: ", response);

                $scope.encodeOutput = response.data.wordsString;
            },
            function (response) {
                console.log("error response is:", response);
                alert("Error occur.");
            });
    }

    $scope.decodeString = function () {
        var decodeUrl = 'http://localhost:8080/decode';
        var inputStrRep = {
            wordsString: $scope.decodeInput
        };

        $http.post(decodeUrl, inputStrRep)
            .then(function (response) {
                console.log("response is: ", response);
                $scope.decodeOutput = response.data.wordsString;
            },
            function (response) {
                console.log("error response is:", response);
                alert("Error occur.");
            });
    }
}]);
