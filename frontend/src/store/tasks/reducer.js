import { TOGGLE_MODAL, INIT_PROJECTS, INIT_INBOX, NEW_ACTIVE_PROJECT } from './actions';

const initialProjectsState = {
  isModalOpen: false,
  projects: [
    
  ],
  activeProject: {},
  inboxTasks: [],
  plans: []
};

export const tasksReducer = ( state = initialProjectsState, action ) => {
  switch (action.type) {
    case TOGGLE_MODAL: {

      return {
        ...state,
        isModalOpen: !state.isModalOpen
      }
    }
    case INIT_PROJECTS: {
      return {
        ...state,
        projects: action.projects
      }
    }
    case INIT_INBOX: {
      return {
        ...state,
        inboxTasks: action.inboxTasks
      }
    }
    case NEW_ACTIVE_PROJECT: 

    const recursiveFind = (array, id) => { // todo okropne, uzyc Ramda.js
      if(!array) return null;
      let found = null;
      for(const element of array) {
        if(elementid == id) return found = element;
        else found = recursiveFind(element.subTasks, id);
        if(found) break;
      }
      return found;
    }
      return {
        ...state,
        activeProject: recursiveFind(state.projects, action.activeProjectId) || {}
      }
    default:
      return state;
  }
};