import { Component } from 'react';
import { connect } from 'react-redux';
// import './AddMeal.css';
import { MEAL } from "../../config/backendRoutes";
import IngredientForm from './IngredientForm';
import { CHANGE_MEAL_NAME } from '../../store/meal/actions';


class NewMealForm extends Component {
  createIngredientInput() {
  }

  onChangeHandler(event) {
    this.props.changeMealName(event.target.value);
  }

  sendMealToBackend = () => {

    return fetch(MEAL,{
      method: 'POST',
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(this.props.createdMeal)
    })
  }

  render() {
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
    changeMealName: (name) => dispatch({type: CHANGE_MEAL_NAME, newMealName: name})
  }
};


export default connect(mapStateToProps, mapDispatchToProps)(NewMealForm);