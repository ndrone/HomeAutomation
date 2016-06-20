import FooterModule from './footer'
import FooterController from './footer.controller';
import FooterComponent from './footer.component';
import FooterTemplate from './footer.html';
import TemplateTestUtil from '../test/templateTestUtil';

describe('Footer', ()=> {
	let $rootScope,
	makeController;

  beforeEach(angular.mock.module(FooterModule.name));
  beforeEach(angular.mock.inject((_$rootScope_)=> {
  	$rootScope = _$rootScope_;
    makeController = ()=> {
    	return new FooterController();
    };
  }));

  describe('Module', ()=> {
  	// test things about the component module
  	// checking to see if it registers certain things and what not
  	// test for best practices with naming too
  	// test for routing
  });

  describe('Controller', ()=> {
  	// test your controller here

  	it('should have a name property name', ()=> { // erase me if you remove this.name from the controller
  		let controller = makeController();

      expect(controller).to.have.property('name');
    });

    it('should have a name property year', ()=> { // erase me if you remove this.name from the controller
    	let controller = makeController();

      expect(controller).to.have.property('year');
    });
  });

  describe('Template', ()=> {
  	var compiledTemplate,
  	expectedDivString = "© Copyright " + new Date().getFullYear() + " All Rights Reserved.";

    beforeEach(()=> {
    	compiledTemplate = new TemplateTestUtil().renderTemplate(FooterTemplate, 'footer', {year: makeController().year});
    });

    it('rendered the current year', ()=> {
    	expect(compiledTemplate.find('div p').html()).eq(expectedDivString)
    });
  });



  describe('Component', ()=> {
  	// test the component/directive itself
  	let component = FooterComponent();

    it('should use the right template', ()=> {
    	expect(component.template).to.equal(FooterTemplate);
    });

    it('should use controllerAs', ()=> {
    	expect(component).to.have.property('controllerAs');
    });

    it('should use the right controller', ()=> {
    	expect(component.controller).to.equal(FooterController);
    });
  });
});






