import { Component } from 'react';
import './../static/css/commons.css';

export default class Header extends Component {
    render() {
        return (
            <header>
                <a className="logo" href="/">
                    <h5 className="logo-title">organize-it</h5>
                </a>
                <a className="" href="/modifySets">
                    <h5 className="logo-title">modify Sets</h5>
                </a>
                <a className="" href="/meal/add">
                    <h5 className="logo-title">add meal</h5>
                </a>
            </header>
        )
    }
}