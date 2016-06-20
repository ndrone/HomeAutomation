import angular from 'angular';
import 'angular-ui-router';
import homeComponent from './home.component';

let homeModule = angular.module('home', [
	'ui.router'
])
.config(/*@ngInject*/($stateProvider)=>{
	$stateProvider
		.state('home', {
			url: '/',
			template: '<home></home>',
        	ncyBreadcrumb: {
          		label: 'Home'
        	}
		});
})
.directive('home', homeComponent);

export default homeModule;