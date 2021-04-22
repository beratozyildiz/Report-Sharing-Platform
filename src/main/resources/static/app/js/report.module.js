var report = angular.module("report", [
    'ngResource'
]);
report
    .factory('AccountApi', ['$resource', function ($resource) {
    return $resource('/account/:id', {id: '@id'},
        {
            list: {
                method: "GET",
                url: "/account/list",
                isArray: true
            },
            delete: {
                method: 'DELETE',
                url: '/account/:id'
            },
            save: {
                method: 'POST',
                url: '/account'
            },
            current: {
                method:'GET',
                url:'/account'
            }
        });

}])
    .factory('ReportApi', ['$resource', function ($resource) {
        return $resource('/report/:id', {id: '@id'},
            {
                list: {
                    method: "GET",
                    url: "/report/list",
                    isArray: true
                },
                delete: {
                    method: 'DELETE',
                    url: '/report/:id'
                },
                save: {
                    method: 'POST',
                    url: '/report'
                },
                listByActor: {
                    method: "GET",
                    url: "/report/list/actor/:id",
                    isArray: true
                },
                switchStatus: {
                    method: 'POST',
                    url: '/report/switch-status/:id'
                }

            });
    }])

    .factory('LogApi', ['$resource', function ($resource) {
        return $resource('/log/:id', {id: '@id'},
            {
                save: {
                    method: "POST",
                    url: "/log",
                },
                list: {
                    method: "GET",
                    url: "/log/list",
                    isArray: true
                }
            });
    }])
    .factory('LinkPropertiesApi', ['$resource', function ($resource) {
        return $resource('/link/:id', {id: '@id'},
            {
                save: {
                    method: "POST",
                    url: "/link",
                },
                delete: {
                    method: 'DELETE',
                    url: '/link/:id'
                }


            });
    }])

    .factory('PublicLinkApi', ['$resource', function ($resource) {
        return $resource('/report/public-Link/:publicLink', {publicLink: '@publicLink'},
            {
                getReportByPublicLink: {
                    method: "GET",
                    url: "/report/public-link/:publicLink"
                }
            });
    }])
    .factory('QRApi', ['$resource', function ($resource) {
        return $resource('/qr/generate/:publicLink', {publicLink: '@publicLink'},
            {
                generateQR: {
                    method: "GET",
                    url: "/qr/generate/:publicLink"
                }
            });
    }])
    .factory('DownloadApi', ['$resource', function ($resource) {
        return $resource('/download/file/:name', {name: '@name'},
            {
                download: {
                    method: "GET",
                    url: "/download/file/:name",
                    responseType: 'arraybuffer',
                    transformResponse: function (data, headers) {
                        var response = {};
                        response.data = data;
                        response.headers = headers();
                        return response;
                    }
                },
                qr: {
                    method: "GET",
                    url: "/download/qr/:name",
                    responseType: 'arraybuffer',
                    transformResponse: function (data, headers) {
                        var response = {};
                        response.data = data;
                        response.headers = headers();
                        return response;
                    }
                }
            });
    }])

    .factory('ReferenceApi', ['$resource', function ($resource) {
        return $resource('/document/:id', {id: '@id'},
            {
                list: {
                    method: "GET",
                    url: "/document/list",
                    isArray:true
                },
                delete: {
                    method: 'DELETE',
                    url: '/document/:id'
                },

            });
    }])










