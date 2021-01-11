const {getSheet} = require('../src/googleAPI')
const SAVED_IMAGES_relativePATH = '/receivedFiles/zeauberg/images/';

function sendQuestion(req, res) {
    getSheet(req.params.googleSheetId, req.params.scopes, res);
}

function sendQuestionImage(req, res) {
    res.sendFile(SAVED_IMAGES_relativePATH + req.params.imageName, {root: __dirname});
}

module.exports.sendQuestion = sendQuestion;
module.exports.sendQuestionImage = sendQuestionImage;