import * as actionTypes from './projectsActions'
import MockTasks from '../mock/MockTree2';

const initialProjectsState = {
    isModalOpen: false, //todo refactor to
    projects: [
        MockTasks
    ]
};

const projectsReducer = ( state = initialProjectsState, action ) => {
    switch (action.type) {
        case actionTypes.OPEN_MODAL: {

            return {
                ...state,
                isModalOpen: true
            }
        }
        case actionTypes.CLOSE_MODAL: {

            return {
                ...state,
                isModalOpen: false
            }
        }
        case actionTypes.INIT_PROJECTS: {
            console.log(action.projects)
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