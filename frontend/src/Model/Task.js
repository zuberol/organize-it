import { useState } from "react";
import { TASK_NEW_URL, TASK_URL } from "../config/backendRoutes";
import { useDispatch } from "react-redux";
import { fetchInbox, fetchPlans } from "../store/tasks/actions";

export default class Task {
  constructor({ id = null, name: name = "", description: description = "", subTasks = [], isDone: isDone, archived: archived }) {
    this.id = id;
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

export function TaskForm(props) {


  const taskDefaults = {
    id: '',
    name: '',
    description: '',
    subTasks: [],
    priority: '',
    archived: false,
    tags: []
  };
  const parentTaskId = props.parentTaskId || '';
  const newTask = props.newTask || false;



  const [taskTO, setTaskTO] = useState({ ...taskDefaults, ...props.task });
  const dispatch = useDispatch();
  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="id" hidden={newTask}>Task ID</label>
      <input
        hidden={newTask}
        name="id"
        type="number"
        onChange={handleChange}
        defaultValue={taskTO.id}
      />
      <label htmlFor="name">Name</label>
      <input
        name="name"
        type="text"
        defaultValue={taskTO.name}
        onChange={handleChange}
      />
      <label htmlFor="description">Description</label>
      <input
        name="description"
        type="text"
        defaultValue={taskTO.description}
        onChange={handleChange}
      />
      <label htmlFor="tags">Tags (comma separated)</label>
      <input
        name="tags"
        type="text"
        defaultChecked={taskTO.tags.toString()}
        onChange={handleChange}
      />
      <label htmlFor="priority">Priority</label>
      <input
        name="priority"
        type="number"
        onChange={handleChange}
      />
      <label htmlFor="archived" hidden={newTask}>Is archived?</label>
      <input
        name="archived"
        type="checkbox"
        hidden={newTask}
        defaultChecked={taskTO.archived}
        onChange={handleChangeCheckbox}
      />
      <label htmlFor="subtaskIds">Subtasks ids</label>
      <input
        name="subtaskIds"
        type="text"
        defaultValue={taskTO.subTasks.map(_ => _.id).toString()}
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
      .then(() => {
        dispatch(fetchInbox());
        dispatch(fetchPlans())
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
  if (!task) return {};
  else return {
    id: task.id,
    name: task.name,
    description: task.description,
    subtaskIds: task.subtaskIds,
    done: task.done,
    archived: task.archived,
    plan: task.plan,
    priority: task.priority
  };
}