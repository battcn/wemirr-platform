'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BOOKSTORE_URL:'"http://localhost:9090/app/books?pageSize=10&pageNum=20"'
})
