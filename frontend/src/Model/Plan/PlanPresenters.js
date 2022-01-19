import { Card, CardContent, IconButton } from "@material-ui/core";
import { CardActions } from "@mui/material";
import CardHeader from '@mui/material/CardHeader';
import Collapse from '@mui/material/Collapse';
import { useEffect, useState } from "react";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import LinearProgress from '@mui/material/LinearProgress';
import { PLAN_STATUS_URL } from '../../config/backendRoutes';

import ShareIcon from '@mui/icons-material/Share';
import CheckCircleOutlinedIcon from '@mui/icons-material/CheckCircleOutlined';
import UnfoldMoreOutlinedIcon from '@mui/icons-material/UnfoldMoreOutlined';
import UnfoldLessOutlinedIcon from '@mui/icons-material/UnfoldLessOutlined';
import SmartToyIcon from '@mui/icons-material/SmartToy';
import PrecisionManufacturingIcon from '@mui/icons-material/PrecisionManufacturing';
import InfoIcon from '@mui/icons-material/Info';
import LocalFireDepartmentIcon from '@mui/icons-material/LocalFireDepartment';
import { red } from '@mui/material/colors';
import FsTreeView from "../../pages/PlansPage/fsTreeView/FsTreeView";
import AccountBalanceIcon from '@mui/icons-material/AccountBalance';
import { useDispatch } from "react-redux";
import { updatePlan } from "../../store/tasks/actions";



const myTheme = createTheme({
    components: {
        MuiCardHeader: {
            styleOverrides: {
                content: {
                    display: "flex"
                },
                subheader: {
                    verticalAlign: 'middle', //todo nie dziala
                    color: 'green'
                }
            }
        },
        MuiCard: {
            backgroundColor: 'red'
        }
    }

})

export function PlanCard(props) {
    const { plan } = props;
    const [status, setStatus] = useState(props.status);
    const dispatch = useDispatch();
    const [expandInfo, setExpandInfo] = useState(false);
    const [expandTasks, setExpandTasks] = useState(false);
    // useEffect(() => {
    //     if(plan && plan.id) {
    //         fetch(PLAN_STATUS_URL + `?id=${plan.id}`)
    //         .then(res => res.json())
    //         .then(planStatus => setStatus(planStatus))
    //         .catch(err => setStatus(false)); //todo dopisac to
    //     }
    // }, []);
    console.log(plan)
    return (
        <Card>
            <ThemeProvider theme={myTheme}>
                <div style={{ display: 'flex' }}>
                    <CardHeader
                        onClick={props.onTitleClick}
                        title={plan.name}
                        // subheader={`#${planid}`}
                        style={{ flexGrow: 3, wordBreak: 'break-word' }}
                    />
                    {/* {status && status.streakable && status.streak && <Streak streak={status.streak} />} */}
                    <div style={{ flexGrow: 7 }}>
                        <p>Description:</p>
                        <p style={{ wordBreak: 'break-word' }}>{plan.description}</p>
                    </div>
                </div>
            </ThemeProvider>
            <CardActions>
                <IconButton>
                    <CheckCircleOutlinedIcon/>
                </IconButton>
                <IconButton onClick={() =>  dispatch(updatePlan({
                    id: plan.id, 
                    isArchived: plan.status == null ? true : !plan.status.archived }))}>
                    <AccountBalanceIcon/>
                </IconButton>
                <IconButton  onClick={() => setExpandInfo(prev => !prev)} >
                    <InfoIcon />
                </IconButton>
            </CardActions>
            <CardContent>
                {status && !status.streakable && <LinearProgress variant="determinate" value={(3 / 5) * 100} />}
                <Collapse in={expandInfo} unmountOnExit>
                    {plan.locallySavedURI && <p>{`Locally saved: ${plan.locallySavedURI}`}</p>}
                    {plan.priority && <p>{`Priority: ${plan.priority}`}</p>}
                </Collapse>
                <span hidden={!props.canExpand}>
                    <div
                        onClick={() => setExpandTasks(prev => !prev)}
                        style={{ height: '30px', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                        <span>
                            {expandTasks ? <UnfoldLessOutlinedIcon /> : <UnfoldMoreOutlinedIcon />}
                        </span>
                    </div>
                    {plan.rootTasks &&
                        <Collapse in={expandTasks} unmountOnExit>
                            {plan.rootTasks.map(task => <FsTreeView task={task} key={task.id} />)}
                        </Collapse>}
                </span>
            </CardContent>
        </Card>
    )
}

function Streak(props) {
    return (
        <div style={{ display: "flex", flexDirection: 'column', alignItems: "center" }}>
            <LocalFireDepartmentIcon />
            <span>{props.streak}</span>
        </div>
    )
}