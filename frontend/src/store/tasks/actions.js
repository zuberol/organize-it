import { dispatch } from 'd3-dispatch';
import { TASK_URL, PLANS_URL, TASK_INBOX_URL, PLAN_STATUS_URL } from '../../config/backendRoutes';
import { stripToDto } from "../../Model/Task";

export const INIT_PLANS = "INIT_PLANS";
export const INIT_INBOX = "INIT_INBOX";
export const NEW_ACTIVE_PLAN = "NEW_ACTIVE_PLAN";
export const TOGGLE_MODAL = "TOGGLE_MODAL";

export function updateTask(task) {
  return (dispatch) => {
    fetch(TASK_URL, {
      method: 'POST',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(stripToDto(task))
    })
    .catch(err => {
      console.error(err);
    })
    .then(() => {
      dispatch(fetchPlans());
    })
    .catch(err => {
      console.error("fetching plans failed: ", err);
    })
  }
}

export function fetchPlans() {
  return (dispatch) => {
    fetch(PLANS_URL)
    .then(res => res.json())
    .then((plans) => {
      dispatch({type: INIT_PLANS, plans});
    })
    .catch(err => {
      console.error(err, "Backend doesn't respond");
      dispatch({type: INIT_PLANS, plans: []});
    })
  }
}

