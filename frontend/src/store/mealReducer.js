import Meal from '../Model/Meal';
import * as actionTypes from './mealActions';

const initialMealState = {
    createdMeal: new Meal('Platki z margaryna','')
};

const mealReducer = ( state = initialMealState, action ) => {
    switch (action.type) {
        case actionTypes.DELETE_INGREDIENT: {
            const filteredIngredients = state.createdMeal.ingredients.filter((ingredient, index) => index != action.ingredientToDeleteIndex);
            const updatedMeal = new Meal(
                state.createdMeal.name,
                state.createdMeal.ammount,
                filteredIngredients
            )
            return {
                ...state,
                createdMeal: updatedMeal
            }
        }
        case actionTypes.CHANGE_MEAL_NAME: {
            console.log(action);
            const updatedMeal = new Meal(
                action.newMealName,
                state.createdMeal.ammount,
                state.createdMeal.ingredients
            )
            return {
                ...state,
                createdMeal: updatedMeal
            }
        }
        case actionTypes.CHANGE_INGREDIENT_PROP:
            const updatedIngredients = state.createdMeal.ingredients.map((ingredient, index) => {
                if(index == action.ingredientToUpdateIndex) {
                    const copy = Object.assign({}, ingredient);
                    copy[action.propToChange] = action.newPropValue;
                    return copy;
                }
                else return ingredient;
            });
            console.log(updatedIngredients)
            // const updatedMeal = new Meal(
            //     state.createdMeal.name,
            //     state.createdMeal.ammount,
            //     updatedIngredients
            // )
            return {
                ...state,
                // createdMeal: updatedMeal
            }
        default:
            // console.log('unknown action in mealReducer:', action);
            return state;
    }
};

export default mealReducer;