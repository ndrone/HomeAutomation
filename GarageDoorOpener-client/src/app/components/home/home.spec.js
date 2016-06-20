// import 'restangular';
// import Rest from '../../common/rest/rest.factory';
// import HomeModule from './home'
// import HomeController from './home.controller';
// import HomeComponent from './home.component';
// import HomeTemplate from './home.html';

// describe('Home', ()=>{
// 	let rootScope, makeController;

// 	beforeEach(() => {
//       window.module(HomeModule.name);
//     });
//   	beforeEach(inject(($injector) => {
// 		rootScope = $injector.get("$rootScope");
//     let timeout = $injector.get("$timeout");
//     let Restangular = $injector.get("Restangular");
// 		makeController = () => {
// 			return new HomeController(new Rest(Restangular, rootScope, console), timeout);
// 		};
// 	}));

// 	describe('Module', ()=>{
// 		// test things about the component module
// 		// checking to see if it registers certain things and what not
// 		// test for best practices with naming too
// 		// test for routing
// 	});

// 	describe('Controller', ()=>{
// 		// test your controller here
// 		let controller;
// 		beforeEach(() => {
// 			controller = makeController();
// 		});

//     it('checking properties and default values.', ()=> {
//       expect(controller).to.have.property('name');
//       expect(controller.name).to.equal('home');

//       expect(controller).to.have.property('keycode');
//       expect(controller.keycode).to.equal('');

//       expect(controller).to.have.property('Rest');
//       expect(controller.Rest).to.be.not.null;

//       expect(controller).to.have.property('timeout');
//       expect(controller.timeout).to.be.not.null;

//       expect(controller).to.have.property('timeoutPromise');
//       expect(controller.timeoutPromise).to.be.null;
//     });

//     it('checking update function', () => {
//     	expect(controller.keycode).to.equal('');
//     	controller.update(1);
//     	expect(controller.keycode).to.equal('1');
//     });

//     it('checking clear function', () => {
//     	expect(controller.keycode).to.equal('');
//     	controller.update(1);
//     	expect(controller.keycode).to.equal('1');
//     	controller.clear();
//     	expect(controller.keycode).to.equal('');
//     });

//     it('checking submit with empty keycode', () => {
//       controller.clear();
//     	controller.submit();
//     	expect(controller.keycode).to.equal('ERROR');
//     	// expect(homeServiceStub.calledOnce).to.be.false;
//     });

//     // it('checking submit with keycode equal 1', () => {
//     // 	homeServiceStub = sinon.sandbox.stub(homeService, 'submit', () => {
//     // 		return true;
//     // 	});
//     //   controller.update('1');
//     // 	controller.submit();
//     // 	expect(controller.keycode).to.equal('1');
//     // 	expect(homeServiceStub.calledOnce).to.be.true;
//     // });
// 	});

// 	describe('Template', ()=>{
// 		// test the template
// 		// use Regexes to test that you are using the right bindings {{  }}
// 	});


// 	describe('Component', ()=>{
// 			// test the component/directive itself
// 			let component = HomeComponent();

// 			it('should use the right template',()=>{
// 				expect(component.template).to.equal(HomeTemplate);
// 			});

// 			it('should use controllerAs', ()=>{
// 				expect(component).to.have.property('controllerAs');
// 			});

// 			it('should use the right controller', ()=>{
// 				expect(component.controller).to.equal(HomeController);
// 			});
// 	});
// });
