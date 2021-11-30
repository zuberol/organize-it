import { OPEN_MODAL, CLOSE_MODAL, INIT_PROJECTS } from './actions';
import MockTasks from '../../mock/MockTree2';

const initialProjectsState = {
  isModalOpen: false,
  projects: [
    MockTasks
  ]
};

const projectsReducer = ( state = initialProjectsState, action ) => {
  switch (action.type) {
    case OPEN_MODAL: {

      return {
        ...state,
        isModalOpen: true
      }
    }
    case CLOSE_MODAL: {

      return {
        ...state,
        isModalOpen: false
      }
    }
    case INIT_PROJECTS: {
      return {
        ...state,
        projects: action.projects
      }
    }
    default:
      return state;
  }
};

export default projectsReducer;