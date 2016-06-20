//TODO forcing the minification in the config, which I do by adding the configuration to squelch the noisy output
//seems to break Karma tests.  This config is a temporary solution until that can be sorted.
var config = require('./webpack.config.js');
var path = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');

var distConfig = {
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
  plugins: [
    new webpack.optimize.CommonsChunkPlugin('vendor', 'scripts/vendor.bundle.js'),

    // Generate the index.html file from a template, add a cache-busting hash to the bundle.css and bundle.js files
    new HtmlWebpackPlugin({
      template: './src/index.tmpl.html',
      hash: true,
      inject: 'body'
    }),

    //Leaving compress in takes a lot of time due to optimizations and barely changes the size of the output file.
    //Without figuring out how to tweak the defaults more its not worth it.  We also exclude the vendor bundle
    //from being processed.
    new webpack.optimize.UglifyJsPlugin({
      compress: false,
      minimize: true,
      exclude: ["scripts/vendor.bundle.js"]
    })
  ],
  resolve: config.resolve
};

//only do ng-annotate on dev/dist builds don't want this behavior for tests where we do our own mocking/injection
distConfig.module.loaders.push({ test: /\.js$/, exclude: /(node_modules|vendor)/, loader: 'ng-annotate!babel' });
module.exports = distConfig;