const rootPath = require('../utils/rootPath')

function sendIndexPage(req, res) {
    res.sendFile('static/Views/coding-quiz.html', {root: rootPath});
}
function sendErrorPage(req, res) {
    res.sendFile('static/Views/index.html', {root: rootPath});
}

module.exports.sendIndexPage = sendIndexPage;
module.exports.sendErrorPage = sendErrorPage;