import { Component } from 'react';
import NewMealForm from "./NewMealForm";

export default class AddMealPage extends Component {
    render() {
        return (
            <div className="app-content">    
                <h3>Add new meal</h3>
                <NewMealForm/>
            </div>
        )
    }
}