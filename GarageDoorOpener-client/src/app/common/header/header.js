import angular from 'angular';
import 'angular-ui-router';
import headerComponent from './header.component';

let headerModule = angular.module('appHeader', [
	'ui.router'
])
.directive('appHeader', headerComponent);

export default headerModule;