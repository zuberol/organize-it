import { Component } from 'react';

export default class FlashcardArticle extends Component {
  render() {
    return (
        <article>
          <h4>{this.props.name}</h4>
          <p>{this.props.googleSheetId}</p>
          <span className="delete-button" onClick={this.props.deleteItem}>
            <img src={deleteImg}></img>
          </span>
        </article>
    )
  }
}