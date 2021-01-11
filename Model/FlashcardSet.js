class FlashcardSet {
    constructor(name, googleSheetId, scopes) {
        this.name = name;
        this.googleSheetId = googleSheetId;
        this.scopes = scopes;
    }
}
module.exports = FlashcardSet;