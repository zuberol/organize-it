const express = require('express');
const Router = express.Router();
const FlashcardController = require('./Controller/FlashcardController');
const QuestionController = require('./Controller/QuestionController');
const MainController = require('./Controller/MainController');

Router.get('/flashcard/decks', FlashcardController.sendAvailableFcDecks);
Router.get('/flashcard/deck/:googleSheetId', QuestionController.sendFcDeck);
Router.post('/flashcard/image', QuestionController.sendFcDeckImage);
Router.get('/flashcard/creator', FlashcardController.sendAddFcPage)
Router.put('/flashcard', FlashcardController.createFlashcard);
Router.use('/', MainController.redirectToIndex);
Router.use(MainController.sendErrorPage);

module.exports = Router;