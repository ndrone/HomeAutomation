var path = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var node_modules_dir = path.join(__dirname, 'node_modules');
var vendor_dir = path.join(__dirname, 'src/app/vendor');

// When you have a dep with a package.json that has a 'main' that points at a minified non-webpack friendly distribution
// try adding it to the list here (relative to node_modules). See README for more information.
var deps = [
  //These are for deps in the node_modules folder
  //the root folder becomes the alias name e.g. node_modules/my-dep/dist/my-dep.min.js  the alias will be my-dep
];


var vendorDeps = [
  //These are for deps in the vendor directory (not available via npm i)
  //the root folder becomes the alias name e.g. vendor/my-dep/dist/my-dep.min.js  the alias will be my-dep
];

var config = {
  entry: {
    app: [
      './src/app/app.js'
    ]
  },
  output: {
    path: path.join(__dirname, 'dist'),
    filename: 'scripts/bundle.js'
  },
  module: {
    noParse: [],
    loaders: [
      { test: /\.js$/, exclude: [/app\/lib/, /node_modules/], loader: 'babel?optional=runtime' },
      { test: /\.html$/, loader: 'raw' },
      { test: /\.less$/, loader: 'style!css!less' },
      { test: /\.scss$/, loader: 'style!css!sass' },
      { test: /\.styl$/, loader: 'style!css!stylus' },
      { test: /\.css$/, loader:  'style-loader!css-loader' },
      { test: /\.(png|jpg|gif)$/, loader: "file-loader?name=images/[name].[ext]" },
      //Most libraries use semantic versioning, however kendo has some .ttf's matching 1.0 instead of 1.0.0
      { test: /\.(ttf|eot|svg)(\?v=[0-9]\.[0-9]\.[0-9])?(\?v=[0-9]\.[0-9])?$/, loader: "file-loader?name=fonts/[name].[ext]" },
      { test: /\.woff(2)?(\?v=[0-9]\.[0-9]\.[0-9])?$/, loader: "file-loader?name=fonts/[name].[ext]" },
      //kendo has some wierd ones that look like this KendoUIGlyphs.eot?-wd8xpd
      { test: /\.(ttf|eot|svg)(\?-)(.+)$/, loader: "file-loader?name=fonts/[name].[ext]" },
      { test: /\.woff(2)?(\?-)(.+)$/, loader: "file-loader?name=fonts/[name].[ext]" },
      { test: /lodash\.min\.js$/, loader: 'expose?_' },
      { test: /jquery\.min\.js$/, loader: 'expose?$!expose?jQuery!expose?window.jQuery' },
      { test: /angular\.min\.js/, loader: "expose?angular!exports?window.angular" },
      { test: /\.ico$/, loader: "file-loader?name=[name].[ext]" }
    ]
  },
  devtool: 'sourcemap',
  plugins: [
    // Generate the index.html file from a template, add a cache-busting hash to the bundle.css and bundle.js files
    new HtmlWebpackPlugin({
      template: './src/index.tmpl.html',
      hash: true,
      inject: 'body'
    })
  ],
  resolve: {
    alias: {
      images: path.join(__dirname, 'src/app/images'),
      fonts: path.join(__dirname, 'src/app/fonts'),
      common: path.join(__dirname, 'src/app/common'),
      components: path.join(__dirname, 'src/app/components'),

      //Use preminified distributions to speed up production builds
      'jquery': path.join(node_modules_dir, 'jquery/dist/jquery.min.js'),
      'angular': path.join(node_modules_dir, 'angular/angular.min.js'),
      'angular-ui-router': path.join(node_modules_dir, 'angular-ui-router/release/angular-ui-router.min.js'),
      'angular-animate': path.join(node_modules_dir, 'angular-animate/angular-animate.min.js'),
      'angular-ui-bootstrap': path.join(node_modules_dir, 'angular-ui-bootstrap/ui-bootstrap-tpls.min.js'),
      'angular-breadcrumb': path.join(node_modules_dir, 'angular-breadcrumb/dist/angular-breadcrumb.min.js'),
      'bootstrap': path.join(node_modules_dir, 'bootstrap/dist/js/bootstrap.min.js'),
      'lodash': path.join(node_modules_dir, 'lodash/lodash.min.js')
    }

  }
};

deps.forEach(function (dep) {
  var depPath = path.resolve(node_modules_dir, dep);
  config.resolve.alias[dep.split(path.sep)[0]] = depPath;
  config.module.noParse.push(depPath);
});

vendorDeps.forEach(function (dep) {
  var depPath = path.resolve(vendor_dir, dep);
  config.resolve.alias[dep.split(path.sep)[0]] = depPath;
  config.module.noParse.push(depPath);
});

module.exports = config;
