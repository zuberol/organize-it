const {flashcardsets, FLASHCARDSETS} = require('../Model/FlashcardSets') // delete FCmp 
const rootDir = require('../utils/rootPath');
const path = require('path');
const SAVED_IMAGES_PATH = path.join(rootDir, '/receivedFiles/zeauberg/images/');
const CodingFlashcard = require('../Model/Flashcard');
const saveSheet = require(path.join(rootDir, 'src', 'googleAPI')).saveSheet;

function addFlashcard(req, res) {
    const flashcardSetInfo = flashcardsets.get(req.body.flashcardSet);
    const fc = new CodingFlashcard(
      req.body.question,
      req.body.page,
      req.body['short-answer'],
      req.body['long-answer'],
      req.body['ref-url'],
      req.body['code-sample-url'],
      req.files.fileUploaded.name
    );
  
    res.cookie('lastAddedFcSet', req.body.flashcardSet);
  
    saveSheet(
      fc,
      res,
      flashcardSetInfo.googleSheetId,
      flashcardSetInfo.scopes
    );
  
    // The name of the input field (i.e. "fileUploaded") is used to retrieve the uploaded file
    let sampleFile = req.files.fileUploaded;
    // Use the mv() method to store the image on server
    sampleFile.mv(path.join(SAVED_IMAGES_PATH, req.body.flashcardSet, sampleFile.name), function(err) {
      if (err) console.log("Error. File couldn't been saved!");
      else console.log("File uploaded!");
    });
}

function getFlashcardSets(req, res) {
    res.json(FLASHCARDSETS);
}

function sendAddFcPage(req, res) {
    res.sendFile('static/Views/add-flashcard.html', {root: rootDir});
}

//todo
function getSavedImagesPath() {
  const userName = 'zeauberg';
  const fcsetName = '';
  const path = require('path').join('receivedFiles' )
}

module.exports.getFlashcardSets = getFlashcardSets;
module.exports.addFlashcard = addFlashcard;
module.exports.sendAddFcPage = sendAddFcPage;