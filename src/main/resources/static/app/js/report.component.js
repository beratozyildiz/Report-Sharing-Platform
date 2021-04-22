var report = angular.module("report");
report

    .component("accountList", {
        templateUrl: "/app/template/administrator/account-list.html",
        controller: function ($scope, AccountApi) {

            $scope.delete = function (account) {
                AccountApi.delete({id: account.id}, function () {
                    $scope.init();
                });
            };
            $scope.init = function () {
                AccountApi.current(function (result) {
                    $scope.actor = result;
                });

                AccountApi.list(function (result) {
                    $scope.accounts = result;

                });
            };
            $scope.init();
        }
    })
    .component("reportList", {
        templateUrl: "/app/template/administrator/report-list.html",
        controller: function ($scope, ReportApi, $location, AccountApi) {
            $scope.gotoLink = function (report) {
                if (report.linkProperties.statusOfThePublicLink) {
                    $scope.report = report;
                    $location.path("/pr/" + report.publicLink);
                } else
                    alert("Error");
            };
            $scope.delete = function (report) {
                ReportApi.delete({id: report.id}, function () {
                    $scope.init();
                });
            };
            $scope.switchStatus = function (report) {
                ReportApi.switchStatus({id: report.id}, function () {
                    $scope.init();
                })
            };
            $scope.init = function () {
                AccountApi.current(function (result) {
                    $scope.actor = result;
                });

                ReportApi.list(function (result) {
                    $scope.reports = result;
                });
            };
            $scope.init();
        }
    })
    .component("logList", {
        templateUrl: "/app/template/administrator/log-list.html",
        controller: function ($scope, LogApi) {

            $scope.init = function () {

                LogApi.list(function (result) {
                    $scope.logs = result;
                });
            };
            $scope.init();
        }
    })
    .component("historyList", {
        templateUrl: "/app/template/administrator/history-list.html",
        controller: function ($scope, ReportApi, $routeParams) {
            $scope.init = function () {
                ReportApi.get({id: $routeParams.id}, function (result) {
                    $scope.report = result;
                    $scope.historyList = $scope.report.history;
                });
            };
            $scope.init();
        }
    })
    .component("eHistoryList", {
        templateUrl: "/app/template/evaluator/e-history-list.html",
        controller: function ($scope, ReportApi, $routeParams) {
            $scope.init = function () {
                ReportApi.get({id: $routeParams.id}, function (result) {
                    $scope.report = result;
                    $scope.historyList = $scope.report.history;
                });
            };
            $scope.init();
        }
    })
    .component("createAccount", {
        templateUrl: "/app/template/administrator/create-account.html",
        controller: function ($scope, AccountApi) {
            $scope.init = function () {
            };
            $scope.save = function (account) {
                var Id = function () {
                    return '_' + Math.random().toString(36).substr(2, 9)
                };
                account.id = Id();
                AccountApi.save(account), function (result) {
                    $scope.account = result;
                };
            };
            $scope.init();
        }
    })
    .component("account", {
        templateUrl: "/app/template/administrator/account.html",
        controller: function ($scope, $routeParams, AccountApi) {

            $scope.init = function () {
                AccountApi.get({id: $routeParams.id}, function (result) {
                    $scope.account = result;
                });
            };
            $scope.save = function (account) {
                AccountApi.save(account), function (result) {
                    $scope.account = result;

                };
            };
            $scope.init();
        }
    })
    .component("createReport", {
        templateUrl: "/app/template/evaluator/create-report.html",
        controller: function ($scope, $routeParams, AccountApi, $http, ReportApi, $location, $timeout) {
            $scope.doUploadFile = function () {
                var files = $scope.files;
                var url = "/upload";
                var data = new FormData();
                for (var key in files)
                    data.append("files", files[key]);
                var config = {
                    transformRequest: angular.identity,
                    transformResponse: angular.identity,
                    headers: {
                        'Content-Type': undefined
                    }
                };
                $http.post(url, data, config).then(function (response) {
                    $scope.reference = JSON.parse(response.data);
                    $scope.save();
                });
            };
            $scope.save = function () {
                var id = function () {
                    return '_' + Math.random().toString(36).substr(2, 9)
                };
                report.id = id();
                report.date = new Date();
                report.actor = $scope.account;
                report.document = $scope.reference;
                ReportApi.save(report), function (result) {
                    $scope.report = result;
                };
                $timeout(function () {
                    $location.path("/evaluator/" + $scope.actor.id + "/report/" + report.id + "/create-link-properties");
                }, 150);

            };


            $scope.init = function () {
                AccountApi.get({id: $routeParams.id}, function (result) {
                    $scope.account = result;
                });
                AccountApi.current(function (result) {
                    $scope.actor = result;
                });

            };
            $scope.init();
        }
    })
    .component("createLinkProperties", {
        templateUrl: "/app/template/evaluator/create-link-properties.html",
        controller: function ($scope, $routeParams, LogApi, AccountApi, ReportApi, LinkPropertiesApi, $location, $timeout, QRApi) {
            $scope.init = function () {
                AccountApi.current(function (result) {
                    $scope.actor = result;
                });
                ReportApi.get({id: $routeParams.id}, function (result) {
                    $scope.report = result;
                });
            };

            $scope.saveLink = function (link) {
                var id = function () {
                    return '_' + Math.random().toString(36).substr(2, 9)
                };
                link.id = id();
                LinkPropertiesApi.save(link), function (result) {
                    $scope.link = result;
                };
                $scope.save($scope.report);
                $timeout(function () {
                    $location.path("/evaluator/" + $scope.actor.id + "/report-list");
                }, 150);
            };
            $scope.save = function (report) {
                report.linkProperties = $scope.link;
                var link = function () {
                    return Math.random().toString(36).substr(2, 16)
                };
                report.publicLink = link();
                QRApi.generateQR({publicLink: report.publicLink}, function () {
                });
                ReportApi.save(report), function (result) {
                    $scope.report = result;
                };
            };
            $scope.init();
        }
    })
    .component("selfReportList", {
        templateUrl: "/app/template/evaluator/self-report-list.html",
        controller: function ($scope, $routeParams, AccountApi, ReportApi, $location, $timeout) {
            $scope.gotoLink = function (report) {
                if (report.linkProperties.statusOfThePublicLink) {
                    $scope.report = report;
                    $timeout(function () {
                        $location.path("/pr/" + report.publicLink);
                    }, 150);

                } else
                    alert("Error");
            };
            $scope.delete = function (report) {
                ReportApi.delete({id: report.id}, function () {
                    $scope.init();
                });
            };
            $scope.switchStatus = function (report) {
                ReportApi.switchStatus({id: report.id}, function () {
                    $scope.init();
                });
            };
            $scope.init = function () {
                AccountApi.current(function (result) {
                    $scope.actor = result;
                });
                ReportApi.listByActor({id: $routeParams.id}, function (result) {
                    $scope.reports = result;
                });
            };
            $scope.init();
        }
    })
    .component("reportLink", {
        templateUrl: "/app/template/public/report-link.html",
        controller: function ($scope, $routeParams, $location, PublicLinkApi, DownloadApi) {
            $scope.downloadQR = function () {
                DownloadApi.qr({name: $scope.report.publicLink}, function (response) {
                    var fileName = response.headers['content-disposition'].split("=")[1].replace(/"/gi, '');
                    var fileType = response.headers['content-type'] + ';charset=utf-8';
                    var blob = new Blob([response.data], {type: fileType});
                    var objectUrl = window.URL || window.webkitURL;
                    var link = angular.element('<a/>');
                    link.css({display: 'none'});
                    link.attr({
                        href: objectUrl.createObjectURL(blob),
                        target: '_blank',
                        download: fileName
                    })
                    link[0].click();
                    link.remove();
                    objectUrl.revokeObjectURL(blob);
                }, function (error) {
                    console.error(error);
                });
            };
            $scope.gotoValidation = function () {
                $location.path("/validation/" + $scope.report.publicLink);
            };
            $scope.gotoFile = function () {
                $location.path("/pr/" + $scope.report.publicLink + "/download/" + $scope.report.document.name);
            };
            $scope.gotoResponse = function () {
                if ($scope.report.linkProperties.linkPermission === "write")
                    $location.path("/ur/" + $scope.report.publicLink);
                else
                    alert("Error");
            };
            $scope.init = function () {
                PublicLinkApi.getReportByPublicLink({publicLink: $routeParams.publicLink}, function (result) {
                    $scope.report = result;
                });
            };
            $scope.init();
        }
    })
    .component("uploadResponse", {
        templateUrl: "/app/template/public/upload-response.html",
        controller: function ($scope, PublicLinkApi, $routeParams, ReportApi, $http) {
            $scope.doUploadFile = function () {
                var files = $scope.files;
                var url = "/upload";
                var data = new FormData();
                for (var key in files)
                    data.append("files", files[key]);
                var config = {
                    transformRequest: angular.identity,
                    transformResponse: angular.identity,
                    headers: {
                        'Content-Type': undefined
                    }
                };
                $http.post(url, data, config).then(function (response) {
                    $scope.reference = JSON.parse(response.data);
                    $scope.save($scope.report);
                });
            };
            $scope.save = function (report) {
                report.document = $scope.reference;
                ReportApi.save(report), function (result) {
                    $scope.report = result;
                };
            };
            $scope.init = function () {
                PublicLinkApi.getReportByPublicLink({publicLink: $routeParams.publicLink}, function (result) {
                    $scope.report = result;
                });
            };
            $scope.init();
        }
    })
    .component("downloadFile", {
        templateUrl: "/app/template/public/download-file.html",
        controller: function ($scope, $routeParams, DownloadApi) {
            $scope.init = function () {
                DownloadApi.download({name: $routeParams.name}, function (response) {
                    var fileName = response.headers['content-disposition'].split("=")[1].replace(/"/gi, '');
                    var fileType = response.headers['content-type'] + ';charset=utf-8';
                    var blob = new Blob([response.data], {type: fileType});
                    var objectUrl = window.URL || window.webkitURL;
                    var link = angular.element('<a/>');
                    link.css({display: 'none'});
                    link.attr({
                        href: objectUrl.createObjectURL(blob),
                        target: '_blank',
                        download: fileName
                    })
                    link[0].click();
                    link.remove();
                    objectUrl.revokeObjectURL(blob);
                }, function (error) {
                    console.error(error);
                });
            };
            $scope.init();
        }
    })
    .component("login", {
        templateUrl: "/app/template/login.html",
        controller: function ($scope) {
            $scope.init = function () {

            };
            $scope.init();
        }
    })
    .component("eHome", {
        templateUrl: "/app/template/evaluator/e-home.html",
        controller: function ($scope, AccountApi) {
            $scope.init = function () {
                AccountApi.current(function (result) {
                    $scope.actor = result;
                });
            };
            $scope.init();
        }
    })

    .component("aHome", {
        templateUrl: "/app/template/administrator/a-home.html",
        controller: function ($scope, AccountApi) {
            $scope.init = function () {
                AccountApi.current(function (result) {
                    $scope.actor = result;
                });
            };
            $scope.init();
        }
    })
    .component("validation", {
        templateUrl: "/app/template/public/validation.html",
        controller: function ($scope, PublicLinkApi, $routeParams) {
            $scope.init = function () {
                PublicLinkApi.getReportByPublicLink({publicLink: $routeParams.publicLink}, function (result) {
                    $scope.report = result;
                });
            };
            $scope.init();
        }
    })

    .controller('toastrCtrl', toastrCtrl);

function toastrCtrl() {
    var vm = this;
    vm.setOptions = function () {
        toastr.options.positionClass = "toast-bottom-right";
        toastr.options.closeButton = true;
        toastr.options.showMethod = 'slideDown';
        toastr.options.hideMethod = 'slideUp';
        toastr.options.progressBar = true;
    };
    vm.setOptions();
    vm.info = function () {
        toastr.info('Account Update is successfull');
    };
    vm.warning = function () {
        toastr.warning('The Link is Only One Time Usable');
    };
    vm.info2 = function () {
        toastr.info('Status Update is successfull');
    };
    vm.accountSuccess = function () {
        toastr.success('Account is successfully created');
    };
    vm.reportSuccess = function () {
        toastr.success('Report is successfully created');
    };
    vm.uploadBack = function () {
        toastr.success('Response of the Report is successfully sended');
    };
    vm.linkSuccess = function () {
        toastr.success('Link is successfully created for Report');
    };
    vm.error = function () {
        toastr.error('Delete Operation is successfully completed.');
    };
    vm.uploaderror = function () {
        toastr.error('Please choose Microsoft Word File or PDF.');
    };
    vm.clear = function () {
        toastr.clear();
    };
}

