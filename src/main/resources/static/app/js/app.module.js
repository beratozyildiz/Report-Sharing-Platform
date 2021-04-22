var app = angular.module("ReportApp", [
    'ngResource',
    'ngRoute',
    'report'
]);


app.config(function ($routeProvider, $locationProvider) {
    $routeProvider
        .when('/administrator/:id/account-list', {
            template: '<account-list></account-list>'
        })
        .when('/administrator/:id/report-list', {
            template: '<report-list></report-list>'
        })
        .when('/administrator/:id/log-list', {
            template: '<log-list></log-list>'
        })
        .when('/administrator/:id/report/:id/history-list', {
            template: '<history-list></history-list>'
        })
        .when('/evaluator/:id/report/:id/history-list', {
            template: '<e-history-list></e-history-list>'
        })
        .when('/administrator/:id/create-account', {
            template: '<create-account></create-account>'
        })
        .when('/administrator/:id/account/:id', {
            template: '<account></account>'
        })
        .when('/evaluator/:id/create-report', {
            template: '<create-report></create-report>'
        })
        .when('/login',{
            template:'<login></login>'
        })
        .when('/evaluator/:id/report/:id/create-link-properties',{
            template:'<create-link-properties></create-link-properties>'
        })
        .when('/evaluator/:id/report-list',{
            template:'<self-report-list></self-report-list>'
        })
        .when('/pr/:publicLink',{
            template:'<report-link></report-link>'
        })
        .when('/ur/:publicLink',{
            template:'<upload-response></upload-response>'
        })
        .when('/pr/:publicLink/download/:name',{
            template:'<download-file></download-file>'
        })
        .when('/evaluator/home',{
            template:'<e-home></e-home>'
        })
        .when('/administrator/home',{
            template:'<a-home></a-home>'
        })
        .when('/validation/:publicLink',{
            template:'<validation></validation>'
        })
        .otherwise({
            redirectTo: "/"
        });

    $locationProvider.html5Mode(true);
})
    .directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function(scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;

                element.bind('change', function(){
                    scope.$apply(function(){
                        modelSetter(scope, element[0].files);
                    });
                });
            }
        };
    }])