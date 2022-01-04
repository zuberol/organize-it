import { Card, CardContent, IconButton } from "@material-ui/core";
import { CardActions } from "@mui/material";
import CardHeader from '@mui/material/CardHeader';
import Collapse from '@mui/material/Collapse';
import { useEffect, useState } from "react";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import LinearProgress from '@mui/material/LinearProgress';
import { PLAN_STATUS } from '../../config/backendRoutes';

import ShareIcon from '@mui/icons-material/Share';
import CheckCircleOutlinedIcon from '@mui/icons-material/CheckCircleOutlined';
import UnfoldMoreOutlinedIcon from '@mui/icons-material/UnfoldMoreOutlined';
import UnfoldLessOutlinedIcon from '@mui/icons-material/UnfoldLessOutlined';
import SmartToyIcon from '@mui/icons-material/SmartToy';
import PrecisionManufacturingIcon from '@mui/icons-material/PrecisionManufacturing';
import InfoIcon from '@mui/icons-material/Info';
import LocalFireDepartmentIcon from '@mui/icons-material/LocalFireDepartment';
import { red } from '@mui/material/colors';


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
        }
    }
    
})

export function PlanCard(props) {
    const { plan } = props;
    const [ status, setStatus ] =  useState(props.status);
    const [ expanded, setExpanded ] = useState(false);
    const [ expandedMeta, setExpandedMeta ] = useState(false);
    useEffect(() => {
        if(plan && plan.id) {
            fetch(PLAN_STATUS + `?id=${plan.id}`)
            .then(res => res.json())
            .then(planStatus => setStatus(planStatus))
            .catch(err => console.log(err));
        }
    }, []);

    return (
        <Card style={{maxWidth: '300px'}}>
            <ThemeProvider theme={myTheme}>
            <div style={{display: 'flex', alignItems: 'center'}}>
                <CardHeader
                    title={plan.name}
                    // subheader={`#${planid}`}
                />
                {status && status.streakable && status.streak && <Streak streak={status.streak}/>}
            </div>

            </ThemeProvider>
            <CardActions>
                <IconButton>
                    <CheckCircleOutlinedIcon 
                        color="success"
                    />
                </IconButton>
                <IconButton>
                    <InfoIcon />
                </IconButton>
                <IconButton
                    onClick={() => setExpandedMeta(prev => !prev)}
                >
                    <PrecisionManufacturingIcon />
                </IconButton>
                <IconButton
                    onClick={() => setExpanded(prev => !prev)}
                >
                    {expanded ? <UnfoldLessOutlinedIcon /> : <UnfoldMoreOutlinedIcon />}
                </IconButton>
            </CardActions>
            <CardContent>
                {status && !status.streakable && <LinearProgress variant="determinate" value={(3/5)*100} />}
                <Collapse in={expanded} timeout="auto" unmountOnExit>
                    <p>Description:</p>
                    <p>{plan.description}</p>
                </Collapse>
                <Collapse in={expandedMeta} unmountOnExit>
                    <p>Meta:</p>
                    <p>{plan.locallySavedURI && `Locally saved: ${plan.locallySavedURI}`}</p>
                    <p>{`Priority: ${plan.priority}`}</p>
                    <p>{`Project: ${plan.project}`}</p>
                </Collapse>
            </CardContent>
        </Card>
    )
}

function Streak(props) {
    return (
        <div style={{display: "flex", flexDirection: 'column', alignItems: "center"}}>
            <LocalFireDepartmentIcon />
            <span>{props.streak}</span>
        </div>
    )
}