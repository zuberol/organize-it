import { TOGGLE_MODAL, INIT_PLANS, INIT_INBOX, NEW_ACTIVE_PLAN } from './actions';

const initialPlansState = {
  isModalOpen: false,
  plans: [
    
  ],
  activePlan: {},
  inboxTasks: [],
  plans: []
};

export const tasksReducer = ( state = initialPlansState, action ) => {
  switch (action.type) {
    case TOGGLE_MODAL: {

      return {
        ...state,
        isModalOpen: !state.isModalOpen
      }
    }
    case INIT_PLANS: {
      return {
        ...state,
        plans: action.plans
      }
    }
    case INIT_PLANS: {
      return {
        ...state,
        plans: action.plans
      }
    }
    case INIT_INBOX: {
      return {
        ...state,
        inboxTasks: action.inboxTasks
      }
    }
    case NEW_ACTIVE_PLAN: 

    const recursiveFind = (array, id) => { // todo okropne, uzyc Ramda.js
      if(!array) return null;
      let found = null;
      for(const element of array) {
        if(element.id == id) return found = element;
        else found = recursiveFind(element.subTasks, id);
        if(found) break;
      }
      return found;
    }
      return {
        ...state,
        activePlan: recursiveFind(state.plans, action.activePlanId) || {}
      }
    default:
      return state;
  }
};