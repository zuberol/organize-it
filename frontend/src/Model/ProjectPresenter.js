import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faStickyNote } from '@fortawesome/free-solid-svg-icons'


// todo poprawic z uwzglednieniem class Project
function projectPresenterFactory(project) { //todo na later
    // if(project['@class'] === "com.zuber.organizeit.Model.Project") {
        const { name, description } = project;
        return {
            ...project,
            presenter: function () {
                return (
                    <div style={{display: 'flex', 'flexDirection': 'row', 'alignItems': 'center', margin: '20px'}}>
                        <FontAwesomeIcon icon={faStickyNote} />
                        {/*<h4>{name}</h4>*/}
                        <p>{name}</p>
                    </div>
                )
            }
        };
    // } // todo
    // else return project;
}