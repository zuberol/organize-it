import { useState } from "react";
import { TASK_NEW_URL, TASK_URL } from "../config/backendRoutes";

export default class Task {
  constructor({ task_id = null, name: name = "", description: description = "", sub_tasks = [], isDone: isDone, isArchived: isArchived }) {
    this.task_id = task_id;
    this.description = description;
    this.name = name; // todo remove
    this.sub_tasks = sub_tasks.map(st => new Task(st));
    this.isDone = isDone;
    this.isArchived = isArchived;
  }

  static empty() {
    return new Task({});
  }

  static transferObjectToTask(taskRecord) {
    let task = Task.empty();
    if (taskRecord["@class"] === Task["@class"]) task == new Task({ ...taskRecord });
    return task;
  }


}

export function TaskForm({
  taskDefaults = {
    task_id: '',
    description: '',
    sub_tasks: [],
    priority: '',
    archived: false,
    project: false 
  }, 
  newTask = false
}) {
  const [taskTO, setTaskTO] =  useState({});
  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="task_id">Task ID</label>
      <input
        hidden={newTask}
        name="task_id"
        type="number"
        onChange={handleChange}
        defaultValue={taskDefaults.task_id}
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
      <label htmlFor="priority">Priority</label>
      <input
        name="priority"
        type="number"
        onChange={handleChange}
      />
      <label htmlFor="is_project">isProject</label>
      <input
        name="is_project"
        type="checkbox"
        defaultChecked={taskDefaults.project}
        onChange={handleChangeCheckbox}
      />
      <label htmlFor="is_archived">Is archived?</label>
      <input
        name="is_archived"
        type="checkbox"
        defaultChecked={taskDefaults.archived}
        onChange={handleChangeCheckbox}
      />
      <label htmlFor="subtaskIds">Subtasks ids</label>
      <input
        name="subtask_ids"
        type="text"
        defaultValue={taskDefaults.sub_tasks.map(_ => _.task_id).toString()}
        onChange={handleChangeArray}
      />
      <button type="submit">Submit</button>
    </form>
  )

  function handleSubmit(event) {
    event.preventDefault();
    fetch((newTask && TASK_NEW_URL) || TASK_URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(taskTO)
    })
    .catch(e => console.log(e))
  }

  function handleChangeCheckbox(e) {
    setTaskTO({
      ...taskTO, 
      [e.target.name]: e.target.checked
    });
  }

  function handleChange(e) {
    setTaskTO({
      ...taskTO, 
      [e.target.name]: e.target.value
    });
  }

  function handleChangeArray(e) {
    setTaskTO({
      ...taskTO, 
      [e.target.name]: e.target.value.split(',')
    });
  }

}

