import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faStickyNote } from '@fortawesome/free-solid-svg-icons'


// todo poprawic z uwzglednieniem class Project
export default function projectPresenterFactory(project) {
        const { name, description } = project;
        return {
            ...project,
            presenter: function () {
                return (
                    <div style={{display: 'flex', 'flexDirection': 'column', 'align-items': 'center', margin: '20px'}}>
                        <FontAwesomeIcon icon={faStickyNote} />
                        <h4>{name}</h4>
                        <p>{description}</p>
                    </div>
                )
            }
        };
}