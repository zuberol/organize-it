const {getSheet} = require('../src/googleAPI')
const SAVED_IMAGES_relativePATH = '/receivedFiles/zeauberg/images/';

function sendFcDeck(req, res) {
    getSheet(req.params.googleSheetId, req.params.scopes, res);
}

function sendFcDeckImage(req, res) {
    res.sendFile(SAVED_IMAGES_relativePATH + req.params.imageName, {root: __dirname});
}

module.exports.sendFcDeck = sendFcDeck;
module.exports.sendFcDeckImage = sendFcDeckImage;