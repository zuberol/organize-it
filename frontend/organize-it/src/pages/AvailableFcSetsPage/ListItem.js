import { Component } from 'react';
import './commons.css';
import deleteImg from './../../assets/delete.png';

export default class ListItem extends Component {
    render() {
        return (
                <article>
                    <h4>{this.props.name}</h4>
                    <p>{this.props.googleSheetId}</p>
                    <span className="delete-button" onClick={this.props.deleteItem}>
                        <img src={deleteImg}></img>
                    </span>
                    <a href={`/modifyFcSet/${this.props.fcSetId}`}>
                        modify it
                    </a>
                </article>
        )
    }
}