const express = require('express');
const db = require('./Model/db');
const bodyParser = require('body-parser');

class CodingFlashcard {
  constructor(q, ans, page, ref_url,code_file_url){
    this.question = q;
    this.answer = ans;
    this.page = page,
    this.ref_url = ref_url;
    this.code_file_url = code_file_url;
  }
}


fs = require('fs');

database = [];
ROOT_DIR = "/Users/zuber/java/scripts/"

var map = new Map();

fs.readdir(ROOT_DIR, function(err, files){
    if (err) {
        return console.log('Unable to scan directory: ' + err);
    } 
    //listing all files using forEach
    files.forEach(function (file) {
        // Do whatever you want to do with the file
        //console.log(file);
        fs.readFile(ROOT_DIR + file, 'utf8', function (err, data) {
            if (err) return console.log(err);
            //console.log(data);
            //database.push(data);
            map.set(file, data);
        });
    });
});



const app = express();

app.use(express.static('static', {root:__dirname+'/static'}))
app.use(bodyParser.json());

app.get('/witam', (req, res) => {
    // console.log(res)
    res.status(200).json({
        db
    });
});

app.get('/tasks', (req,res) => {
    res.status(200).json(db);
});
app.post('/tasks', (req,res) => {
    db.push(req.body.newTask);
    res.status(200).json("Task succesfully added");
});

app.use('/quiz',(req,res) => {
    res.sendFile('static/Views/quiz.html', {root: __dirname});
});

app.use('/coding-quiz',(req,res) => {
  res.sendFile('static/Views/coding-quiz.html', {root: __dirname});
});

app.use('/get-questions',(req,res) => {



    const fs = require('fs');
    const readline = require('readline');
    const {google} = require('googleapis');
    
    // If modifying these scopes, delete token.json.
    const SCOPES = ['https://www.googleapis.com/auth/spreadsheets.readonly'];
    // The file token.json stores the user's access and refresh tokens, and is
    // created automatically when the authorization flow completes for the first
    // time.
    const TOKEN_PATH = 'token.json';
    
    // Load client secrets from a local file.
    fs.readFile('credentials.json', (err, content) => {
      if (err) return console.log('Error loading client secret file:', err);
      // Authorize a client with credentials, then call the Google Sheets API.
      authorize(JSON.parse(content), listMajors);
    });
    
    /**
     * Create an OAuth2 client with the given credentials, and then execute the
     * given callback function.
     * @param {Object} credentials The authorization client credentials.
     * @param {function} callback The callback to call with the authorized client.
     */
    function authorize(credentials, callback) {
      const {client_secret, client_id, redirect_uris} = credentials.installed;
      const oAuth2Client = new google.auth.OAuth2(
          client_id, client_secret, redirect_uris[0]);
    
      // Check if we have previously stored a token.
      fs.readFile(TOKEN_PATH, (err, token) => {
        if (err) return getNewToken(oAuth2Client, callback);
        oAuth2Client.setCredentials(JSON.parse(token));
        callback(oAuth2Client);
      });
    }
    
    /**
     * Get and store new token after prompting for user authorization, and then
     * execute the given callback with the authorized OAuth2 client.
     * @param {google.auth.OAuth2} oAuth2Client The OAuth2 client to get token for.
     * @param {getEventsCallback} callback The callback for the authorized client.
     */
    function getNewToken(oAuth2Client, callback) {
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
          callback(oAuth2Client);
        });
      });
    }
    
    /**
     * Prints the names and majors of students in a sample spreadsheet:
     * @see https://docs.google.com/spreadsheets/d/13VsYi1cnJ-3n4H9dt1Gc96VgMLt5PAgLpMFlxmGvzc4/edit#gid=1327921580
     * @param {google.auth.OAuth2} auth The authenticated Google OAuth client.
     */
    function listMajors(auth) {
      const sheets = google.sheets({version: 'v4', auth});
      sheets.spreadsheets.values.get({
        spreadsheetId: '13VsYi1cnJ-3n4H9dt1Gc96VgMLt5PAgLpMFlxmGvzc4',
        range: "'java-pytania'!A:E",
      }, (err, response) => {
        if (err) return console.log('The API returned an error: ' + err);
        const rows = response.data.values;
        //console.log(rows.length);
        //console.log(rows[60]);


        rows.forEach(function(row){
            //q, ans, page, ref_url,code_file_url
            //console.log(row[4])
            database.push(
              new CodingFlashcard(
                row[0],
                row[2],
                row[1],
                row[3],
                map.get(row[4])
              )
            )
        });
        //console.log(map.get('genericClassInterface.java'));
        //console.log(JSON.stringify(database))
        res.status(201).json(database);




        return rows;
      });
    }










});

app.use((req, res) => {
    res.sendFile('static/Views/index.html', {root: __dirname});
});


const listeningPort = 3000;

app.listen(listeningPort, () => {
    console.log(`Server listening on port ${listeningPort}`)
});
