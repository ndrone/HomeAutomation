'use strict';

import gulp     from 'gulp';
import path     from 'path';
import sync     from 'run-sequence';
import rename   from 'gulp-rename';
import template from 'gulp-template';
import fs       from 'fs';
import yargs    from 'yargs';
import lodash   from 'lodash';

let root = 'src';
let dist = 'dist';

let resolveToComponents = (glob) => {
  glob = glob || '';
  return path.join(root, 'app/components', glob); // app/components/{glob}
};


let resolveToDist = (glob) => {
  glob = glob || '';
  return path.join(dist, '', glob); // app/components/{glob}
};

// map of all paths
let paths = {
  js: resolveToDist('**/*!(.spec.js).js'), // exclude spec files
  css: resolveToDist('**/*.css'),
  html: [
    resolveToDist('**/*.html'),
    path.join(dist, 'index.html')
  ],
  dist: path.join('dist', ''),
  blankTemplates: path.join(__dirname, 'generator', 'component/**/*.**')
};

gulp.task('component', () => {
  let cap = (val) => {
    return val.charAt(0).toUpperCase() + val.slice(1);
  };
  let name = yargs.argv.name;
  let hyphenName = yargs.argv.name.replace(/([a-z])([A-Z])/g, '$1-$2').replace(/([A-Z])([A-Z])/g, '$1-$2').toLowerCase();
  let parentPath = yargs.argv.parent || '';
  let destPath = path.join(resolveToComponents(), parentPath, name);

  return gulp.src(paths.blankTemplates)
    .pipe(template({
      name: name,
      hyphenName: hyphenName,
      upCaseName: cap(name)
    }))
    .pipe(rename((path) => {
      path.basename = path.basename.replace('temp', name);
      path.basename = path.basename.replace('temp', hyphenName);
    }))
    .pipe(gulp.dest(destPath));
});
