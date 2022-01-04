import { useState } from "react";
import { TASK_NEW_URL, TASK_URL } from "../config/backendRoutes";

export default class Task {
  constructor({ taskId = null, name: name = "", description: description = "", subTasks = [], isDone: isDone, archived: archived }) {
    thisid = taskId;
    this.description = description;
    this.name = name; // todo remove
    this.subTasks = subTasks.map(st => new Task(st));
    this.isDone = isDone;
    this.archived = archived;
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
    taskId: '',
    name: '',
    description: '',
    subTasks: [],
    priority: '',
    archived: false,
    project: false ,
    tags: []
  }, 
  parentTaskId =  '',
  newTask = false
}) {
  const [taskTO, setTaskTO] =  useState({...taskDefaults});
  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="taskId">Task ID</label>
      <input
        hidden={newTask}
        name="taskId"
        type="number"
        onChange={handleChange}
        defaultValue={taskDefaultsid}
      />
      <label htmlFor="name">Name</label>
      <input
        name="name"
        type="text"
        defaultValue={taskDefaults.name}
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
        defaultChecked={taskDefaults.tags.toString()}
        onChange={handleChange}
      />
      <label htmlFor="priority">Priority</label>
      <input
        name="priority"
        type="number"
        onChange={handleChange}
      />
      <label htmlFor="isProject">isProject</label>
      <input
        name="isProject"
        type="checkbox"
        defaultChecked={taskDefaults.project}
        onChange={handleChangeCheckbox}
      />
      <label htmlFor="archived">Is archived?</label>
      <input
        name="archived"
        type="checkbox"
        defaultChecked={taskDefaults.archived}
        onChange={handleChangeCheckbox}
      />
      <label htmlFor="subtaskIds">Subtasks ids</label>
      <input
        name="subtaskIds"
        type="text"
        defaultValue={taskDefaults.subTasks.map(_ => _id).toString()}
        onChange={handleChangeArray}
      />
      <button type="submit">Submit</button>
    </form>
  )

  function handleSubmit(event) {
    event.preventDefault();
    fetch(((newTask && TASK_NEW_URL) || TASK_URL) + `?parentTaskId=${parentTaskId}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(stripToDto(taskTO))
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

export function stripToDto(task) {
  if(!task) return {};
  else return {  
    taskId: taskid,
    name: task.name,
    description: task.description,
    subtaskIds: task.subtaskIds,
    done: task.done,
    archived: task.archived,
    project: task.project,
    priority: task.priority
  };
}