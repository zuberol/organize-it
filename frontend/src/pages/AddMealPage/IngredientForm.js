// import './AddMeal.css';
import { Component } from 'react';
import { connect } from 'react-redux';
import deleteImg from '../../assets/delete.png';
import * as actionTypes from '../../store/mealActions';


class IngredientForm extends Component {
    state = { isSubmited: false }

    submitIngredient() {
        this.setState({
            isSubmited: !this.state.isSubmited
        })
    }

    onChangeHandler(event) {
        console.log(event.target.name);
        this.props.changePropValue(this.props.index, event.target.name, event.target.value);
    }

    render() {
        console.log(this.state)
        return (
            <div className={this.state.isSubmited == true ? "submittedIngredient" : "notSubmittedIngredient"}>
                <h6>new ingredient</h6>
                <p>{this.props.index}</p>
                <p>{this.props.ingredient.name}</p>
                <label htmlFor="ingredientName">ingredient name:</label>
                    <input name="ingredientName" type="text" value={this.props.ingredient.name} onChange={this.onChangeHandler.bind(this)}></input>
                    <img src={deleteImg} onClick={() => this.props.onClickDelete(this.props.index)}></img>
                <label htmlFor="ammount">ingredient ammount:</label>
                    <input name="ammount" type="text" value={this.props.ingredient.ammount} onChange={this.onChangeHandler.bind(this)}></input>
                <button type="button" onClick={this.submitIngredient.bind(this)}>submit ingredient</button>
            </div>
        )
    }
}

const mapDispatchToProps = dispatch => {
    return {
        onClickDelete: (index) => dispatch({type: actionTypes.DELETE_INGREDIENT, ingredientToDeleteIndex: index}),
        changePropValue: (ingredientFormIndex, propName, val) => dispatch({
            type: actionTypes.CHANGE_INGREDIENT_PROP, 
            ingredientToUpdateIndex: ingredientFormIndex, 
            propToChange: propName,
            newPropValue: val
        })
    }
};

export default connect(null, mapDispatchToProps)(IngredientForm);