import $ from 'jquery';
import _ from 'lodash';
import 'bootstrap';
import angular from 'angular';
import 'angular-ui-router';
import 'angular-breadcrumb';
import 'restangular';
import Rest from './common/rest/rest.factory';
import Common from './common/common';
import Components from './components/components';
import AppComponent from './app.component';
import {logConfig} from './log.config';

let appModule = angular.module('app', [
	'ui.router',
  'ncy-angular-breadcrumb',
	'ui.bootstrap',
  'restangular',
	Common.name,
	Components.name
])
.directive('app', AppComponent)
.config(logConfig)
.config(/*@ngInject*/function ($uiViewScrollProvider, $urlRouterProvider) {
  $uiViewScrollProvider.useAnchorScroll();
  $urlRouterProvider.otherwise("/");
})
.config(/*@ngInject*/($breadcrumbProvider)=>{
  $breadcrumbProvider.setOptions({
    prefixStateName: 'home',
    template: 'bootstrap3'
  });
})
.factory('Rest', Rest.restFactory)
.run(/*@ngInject*/function ($rootScope, $state, $stateParams, $anchorScroll, $log) {
  $rootScope.$state = $state;
  $rootScope.$stateParams = $stateParams;
  $rootScope.$on('$stateChangeStart', (event, toState, toParams, fromState, fromParams) => {
    $anchorScroll('page');
  });
  $rootScope.$on('$stateChangeError', (event, toState, toParams, fromState, fromParams, error) => {
    $log.error('$stateChangeError:', event, toState, toParams, fromState, fromParams, error);
    if (error) {
      throw error;
    }
  });
  $rootScope.$on('$stateChangeSuccess', (event, to, toParams, from, fromParams) => {

    //For convenience set where you came from where you are and where you are going
    $rootScope.previousState = from.name;
    $rootScope.currentState = to.name;
    $rootScope.fromParams = fromParams;
    $log.debug('$stateChangeSuccess - name:', to.name);
  });
  $rootScope.$on('$stateNotFound', (event, unfoundState, fromState, fromParams)=> {
    $log.warn('$stateNotFound', {
      event: event,
      unfoundState: unfoundState,
      fromState: fromState,
      fromParams: fromParams
    });
  });
})
.run(/*@ngInject*/($rootScope, $state) => {
    $rootScope.isActive = function(stateName) {
      return $state.includes(stateName);
    };

    $rootScope.getLastStepLabel = function() {
      return 'Angular-Breadcrumb';
    };
});


export default appModule;
