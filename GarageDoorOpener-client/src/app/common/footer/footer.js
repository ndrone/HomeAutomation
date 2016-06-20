import angular from 'angular';
import 'angular-ui-router';
import footerComponent from './footer.component';

let footerModule = angular.module('footer', [
	'ui.router'
])
.directive('appFooter', footerComponent);

export default footerModule;