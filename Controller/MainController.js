const rootPath = require('../utils/rootPath');
const FRONTEND_BASE_URL = require('../utils/config').FRONTEND_BASE_URL;

function redirectToIndex(req, res) {
    res.sendFile('static/Views/coding-quiz.html', {root: rootPath});
    res.redirect(FRONTEND_BASE_URL);
}
function sendErrorPage(req, res) {
    res.sendFile('static/Views/index.html', {root: rootPath});
}

module.exports.redirectToIndex = redirectToIndex;
module.exports.sendErrorPage = sendErrorPage;