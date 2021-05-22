import Ingredient from './Ingredient'
import { mockIngredients } from '../mock/MockIngredients'

export default class Meal {
  constructor(name, ammount, ingredients = mockIngredients) {
  this.name = name;
  this.ammount = ammount;

  //mock
  this.ingredients = ingredients;
  }
}