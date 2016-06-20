//From a technical view it makes no sense to use the CommonsChunkPlugin with a single app entry, it only makes it look better.
//It costs an additional HTTP request and doesn't give you anything in production.
//In development however separating vendor to its own bundles provides faster incremental compiling.
//Ref - https://github.com/webpack/webpack/issues/368
//Note - Karma is not compatible with the commons chunk plugin so it must be disabled for testing.
//Ref - [https://github.com/webpack/karma-webpack/issues/24].
//Also we don't wish to bother with minification for development.
var config = require('./webpack.config.js');
var path = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');


var devConfig = {
  entry: {
    app: [
      './src/app/app.js'
    ],
    vendor: ["jquery", "angular", "angular-ui-router", "angular-animate", "angular-ui-bootstrap", "angular-breadcrumb", "bootstrap", "lodash", "restangular"]
  },
  output: config.output,
  externals: config.externals,
  module: config.module,
  devtool: config.devtool,
  devServer: {
    hot: true,
    contentBase: "src",
    // Set this if you want webpack-dev-server to delegate a single path to an arbitrary server.
    // Use "*" to proxy all paths to the specified server.
    // This is useful if you want to get rid of 'http://localhost:8080/' in script[src],
    // and has many other use cases (see https://github.com/webpack/webpack-dev-server/pull/127 ).
    proxy: {
      "/api/*": "http://localhost:8080"
    }
  },
  plugins: [

    new webpack.optimize.CommonsChunkPlugin('vendor', 'scripts/vendor.bundle.js'),

    // Generate the index.html file from a template, add a cache-busting hash to the bundle.css and bundle.js files
    new HtmlWebpackPlugin({
      template: './src/index.tmpl.html',
      hash: true,
      inject: 'body'
    })
  ],
  resolve: config.resolve
};
//only do ng-annotate on dev/dist builds don't want this behavior for tests where we do our own mocking/injection
devConfig.module.loaders.push({ test: /\.js$/, exclude: /(node_modules|vendor)/, loader: 'ng-annotate!babel' });
module.exports = devConfig;