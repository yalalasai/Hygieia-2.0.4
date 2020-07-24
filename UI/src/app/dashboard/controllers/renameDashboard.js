/**
 * Controller for the modal popup when creating
 * a new dashboard on the startup page
 */
(function () {
    'use strict';

    angular
        .module(HygieiaConfig.module)
        .controller('RenameDashboardController', RenameDashboardController);

    RenameDashboardController.$inject = ['$modalInstance', 'dashboardData', 'dashboardId','dashboardName'];
    function RenameDashboardController($modalInstance, dashboardData, dashboardId,dashboardName) {

        var ctrl = this;

        // public variables
        ctrl.dashboardTitle = dashboardName;

        // public methods
        ctrl.submit = submit;


        function submit(form) {

            form.dashboardTitle.$setValidity('createError', true);

            if (form.$valid) {
                dashboardData
                    .rename(dashboardId, document.cdf.dashboardTitle.value)
                    .success(function (data) {
                        $modalInstance.close();
                })
                    .error(function(data){
                    form.dashboardTitle.$setValidity('createError', false);
                });
            }
            else
            {
                form.dashboardTitle.$setValidity('createError', false);
            }

        }
        
    }
})();