
export default class Task {
  constructor({ task_id = null, note = "", sub_tasks = [] }) {
    this.task_id = task_id;
    this.note = note;
    this.sub_tasks = sub_tasks.map(st => new Task(st));
    this['@class'] = "com.zuber.organizeit.Model.Task";
  }

  static ['@class'] = "com.zuber.organizeit.Model.Task"; // todo remove

  static empty() {
    return new Task({});
  }

  static recordToTask(taskRecord) {
    let task = Task.empty();
    if (taskRecord["@class"] === Task["@class"]) task == new Task({...taskRecord});
    return task;
  }

}
