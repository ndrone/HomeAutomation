class TemplateTestUtil {
    constructor(){
        // i construct stuff
    }
    renderTemplate(templateToRender, controllerViewModelName, options) {
        let compiledTemplate, compile, scope;

        inject(($compile, $rootScope)=> {
            compile = $compile;
            scope = $rootScope;
        });

        if (scope !== undefined && compile !== undefined) {
            scope[controllerViewModelName] = options;
            compiledTemplate = compile(templateToRender)(scope);
            scope.$digest();
        } else {
            throw new Error("scope and compile must be defined")
        }

        return compiledTemplate;
    }
}
export default TemplateTestUtil;