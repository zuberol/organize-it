const express = require('express');
const bodyParser = require('body-parser');
const fileUpload = require('express-fileupload');
const cookieParser = require('cookie-parser')
const cors = require('cors');
const Router = require('./routes');

const app = express();
app.use(express.static('static', {root:__dirname+'/static'}))
app.use(cors({
    origin: 'http://localhost:3000'
}));
app.use(bodyParser.json());
app.use(fileUpload());
app.use(cookieParser());
app.use(Router);

const listeningPort = 8080;
app.listen(listeningPort, () => {
    console.log(`Server listening on port ${listeningPort}`)
});
