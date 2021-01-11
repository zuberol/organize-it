const FlashcardSet = require('../Model/FlashcardSet');

const FLASHCARDSETS = [
  {
    name: 'java fundamentals',
    googleSheetId: '13VsYi1cnJ-3n4H9dt1Gc96VgMLt5PAgLpMFlxmGvzc4',
    scopes: "'java-pytania'!A:G"
  },
  {
    name: 'java 2',
    googleSheetId: '1xtsqyloR7YWikuFVi95juoRkvOpQS6w9W5whzRPiJlk',
    scopes: "'java'!A:G"
  },
  {
    name: 'spring',
    googleSheetId: '1xtsqyloR7YWikuFVi95juoRkvOpQS6w9W5whzRPiJlk',
    scopes: "'spring'!A:G"
  },
  {
    name: 'AWS',
    googleSheetId: '1xtsqyloR7YWikuFVi95juoRkvOpQS6w9W5whzRPiJlk',
    scopes: "'AWS'!A:G"
  },
  {
    name: 'react.js',
    googleSheetId: '1xtsqyloR7YWikuFVi95juoRkvOpQS6w9W5whzRPiJlk',
    scopes: "'react.js'!A:G"
  },
  {
    name: 'testing one two 1 2',
    googleSheetId: '1cyH_Ar8mibI3jifRh1mbLe5BBCnHEIkbexrdbRUA74w',
    scopes: "'react.js'!A:G"
  }
];

const flashcardsets = new Map();
FLASHCARDSETS.forEach(fc_set => {
  flashcardsets.set(fc_set.name, fc_set);
});

exports.FLASHCARDSETS = FLASHCARDSETS;
exports.flashcardsets = flashcardsets;
