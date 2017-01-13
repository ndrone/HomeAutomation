import HeaderModule from './header'
import HeaderController from './header.controller';
import HeaderComponent from './header.component';
import HeaderTemplate from './header.html';

describe('Header', () => {
    let $rootScope,
    makeController;

    beforeEach(angular.mock.module(HeaderModule.name));
    beforeEach(angular.mock.inject((_$rootScope_) => {
            $rootScope = _$rootScope_;
    makeController = () =>
    {
        return new HeaderController();
    };
    }));

    describe('Module', () => {
        // test things about the component module
        // checking to see if it registers certain things and what not
        // test for best practices with naming too
        // test for routing
    });

    describe('Controller', () => {
        // test your controller here

        it('should have a name property',() =>
        { // erase me if you remove this.name from the controller
            let controller = makeController();

            expect(controller).to.have.property('name');
        });
    });

    describe('Template', () => {
        // test the template
    });


    describe('Component', () => {
        // test the component/directive itself
        let component = HeaderComponent();

        it('should use the right template', () => {
            expect(component.template
        ).to.equal(HeaderTemplate);
        });

        it('should use controllerAs', () => {
            expect(component).to.have.property('controllerAs');
        });

        it('should use the right controller', () => {
            expect(component.controller
        ).to.equal(HeaderController);
        });
    });
});






