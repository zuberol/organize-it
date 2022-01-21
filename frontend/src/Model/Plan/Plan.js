import { PLANS_URL, PLANS_URL_NEW } from "../../config/backendRoutes";
import { useState } from 'react';
import { useDispatch } from "react-redux";
import { fetchPlans } from "../../store/tasks/actions";
import Agriculture from "@mui/icons-material/Agriculture";

export function PlanForm({
  planDefaults = {
    id: '',
    name: '',
    description: '',
    rootTaskIds: []
  },
  newPlan = false
}) {
  const [planTO, setPlanTO] = useState({ ...planDefaults });
  const dispatch = useDispatch();
  return (
    <>
      <h2>Short Term Plan</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="id" hidden={newPlan}>Plan ID</label>
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
          defaultValue={planDefaults.rootTaskIds.map(_ => _.id).toString()}
          onChange={handleChangeArray}
        />
        <button type="submit">Submit</button>
      </form>
    </>
  )

  function handleSubmit(event) {
    event.preventDefault();
    fetch(((newPlan && PLANS_URL_NEW) || PLANS_URL), {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(planTO)
    })
      .then(() => dispatch(fetchPlans()))
      .catch(e => console.log(e))
  }

  function handleChange(e) {
    setPlanTO({
      ...planTO,
      [e.target.name]: e.target.value
    });
  }

  function handleChangeArray(e) {
    setPlanTO({
      ...planTO,
      [e.target.name]: e.target.value.split(',')
    });
  }

}

export function PlanIcon(props) {
  return <Agriculture {...props} />
}


