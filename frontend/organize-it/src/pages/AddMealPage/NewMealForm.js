import { Component } from 'react';
import { connect } from 'react-redux';
import './AddMeal.css';
import { BACKEND_BASE_URL } from "../../utils/config";
import Ingredient from '../../Model/Ingredient';
import IngredientForm from './IngredientForm';
import * as actionTypes from '../../store/mealActions';


class NewMealForm extends Component {
    createIngredientInput() {
        console.log("input created") 
    }

    onChangeHandler(event) {
        this.props.changeMealName(event.target.value);
    }

    sendMealToBackend = () => {
        console.log('sending ...', this.props.createdMeal)

        return fetch(new URL('/meal', BACKEND_BASE_URL),{
            method: 'POST',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.props.createdMeal)
        })
    }

    render() {
        console.log(this.props.createdMeal.name)
        const ingredientList = this.props.createdMeal.ingredients.map((ingredient, index) => <IngredientForm ingredient={ingredient} key={index} index={index}/>);
        return (
            <form id="mealCreator">
                <label htmlFor="mealName">Meal name</label>
                    <h3>{this.props.createdMeal.name}</h3>
                    <input name="mealName" value={this.props.createdMeal.name} placeholder="Type meal name" onChange={this.onChangeHandler.bind(this)}></input>
                {ingredientList}
                <button type="button" onClick={this.createIngredientInput()}>+</button>
                <button type="button" onClick={this.sendMealToBackend}>Submit</button>
            </form>
        )
    }
}



const mapStateToProps = state => {
    const { mealReducer: mealGlobalState } = state;
    return {
        createdMeal: mealGlobalState.createdMeal
    }
};

const mapDispatchToProps = dispatch => {
    return {
        changeMealName: (name) => dispatch({type: actionTypes.CHANGE_MEAL_NAME, newMealName: name})
    }
};


export default connect(mapStateToProps, mapDispatchToProps)(NewMealForm);