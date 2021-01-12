const express = require('express');
const Router = express.Router();
const FlashcardController = require('./Controller/FlashcardController');
const QuestionController = require('./Controller/QuestionController');
const MainController = require('./Controller/MainController');

Router.use('/coding-quiz', MainController.sendIndexPage);
Router.get('/get-questions/:googleSheetId/scopes/:scopes', QuestionController.sendQuestion);
Router.get('/get-questions/:imageName', QuestionController.sendQuestionImage);
Router.get('/get-flashcard-sets', FlashcardController.getFlashcardSets);
Router.get("/add-flashcard", FlashcardController.sendAddFcPage)
Router.post("/add-flashcard", FlashcardController.addFlashcard);
Router.use(MainController.sendErrorPage);

module.exports = Router;