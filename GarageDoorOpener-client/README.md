# Angular with Webpack

This is starter project for building scalable apps with [Angular](https://angularjs.org), [ES6](https://git.io/es6features), and [Webpack](http://webpack.github.io/) and [Gulp](http://gulpjs.com/)

**Please read this REAME in its entirety.**  It is updated frequently to address questions and issues that have come up.

**Overview**:

This repo serves as a minimal starter for those looking to get up-and-running with Angular and ES6, using [Webpack](http://webpack.github.io/) for the build process, and gulp for generating boilerplate when creating new components.

**Features**:

* The best practice in directory/file organization for Angular (allowing for infinite horizontal app scaling)
* A ready-to-go build system for working with [ES6](https://git.io/es6features)
* Tasks for generating additional boilerplate Angular components
* A full testing system in place using PhantonJS, Mocha Sinon, Chai, and Karma
* Less, Stylus, and Sass CSS preprocessing are all supported.  The default used is less but you can use any of them.
	* If you wish to alter the component generator to produce one of the other types, simply rename the less template file in the component directory to your preferred type.
* Incremental building and hot browser reloads during development leading to no downtime for the developer as they make changes

___

# Table of Contents
* Walkthrough
    * Build System
    * File Structure
    * Testing Setup
* Getting Started
    * Dependencies
    * Installing
    * Running the App
    	* Installing/Uninstalling Dependencies
    	* Using Aliases
    	* Referencing static assets
    	* NPM tasks
        * Gulp Tasks
        * Testing
		* Generating Components
	* Handling non-friendly modules
	* Using a context root
	* Optimizing build times)
	* Best Practices


# Walkthrough
## Build System
This seed uses Webpack for its build system, and gulp for a few additional tasks.

`Webpack` handles all file-related concerns:

* Transpiling from ES6 to ES5 with `Babel`
* Loading HTML files as modules
* Transpiling stylesheets and appending them to the DOM
* Bundling the app
* Loading all modules
* Watching for file changes during development and updating the bundle
* Generating cache busting URL's
* Minification, and annotating angular files
* Handling fonts and images
* Handling CSS preprocessing of SASS, Less, or Stylus
* Builds source maps
* Doing all of the above for `*.spec.js` files as well

`Gulp` is generating boilerplate:


`NPM` is used to orchestrate and execute various tasks.  You can see what they are below.

There is also a `Maven POM` file.  This is used to kick off the build when this seed is used as the client portion of a Spring Boot application generated from the KIC initializr.  It can be ignored otherwise.

**Note**
We using Angular's `strict-di` to enforce that the code is minification safe.  This will cause errors to be thrown during development against the unimified bundle if the code being used is not properly annotated for minification.  If you get these sorts of errors you are probably missing `/*@ngInject*/` comments in your code.  See the project for examples of this.

The error if you encounter it will be similar to

```
Uncaught Error: [$injector:modulerr] Failed to instantiate module app due to:
```

### Additional Configuration Options

**proxy requests to a backend server**

If you wish to proxy your requests through to a different backend server (like Spring Boot), simply add that proxy configuration in the `proxy` section of `webpack-dev.config.js`.  The defaults are shown below.  Note if you wish to use a context root that must also be specified as described in the section about using context roots.

**Base**

```
    proxy: {
      "/api/*": "http://localhost:8080"
    }
```

**Debug logging**

You can toggle debug logging on and off in `log.config.js`

**Windows/Linux/Team City specific build issues**

**NOTE** *The below remarks do not apply if you have a generated your project from initializr and are using maven to do your build*

Some windows users and TeamCity Linux Agents are having failures building `node-sass` which is failing with an `msbuild.exe` error or other error concerning an inability to build node-gyp.  This causes a fallback which attempts to download the binary from github instead which causes a proxy issue.  You have a couple options:

1. `mvn clean install`

## File Structure
We use a componentized approach. This will be the eventual standard (and particularly helpful, if using Angular's new router) as well as a great way to ensure a tasteful transition to Angular 2, when the time is ripe. Everything--or mostly everything, as we'll explore (below)--is a component. A component is a self-contained concern--may it be a feature or strictly-defined, ever-present element of the UI (such as a header, sidebar, or footer). Also characteristic of a component is that it harnesses its own stylesheets, templates, controllers, routes, services, and specs. This encapsulation allows us the comfort of isolation and structural locality. Here's how it looks:

```
client
⋅⋅app/
⋅⋅⋅⋅app.js * app entry file
⋅⋅⋅⋅app.html * app template
⋅⋅⋅⋅common/ * functionality pertinent to several components propagate into this directory
⋅⋅⋅⋅components/ * where components live
⋅⋅⋅⋅⋅⋅components.js * components entry file
⋅⋅⋅⋅⋅⋅home/ * home component
⋅⋅⋅⋅⋅⋅⋅⋅home.js * home entry file (routes, configurations, and declarations occur here)
⋅⋅⋅⋅⋅⋅⋅⋅home.component.js * home "directive"
⋅⋅⋅⋅⋅⋅⋅⋅home.controller.js * home controller
⋅⋅⋅⋅⋅⋅⋅⋅home.less * home styles (could be CSS, SCSS, or styl as well)
⋅⋅⋅⋅⋅⋅⋅⋅home.html * home template
⋅⋅⋅⋅⋅⋅⋅⋅home.spec.js * home specs (for entry, component, and controller)
```

## Testing Setup
All tests are also written in ES6. We use Webpack to take care of the logistics of getting those files to run in the various browsers, just like with our client files. This is our testing stack:

* Karma
* Webpack + Babel
* Mocha
* Chai
* Sinon

To run tests, type `npm test` or `karma start` in the terminal.  Note that to use karma start you will need to have karma-cli installed globally (see dependencies) Read more about testing below.

# Getting Started
## Dependencies
Tools needed to run this app:

* `node` and `npm`
Once you have these, install the following as globals:

`npm install -g gulp karma karma-cli webpack`

Then set save exact to prevent your versions from changing under your feet.

```
npm config set save-exact=true
```

## Installing

* `npm install` to install dependencies

## Running the App
NPM can be used to build and launch the development environment. After you have installed all dependencies, you may run the app.

#### Running in development

To run the application during development you can run the following and navigate to `http://localhost:3000`

* `npm start`

#### Debugging

Files are built incrementally and hot reloaded in your browser when they are changed.  The `bundle.js` file is hard to debug, so **make sure source maps are enabled** in your Chrome developer tools.  Once this is done you should see a webpack folder in your scripts tab that you can expand and set breakpoints in.  The folder structure of the source map files will be identical to the source files in your project.

### Installing/Uninstalling Dependencies

####  Use explicit versions in your dependencies
First of all if you havent already run this command

```
npm config set save-exact=true
```

If you don't define an explicit version npm i will install the latest.  This causes versions to potentionally change on every build.  To avoid this explicitly define a version.

Now when you install you can specify a version explitly like so:

```
npm install -S angular-locker@2.0.2
```

Or you can omit the version to use the latest, but because of the config command set previously it will not change in the future without explicity intervention.


**To install a dependency that is needed only as part of the build process (not required as a runtime dependency in the users browser):**

`npm install --save-dev [dep]`

*or the short hand*

`npm i -D [dep]`



**To install a new dependency needed at runtime (this is the most common)**

`npm install --save [dep]`

*or the short hand*

`npm i -S [dep]`

**To uninstall a dependency**

`npm uninstall --save-dev [dep]` or `npm uninstall --save [dep]` respectively.

*or the short hand*

`npm un -D [dep]` or `npm un -S [dep]` respectively.

### Using Aliases
Sometimes you find yourself referencing locations frequently in your imports.  Webpack supports the concepts of aliases which can be defined in `webpack.config.js`

Here are some we have predifined

```
    alias: {
      images: path.join(__dirname, 'src/app/images'),
      fonts: path.join(__dirname, 'src/app/fonts'),
      common: path.join(__dirname, 'src/app/common'),
      vendor: path.join(__dirname, 'src/app/vendor'),
      components: path.join(__dirname, 'src/app/components')
    }
```

This allows you to do things like `import 'images/myImage.png'` rather than `../../images/myImage.png`


### Referencing Static Assets

In order for a static asset to be picked up by webpack and moved into dist it must be referenced.  This can most easily be done in 2 ways:

* Reference the asset in your css, less, stylus, or sass file
* import the static asset (see the fonts, and images alias in the `Defining Aliases` section above.)

If you reference an asset like an image or a font from within a style file, it will automatically be resolved and included.  However if you look at kitchen.html as an example we have included the following tag.

```
<a class="pull-left" href="#"> <img class="media-object" src="images/placeholder.png"></a>
```

In this case the src tag in the html references the image.  In this case to include the resource you must import it in the module.

*kitchen.component.js*

```
import 'images/placeholder.png';
```

### NPM Tasks

All the below should use the following syntax to run `npm run [task name]`

* `prune`
	* Removes NPM modules no longer referenced by `package.json`
* `clean`
	* Removes the `dist` folder where the built files are placed.
* `test`
	* Starts Karma and runs your tests
* `prebuild`
	* runs the `clean` task, creates a dist folder and copies the favicon there.
* `build:dev`
	* runs the `prebuild` task, builds the unminified version of the bundle and places the artifacts in the dist folder.
* `build:dist`
	* Builds the minified production build and places it in the `dist` directory.
* `start`
	* This starts a dev server on port 3000.  Do this and visit `localhost:3000`. Files will be watched and the bundle will be autmatically updated to reflect any changes.  The port can be updated by altering the start task in `package.json`


### Gulp Tasks
Here's a list of available tasks:

* `component`
	* scaffolds a new Angular component. Read below for usage details.

### Testing
To run the tests, run `npm test` or `karma start`.  Note to use `karma start` you must have webpack and karma-cli installed globally.

`Karma` combined with Webpack runs all files matching `*.spec.js` inside the `app` folder. This allows us to keep test files local to the component--which keeps us in good faith with continuing to build our app modularly. The file `spec.bundle.js` is the bundle file for **all** our spec files that Karma will run.

Be sure to define your `*.spec.js` files within their corresponding component directory. You must name the spec file like so, `[name].spec.js`. If you don't want to use the `.spec.js` suffix, you must change the `regex` in `spec.bundle.js` to look for whatever file(s) you want.
`Mocha` is the testing suite and `Chai` is the assertion library (see `karma.conf.js`).

**Template Testing**

There is a TemplateTestUtil for rendering templates against your controller view model. You can see an example of how to use it in the footer.spec.js.

```
 // import the TemplateTestUtil directly in to your spec

 import TemplateTestUtil from '../test/templateTestUtil';

 // in your test beforeEach you can inject the html directive template for the component
 // you are testing, the controllerViewModelName, and the data from your controller or just fake data.

 beforeEach(()=> {
    compiledTemplate = new TemplateTestUtil().renderTemplate(FooterTemplate, 'footer', {year: makeController().year});
 });

```

### Generating Components
Following a consistent directory structure between components offers us the certainty of predictability. We can take advantage of this certainty by creating a gulp task to automate the "instantiation" of our components. The component boilerplate task generates this:

```
⋅⋅⋅⋅⋅⋅componentName/
⋅⋅⋅⋅⋅⋅⋅⋅componentName.js // entry file where all its dependencies load
⋅⋅⋅⋅⋅⋅⋅⋅componentName.component.js
⋅⋅⋅⋅⋅⋅⋅⋅componentName.controller.js
⋅⋅⋅⋅⋅⋅⋅⋅componentName.html
⋅⋅⋅⋅⋅⋅⋅⋅componentName.less // scoped to affect only its own template.  Can use other preprocessors as well
⋅⋅⋅⋅⋅⋅⋅⋅componentName.spec.js // contains passing demonstration tests
```

You may, of course, create these files manually, every time a new module is needed, but that gets quickly tedius.
To generate a component, run `gulp component --name componentName`.

The parameter following the `--name` flag is the name of the component to be created. Ensure that it is unique or it will overwrite the preexisting identically-named component.

The component will be created, by default, inside `client/app/components`. To change this, apply the `--parent` flag, followed by a path relative to `client/app/components/`.

For example, running `gulp component --name signup --parent auth` will create a `signup` component at `client/app/components/auth/signup`.

Running `gulp component --name footer --parent ../common` creates a `footer` component at `client/app/common/footer`.

Because the argument to `--name` applies to the folder name **and** the actual component name, make sure to camelcase the component names.

#### Additional Notes
If you name a component using a name that is a reserved html keyword such as header or footer (see those components for an example), then you must rename the directive to an element that is not a reserved keyword.  Taking the header component as an example the directive was renamed to `appHeader` and is then activated in the html using `<app-header></app-header>`

Also please always make sure to run `npm test` to verify that your tests pass before you commit code.  The tests that are generated are meant to be updated to be more meaningful.

## Handling non-friendly modules
Occasionaly you will run into a module that you install that will cause you issues.  One example we have been told about already is 'angular-locker'.  In this case the `package.json` points at a minified distribution which removes the ability to use certain workarounds. It also demonstrates the most common problem encounterd which is it does not export the module so webpack can properly handle it.  In cases like these try adding the path (relative to node_modules) to the minified distribution to the `deps` array in `webpack.config.js`.  This will essentially tell webpack not to parse it and add an alias to that distribution.

See `webpack.config.js` for the angular-locker example.

**Note** angular-locker has now been patched in the latest release to properly export the module and support webpack.  It still remains in the config as an example though.

## Using a context root

If your application is going to be deployed on a traditional shared application server, you will most likely be required to use a context root for your application.
e.g. `http://server.test.com/my-app` where `my-app` is the context root. This requires a few changes in your client side code to support properly.

####Client Module

**src/app/index.tmpl.html**

*Uncomment the following line*

```
<base href="/context-root/">
```

**webpack-dev.config.js**

```
    proxy: {
      "context-root/api/*": "http://localhost:8080"
    }
```


####Server Module
**src/main/resources/application.yml**

```
server:
	context-path: "/context-root"
```

## Optimizing build times

The best way to optimize build times both dev and production is by not including large 3rd party libraries in your app bundle, but rather pushing them to a separate vendor bundle.  This vendor bundle is excluded from the watch on dev builds and from minification on the production builds.

This can be done my modifying the `webpack-dev.config.js` and `webpack-dist.config.js` respectively,  and adding the **preminified version** of the 3rd party lib to the vendor list.

```
    vendor: ["jquery", "angular", "angular-ui-router", "angular-animate", "angular-ui-bootstrap", "bootstrap"]
```

Note **to get the preminified version your must alias it that way at the bottom of the config file**.  It is not necessary to do this for every small dependency but if its is a larger libary it may be worth it.

All of the kendo libararies as well as those listed above are already aliased to reference their minified versions and thus can just be added to the list without any additional alias being added.

**Note:** By adding a dependency to this list it will be autmatically included in your bundle even if its not imported in any of your application files.  Therefore do not add dependencies to this list you are not using and if you remove a dependency from your project remove it from this list as well, to keep the file sizes smaller.

## Best Practices

There are examples of controllers, directives, factories, modules, and services in the generated application.  Have a look at them for ideas on how to structure your code.

1. Try to avoid putting everything in class constructors where possible.
2. Put HTTP calls and other service logic in a service not the controller.
2. Try to use `/*@ngInject*/` for annotation markers where possible.  This way people don't have to remember to update the explicit annotations.
3. Avoid the use of $scope when you can.  Exceptions include `$emit`, `$broadcast`, `$on`, `$watch` etc.
4. Don't forget you can use `aliases` in your imports as specified earlier.
5. Avoid `vm = this` syntax.  With ES6 the same can be achieved with `this` and `=>` syntax.
6. Do not use console.log instead use Angular's `$log`. Logging levels can be adjusted as mentioned earlier. Use `debug()` for stuff that should be turned off in production.
7. Always use explicit dependencies.  See the above section.