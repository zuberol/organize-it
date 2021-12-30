import { Card, CardContent, IconButton } from "@material-ui/core";
import { CardActions } from "@mui/material";
import CardHeader from '@mui/material/CardHeader';
import Collapse from '@mui/material/Collapse';
import { useState } from "react";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import LinearProgress from '@mui/material/LinearProgress';

import ShareIcon from '@mui/icons-material/Share';
import CheckCircleOutlinedIcon from '@mui/icons-material/CheckCircleOutlined';
import UnfoldMoreOutlinedIcon from '@mui/icons-material/UnfoldMoreOutlined';
import UnfoldLessOutlinedIcon from '@mui/icons-material/UnfoldLessOutlined';
import SmartToyIcon from '@mui/icons-material/SmartToy';
import PrecisionManufacturingIcon from '@mui/icons-material/PrecisionManufacturing';
import InfoIcon from '@mui/icons-material/Info';

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

export function ProjectCard(props) {

    const { project } = props;
    const [expanded, setExpanded] = useState(false);
    const [expandedMeta, setExpandedMeta] = useState(false);

    return (
        <Card>
            <ThemeProvider theme={myTheme}>
                <CardHeader
                    title={project.name}
                    subheader={`#${project.taskId}`}
                />
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
                <LinearProgress variant="determinate" value={(3/5)*100} />
                <Collapse in={expanded} timeout="auto" unmountOnExit>
                    <p>Description:</p>
                    <p>{project.description}</p>
                </Collapse>
                <Collapse in={expandedMeta} unmountOnExit>
                    <p>Meta:</p>
                    <p>{project.locallySavedURI && `Locally saved: ${project.locallySavedURI}`}</p>
                    <p>{`Priority: ${project.priority}`}</p>
                    <p>{`Project: ${project.project}`}</p>
                </Collapse>
            </CardContent>
        </Card>
    )
}