import template from './footer.html';
import controller from './footer.controller';
import './footer.less';

let footerComponent = function(){
	return {
		template,
		controller,
		restrict: 'E',
		controllerAs: 'footer',
		scope: {},
		bindToController: true
	};
};

export default footerComponent;