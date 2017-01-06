import controller from './home.controller';
import template from './home.html';
import './home.less';

let homeComponent = function () {
    return {
        template,
        controller,
        restrict: 'E',
        controllerAs: 'vm',
        scope: {},
        bindToController: true
    };
};

export default homeComponent;