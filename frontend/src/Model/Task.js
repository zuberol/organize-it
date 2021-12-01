
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
    if (taskRecord["@class"] === Task["@class"]) task == new Task({ ...taskRecord });
    return task;
  }


}

export function TaskForm() {
  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="task_id">Task ID</label>
      <input
        name="task_id"
        type="number"
        onChange={handleChange}
      />
      <label htmlFor="name">Name</label>
      <input
        name="name"
        type="text"
        onChange={handleChange}
      />
      <label htmlFor="description">Description</label>
      <input
        name="description"
        type="text"
        onChange={handleChange}
      />
      <label htmlFor="tags">Tags (comma separated)</label>
      <input
        name="tags"
        type="text"
        onChange={handleChange}
      />
      <button type="submit">Submit</button>
    </form>
  )

  function handleSubmit(event) {
    event.preventDefault();
    // todo
  }

  function handleChange(e) {
    // todo
  }

}

