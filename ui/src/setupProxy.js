const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        '/api',
        createProxyMiddleware({
            target: "http://bookstore-api:8080",
            changeOrigin: true
        })
    );
};
