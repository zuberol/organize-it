import { ModalBtn } from "../../common/presenters/ModalBtn";
import FsTreeView from "./fsTreeView/FsTreeView";
import DirectionsRunIcon from '@mui/icons-material/DirectionsRun';
import { TaskForm, TaskIcon } from "../../Model/Task";



export function PlanDetails(props) {
    const { plan } = props;
    const { rootTasks } = plan;
    return (
        <div style={{
            display: 'flex', 
            flexDirection: 'column', 
            gap: '10px', 
            justifyContent: 'center', 
            alignItems: 'stretch',
            
            }}>
            <h1>{plan.name || "some plan"}</h1>
            <div style={{
                            // display: 'flex',
                            // flexDirection: 'column',
                            // justifyContent: "stretch",
                            // gap: '10px',
                            padding: '10px',
                            backgroundColor: '#f5dfbb',
                            borderRadius: '4px',
                            minHeight: '300px'
                        }}>
                <h2>Tasks</h2>
                {rootTasks.map(task => <FsTreeView key={task.id} task={task} />)}
            </div>

            <div style={{
                            // display: 'flex',
                            // flexDirection: 'column',
                            // justifyContent: "stretch",
                            // gap: '10px',
                            padding: '10px',
                            backgroundColor: '#f5dfbb',
                            borderRadius: '4px',
                            minHeight: '300px'
                        }}>
                <h2>hehe</h2>
                <ModalBtn btn={{ icon: <TaskIcon fontSize="medium" />, title: "new", titleSize: '10px' }}>
                    <TaskForm newTask planId={plan.id}/>
                </ModalBtn>
            </div>
        </div>
    )
}