import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faStickyNote } from '@fortawesome/free-solid-svg-icons'


// todo poprawic z uwzglednieniem class Plan
function planPresenterFactory(plan) { //todo na later
    // if(plan['@class'] === "com.zuber.organizeit.Model.Plan") {
        const { name, description } = plan;
        return {
            ...plan,
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
    // else return plan;
}