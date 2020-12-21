const express = require('express');
const bodyParser = require('body-parser');
const fileUpload = require('express-fileupload');
const cookieParser = require('cookie-parser')

const { getSheet, saveSheet } = require ('./static/scripts/googleAPI');
const { CodingFlashcard } = require('./Model/Flashcard');
const {FLASHCARDSETSmp, FLASHCARDSETS} = require('./static/scripts/fcSets')
const SAVED_IMAGES_PATH = __dirname + '/receivedFiles/zeauberg/images/';
const SAVED_IMAGES_relativePATH = '/receivedFiles/zeauberg/images/';

const app = express();
app.use(express.static('static', {root:__dirname+'/static'}))
app.use(bodyParser.json());
app.use(fileUpload());
app.use(cookieParser());


app.use('/coding-quiz', (req, res) => {
  res.sendFile('static/Views/coding-quiz.html', {root: __dirname});
});

app.get('/get-questions/:googleSheetId/scopes/:scopes', (req, res) => {
  getSheet(req.params.googleSheetId, req.params.scopes, res);
});

app.get('/get-questions/:imageName', (req, res) => {
  res.sendFile(SAVED_IMAGES_relativePATH + req.params.imageName, {root: __dirname});
});

app.get('/get-flashcard-sets', (req, res) => {
  res.json(FLASHCARDSETS);
});

app.get("/add-flashcard", (req, res) => {
  res.sendFile('static/Views/add-flashcard.html', {root: __dirname});
})

app.post("/add-flashcard", (req, res) => {
  if (!req.files || Object.keys(req.files).length === 0) {
    return res.status(400).send('No files were uploaded.');
  }
  const flashcardSetInfo = FLASHCARDSETSmp.get(req.body.flashcardSet);
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
  sampleFile.mv(SAVED_IMAGES_PATH + req.body.flashcardSet+ '/' + sampleFile.name, function(err) {
    if (err) console.log("Error. File couldn't been saved!");
    else console.log("File uploaded!");
  });
});

app.use((req, res) => {
    res.sendFile('static/Views/index.html', {root: __dirname});
});


const listeningPort = 3000;
app.listen(listeningPort, () => {
    console.log(`Server listening on port ${listeningPort}`)
});
