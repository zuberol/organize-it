import { Label } from "@material-ui/icons";
import styled from 'styled-components'

const Label = styled.button`
  background: transparent;
  border-radius: 3px;
  border: 2px solid palevioletred;
  color: palevioletred;
  margin: 0 1em;
  padding: 0.25em 1em;

  ${props =>
    props.primary &&
    css`
      background: palevioletred;
      color: white;
    `};
`


export function Label(captioned) {
    const { name, description } = captioned;
    return (
        <div style={{display: 'flex', 'flexDirection': 'column', 'align-items': 'center', margin: '20px'}}>
            <FontAwesomeIcon icon={faStickyNote} />
            <h4>{name}</h4>
            <p>{description}</p>
        </div>
    );
}


