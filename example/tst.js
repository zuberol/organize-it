class FlashcardSet {
    constructor(name, googleSheetId, scopes) {
        this.name = name;
        this.googleSheetId = googleSheetId;
        this.scopes = scopes;
    }
}

const set = {
    name: 'java fundamentals',
    googleSheetId: '13VsYi1cnJ-3n4H9dt1Gc96VgMLt5PAgLpMFlxmGvzc4',
    scopes: "'java-pytania'!A:G"
}

const fcSet = new FlashcardSet(...Object.values(set)) 

console.log(fcSet)