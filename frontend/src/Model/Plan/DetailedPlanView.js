
import { faFire } from '@fortawesome/free-solid-svg-icons'
import { NamedIcon } from "../../common/presenters/NamedIcon";

export function DetailedPlanView(props) {
    if(!props.plan) return null;
    const { plan } = props;
    return (
        <div>
            <div className="fire-streak">
                <NamedIcon 
                    iconDef={faFire} 
                    caption={`${parseInt(Math.random() * 100)} days`}/>
            </div>
            <div className="plan-info">
                <p>{plan.name}</p>
            </div>
            <div className="control-buttons"></div>
        </div>
    )
}