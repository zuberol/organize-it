const CodingFlashcard = require('./../Model/Flashcard');

const testReq = {
    body: {
        question: "q",
        page: 'p',
        "short-answer": "sa",
        'long-answer': "la",
        'code-sample-url': 'csu'
    },

    files: {
        fileUploaded: {
            name: "namen"
        }
    }
} 
