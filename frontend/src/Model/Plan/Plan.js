import { PLANS_URL } from "../../config/backendRoutes";



export function PlanForm({
    planDefaults = {
      id: '',
      name: '',
      description: '',
      rootTaskIds: []
    },
    newPlan = false
  }) {
    const [planTO, setPlanTO] =  useState({...planDefaults});
    return (
      <form onSubmit={handleSubmit}>
        <label htmlFor="id">Plan ID</label>
        <input
          hidden={newPlan}
          name="id"
          type="number"
          onChange={handleChange}
          defaultValue={planDefaults.id}
        />
        <label htmlFor="name">Name</label>
        <input
          name="name"
          type="text"
          defaultValue={planDefaults.name}
          onChange={handleChange}
        />
        <label htmlFor="description">Description</label>
        <input
          name="description"
          type="text"
          defaultValue={planDefaults.description}
          onChange={handleChange}
        />
        <label htmlFor="rootTaskIds">Tasks ids</label>
        <input
          name="rootTaskIds"
          type="text"
          defaultValue={taskDefaults.rootTaskIds.map(_ => _.id).toString()}
          onChange={handleChangeArray}
        />
        <button type="submit">Submit</button>
      </form>
    )
  
    function handleSubmit(event) {
      event.preventDefault();
      fetch(((newPlan && PLANS_URL_NEW) || TASK_URL) + `?parentTaskId=${parentTaskId}`, {
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



