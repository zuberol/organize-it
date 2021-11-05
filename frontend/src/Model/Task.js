
export default class Task {
  constructor({ task_id = null, name: name = "", description: description = "", sub_tasks = [], isDone: isDone, isArchived: isArchived }) {
    this.task_id = task_id;
    this.description = description;
    this.name = name; // todo remove
    this.sub_tasks = sub_tasks.map(st => new Task(st));
    this.isDone = isDone;
    this.isArchived = isArchived;
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
