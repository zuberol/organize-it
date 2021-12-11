
import { faFire } from '@fortawesome/free-solid-svg-icons'
import { NamedIcon } from "../../common/presenters/NamedIcon";

export function DetailedProjectView(props) {
    if(!props.project) return null;
    const { project } = props;
    return (
        <div>
            <div className="fire-streak">
                <NamedIcon 
                    iconDef={faFire} 
                    caption={`${parseInt(Math.random() * 100)} days`}/>
            </div>
            <div className="project-info">
                <p>{project.name}</p>
            </div>
            <div className="control-buttons"></div>
        </div>
    )
}