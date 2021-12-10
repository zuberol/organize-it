export default class Flashcard {
  constructor(
    fc_id,
    question = '',
    short_answer = '',
    long_answer = '',
    reference_resources = []) {

    this.fc_id = fc_id;
    this.question = question;
    this.short_answer = short_answer;
    this.long_answer = long_answer;
    this.reference_resources = reference_resources;
  }
  // static reqbodyToFlashcard(req) {
  //   return new Flashcard(
  //   req.body.question,
  //   req.body.page,
  //   req.body['short-answer'],
  //   req.body['long-answer'],
  //   req.body['ref-url'],
  //   req.body['code-sample-url'],
  //   req.files.fileUploaded.name,
  //   req.body.id
  //   )
  // }

  //   deleteSelfFromDB() {
  //   fetch(path.join(BACKEND_BASE_URL, "/flashcard"), {
  //     method: 'DELETE',
  //     credentials: 'include',
  //     body: this
  //   })
  //   .then(res => {
  //     if(res.status != 200) throw `Something went wrong when deleting Flashcard from db. Response code: ${res.status}`;
  //   })
  //   .catch(e => console.log(e));
  // }
}



// returns <JSX> resourceDef
export function resourceToJsx(resource, key) {
  const simpleRefDef = (
    resource && resource.reference_url &&
    <div>
      <a href={resource.reference_url || "#"}>{resource.caption || "no caption"}</a>
      {resource.comment && <p>{resource.comment}</p>}
    </div>
  );
  const codeJsx = resource && typeof resource['@class'] === "String" && resource['@class'].match(/CodeReference/gi) && <div>CodeReference</div>

  return (
    <div key={key}>
      {simpleRefDef}
      {codeJsx}
    </div>
  )
}

