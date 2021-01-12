class CodingFlashcard {
    constructor(question, page, short_answer, long_answer, ref_url, code_sample_url, picture_url) {
      this.question = question;
      this.page = page,
      this.short_answer = short_answer;
      this.long_answer = long_answer;
      this.ref_url = ref_url;
      this.code_sample_url = code_sample_url;
      this.picture_url = picture_url;
    }
    static reqbodyToFlashcard(req) {
      return new CodingFlashcard(
        req.body.question,
        req.body.page,
        req.body['short-answer'],
        req.body['long-answer'],
        req.body['ref-url'],
        req.body['code-sample-url'],
        req.files.fileUploaded.name
      )
    }
}

module.exports = CodingFlashcard;