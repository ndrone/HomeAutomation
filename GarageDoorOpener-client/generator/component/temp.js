import angular from 'angular';
import uiRouter from 'angular-ui-router';
import <%= name %>Component from './<%= name %>.component';

let <%= name %>Module = angular.module('<%= name %>', [
  'ui.router'
])
.config(/*@ngInject*/($stateProvider)=>{
  $stateProvider
    .state('<%= name %>', {
      url: '/<%= name %>',
      template: '<<%= hyphenName %>></<%= hyphenName %>>'
    });
})

.directive('<%= name %>', <%= name %>Component);

export default <%= name %>Module;
