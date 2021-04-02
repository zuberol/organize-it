import { Component } from 'react';
import { connect } from 'react-redux';
import ListItem from './ListItem';
import * as actionTypes from '../../store/actions';

class AvailableFlashcardSetsPage extends Component {

    componentDidMount() {
        this.props.fetchFCSETS();
    }

    render() {
        let fcSets = this.props.fcsets || null;
        if(fcSets != null){
            console.log(fcSets)
            fcSets = fcSets.map((fcSet, index) =>
            <ListItem
                key={index}
                name={fcSet.name}
                googleSheetId={fcSet.googleSheetId}
                deleteItem={() => this.props.onClickDelete(index)}
            />
            );
        }
        
        return (
            <div className="app-content">    
                <h3>Available Flashcard Sets</h3>
                <div className="page-content">
                    {fcSets || "no sets available"}
                </div>
            </div>
        )
    }
}

const mapStateToProps = state => {
    const { flashcardReducer: flashcardGlobalState } = state;
    return {
        fcsets: flashcardGlobalState.fcsets
    }
};

const mapDispatchToProps = dispatch => {
    return {
        onClickDelete: (id) => dispatch({type: actionTypes.DELETE_ITEM, itemToDeleteID: id}),
        fetchFCSETS: () => dispatch(actionTypes.fetchFCSETS())
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(AvailableFlashcardSetsPage);