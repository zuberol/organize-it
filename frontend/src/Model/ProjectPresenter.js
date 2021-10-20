import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faStickyNote } from '@fortawesome/free-solid-svg-icons'


// todo poprawic z uwzglednieniem class Project
export default function projectPresenterFactory(project) {
    // if(project['@class'] === "com.zuber.organizeit.Model.Project") {
        const { title, description } = project;
        return {
            ...project,
            presenter: function () {
                return (
                    <div>
                        <FontAwesomeIcon icon={faStickyNote} />
                        <h4>{title}</h4>
                        <p>{description}</p>
                    </div>
                )
            }
        };
    // } // todo
    // else return project;
}