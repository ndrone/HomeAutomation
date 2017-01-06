class Rest {
    constructor(Restangular, $rootScope, $log) {
        Object.assign(this, {Restangular, $rootScope, $log});

        return Restangular.withConfig((RestangularConfigurer) = > {
                RestangularConfigurer.setBaseUrl(window.location.href.substring(0, window.location.href));
    })
        ;
    }

    static restFactory(Restangular, $rootScope, $log) {
        return new Rest(Restangular, $rootScope, $log);
    }
}

Rest.restFactory.$inject = ['Restangular', '$rootScope', '$log'];

export default Rest;
