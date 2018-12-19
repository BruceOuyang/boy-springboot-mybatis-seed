'use strict'
// =======================================================
//                Local Mock Server
// =======================================================
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

var express = require('express')
var fs = require('fs');

var localMockServer = express()
var mockRouter = express.Router();

mockRouter.all('/*', function (req, res) {
  fs.readdir("./mock/" ,function (errors, files) {
    for(var i in files) {
      var bufferStr = fs.readFileSync('./mock/'+files[i]);
      var appData = eval('('+bufferStr+')');
      let originalUrl = req.originalUrl.replace('/api/boss-fps', '').split('?')[0];
      if(appData[originalUrl]) {
        res.send(appData[originalUrl]);
        break;
      }
    }
  });
});

localMockServer.use('/',mockRouter);
localMockServer.listen(3034, () => console.log('LocalMockServer listening on port 3034!'))

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"'
})
