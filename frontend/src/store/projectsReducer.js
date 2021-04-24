import * as actionTypes from './projectsActions'

const initialProjectsState = {
    isModalOpen: false
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
        default:
            return state;
    }
};

export default projectsReducer;