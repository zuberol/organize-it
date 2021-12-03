import { TOGGLE_MODAL, INIT_PROJECTS, INIT_INBOX, NEW_ACTIVE_PROJECT } from './actions';

const initialProjectsState = {
  isModalOpen: false,
  projects: [
    
  ],
  activeProject: {},
  inboxTasks: []
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
      return {
        ...state,
        activeProject: state.projects.find(project => project.task_id == action.activeProjectId) || {}
      }
    default:
      return state;
  }
};