class CodingFlashcard {
    constructor({question, page, short_answer, long_answer, ref_url, code_sample_url, picture_url, id}) {
      this.question = question;
      this.page = page,
      this.short_answer = short_answer;
      this.long_answer = long_answer;
      this.ref_url = ref_url;
      this.code_sample_url = code_sample_url;
      this.picture_url = picture_url;
      this.id = id;
    }
}

module.exports = CodingFlashcard;