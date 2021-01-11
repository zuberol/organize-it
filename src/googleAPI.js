const fs = require('fs');
const readline = require('readline');
const {google} = require('googleapis');
const CodingFlashcard = require('./../Model/Flashcard');
const { auth } = require('googleapis/build/src/apis/abusiveexperiencereport');

const ROOT_DIRS = [
  "/Users/zuber/js/organize-it/receivedFiles/zeauberg/java/",
  "/Users/zuber/js/ref/react-scripts/"
]
const SCOPES = ['https://www.googleapis.com/auth/spreadsheets'];
const TOKEN_PATH = 'token.json';

let SPREEDSHEETID = null;
let RANGE = null;

/**
 * Get data from google-sheets and send it to the client
 * @param {String} spreadsheetId what sheet you want to read
 * @param {String} range sheet range you want to read
 * @param  {Express.Response} res The express.js response object.
 */
function getSheet(spreadsheetId, range, res) {
  SPREEDSHEETID = spreadsheetId;
  RANGE = range;
  fs.readFile('credentials.json', (err, content) => {
    if (err) return console.log('Error loading client secret file:', err);    //todo throw error
    authorize(JSON.parse(content), res);
  });
}

/**
 * Create an OAuth2 client with the given credentials, and then execute the
 * given callback function.
 * @param {Object} credentials The authorization client credentials.
 * @param  {Express.Response} res The express.js response object.
 */
function authorize(credentials, res) {
  const {client_secret, client_id, redirect_uris} = credentials.installed;
  const oAuth2Client = new google.auth.OAuth2(
      client_id, client_secret, redirect_uris[0]);

  fs.readFile(TOKEN_PATH, (err, token) => {
    if (err) return getNewToken(oAuth2Client, res);
    oAuth2Client.setCredentials(JSON.parse(token));
    parseDataAndSend(oAuth2Client, res);
  });
}

/**
 * Get and store new token after prompting for user authorization, and then
 * execute the given callback with the authorized OAuth2 client.
 * @param {google.auth.OAuth2} oAuth2Client The OAuth2 client to get token for.
 * @param  {Express.Response} res The express.js response object.
 */
function getNewToken(oAuth2Client, res) {
  const authUrl = oAuth2Client.generateAuthUrl({
    access_type: 'offline',
    scope: SCOPES,
  });
  console.log('Authorize this app by visiting this url:', authUrl);
  const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
  });
  rl.question('Enter the code from that page here: ', (code) => {
    rl.close();
    oAuth2Client.getToken(code, (err, token) => {
      if (err) return console.error('Error while trying to retrieve access token', err);
      oAuth2Client.setCredentials(token);
      // Store the token to disk for later program executions
      fs.writeFile(TOKEN_PATH, JSON.stringify(token), (err) => {
        if (err) return console.error(err);
        console.log('Token stored to', TOKEN_PATH);
      });
      parseDataAndSend(oAuth2Client, res);
    });
  });
}

/**
 * Prints the names and majors of students in a sample spreadsheet:
 * @see https://docs.google.com/spreadsheets/d/13VsYi1cnJ-3n4H9dt1Gc96VgMLt5PAgLpMFlxmGvzc4/edit#gid=1327921580
 * @param {google.auth.OAuth2} auth The authenticated Google OAuth client.
 * @param  {Express.Response} res The express.js response object.
 */
function parseDataAndSend(auth, res) {
  const sheets = google.sheets({version: 'v4', auth});
  sheets.spreadsheets.values.get({
    spreadsheetId: SPREEDSHEETID,
    range: RANGE,
  }, (err, response) => {
    if (err) return console.log('The API returned an error: ' + err);
    const rows = response.data.values;
    const flashCards = []
    rows.forEach(function(row){
      flashCards.push(
          new CodingFlashcard(
            row[0],
            row[1],
            row[2],
            row[3],
            row[4],
            map.get(row[5]),
            row[6]
          )
      );
    });
    res.status(201).json(flashCards);
  });
}


// insert code to map
let map = new Map();
/**
 * 
 * @param {String []} root_dirs Array of directories paths
 * @param  {Map} map Map to store code files contents.
 */
function readCodeDirs(root_dirs, map) {
  root_dirs.forEach(dirName => {
    fs.readdir(dirName, function(err, files){
      if (err) {
          return console.log('Unable to scan directory: ' + err);
      } 
      files.forEach(function (file) {
          fs.readFile(dirName + file, 'utf8', function (err, data) {
            if (err) console.log(err);
            map.set(file, data);
          });
      });
    });
  })
}
readCodeDirs(ROOT_DIRS, map);


/**
 * Saves Flashcard object to the pointed sheet
 * @param {google.auth.OAuth2} auth The authenticated Google OAuth client.
 * @param  {Express.Response} res The express.js response object.
 * @param  {String} googleSheetId Sheet id to save flashcard.
 */
async function saveSheet(fc, res, googleSheetId, range) {
  SPREEDSHEETID = googleSheetId;    //TODO 
  RANGE = range;   //TODO
  fs.readFile('credentials.json', (err, content) => {
    if (err) return console.log('Error loading client secret file:', err);    //todo throw error
    else authorization(fc, JSON.parse(content), res);
  });
}

async function authorization(fc, credentials, res) {
  const {client_secret, client_id, redirect_uris} = credentials.installed;
  const oAuth2Client = new google.auth.OAuth2(
      client_id, client_secret, redirect_uris[0]);

  fs.readFile(TOKEN_PATH, (err, token) => {
    if (err) res.status(500).send("Error with reading token. Check if token was previously succesfully stored");
    else {
      oAuth2Client.setCredentials(JSON.parse(token));
      parseDataAndWrite(fc, oAuth2Client, res);
    }
  });
}

async function parseDataAndWrite(fc, authClient, res) {
  const request = {
    spreadsheetId: SPREEDSHEETID,
    range : RANGE,
    valueInputOption: 'RAW',
    requestBody: {
      range: RANGE,
      majorDimension: "ROWS",
      values: [
        [        
          ...Object.values(fc)
        ]
      ]
    },
    auth: authClient
  }
  
  try {
    const sheets = google.sheets({version: 'v4', auth});
    sheets.spreadsheets.values.append(request);
    res.send("Flashcard was succesfully saved in googleSheet");
  } catch (err) {
    console.error(err);
    res.send(error);
  }
}



module.exports.getSheet = getSheet;
module.exports.saveSheet = saveSheet;
