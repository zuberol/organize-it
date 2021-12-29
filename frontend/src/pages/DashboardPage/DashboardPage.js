import { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { GenericList } from "../../common/presenters/ListView";
// import { fetchInbox } from "../../store/tasks/actions";
import '../../common/styles/commons.scss';
import * as R from 'ramda';
import { ListItem } from "../../common/presenters/ListItem";
import { fetchSnippets } from "../../store/flashcards/actions";
import { DataGrid } from '@mui/x-data-grid';

import MovingIcon from '@mui/icons-material/Moving';
import SmartToyIcon from '@mui/icons-material/SmartToy';
import CheckCircleOutlinedIcon from '@mui/icons-material/CheckCircleOutlined';
import HighlightOffIcon from '@mui/icons-material/HighlightOff';
import { Box, Grid, Paper } from "@mui/material";
import { Container } from "@material-ui/core";
import { padding } from "@mui/system";

import { fetchProjects, updateTask } from "../../store/tasks/actions";



// todo styles
export default function DashboardPage() {
    const [snippetId, setSnippet] = useState(0);
    const snippet = useSelector(state => state.flashcardReducer.snippets.find(s => s.id == snippetId), (left, right) => R.equals(left, right));
    useEffect(() => { dispatch(fetchSnippets(), []) })
    const inboxTasks = useSelector(state => {
        const inbx = state.tasksReducer.projects.find(project => project.name === 'inbox') || {sub_tasks: []};
        return inbx.sub_tasks;
    });
    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(fetchProjects());
    }, []);
    const columns = [
        {
            field: "name",
            headerName: 'Name',
            flex: 0.3,
            minWidth: 50
        },
        {
            field: 'actions',
            headerName: 'Actions',
            type: 'actions',
            getActions: (row) => [
                <CheckCircleOutlinedIcon key="Done" onClick={() => console.log(row.id)} label="Done" />,
                <HighlightOffIcon key="Delete" label="Delete" onClick={() => dispatch(updateTask({task_id: row.id, is_archived: true}))}/>,
                <MovingIcon key="PriorityUp" onClick={() => {
                    dispatch(updateTask({task_id: row.id, priority: Number(row.row.priority_point)+10}))
                }}/>
            ]
        },
        {
            field: 'priority_point',
            headerName: 'Priority',
            flex: 0.1,
            minWidth: 10,
            type: 'number'
        }
    ]

    return (
        <main className="">
            <h2>Dashboard here</h2>
            <Box padding={"20px"}>
                <Grid container columnSpacing={{ xs: 1, sm: 2, md: 3 }} justifyItems={"center"}>
                    <Grid item xs={4} >
                        <Paper elevation={3}>
                            <ListItem snippet={snippet} />
                            <button onClick={() => setSnippet(prev => prev + 1)}>next</button>
                        </Paper>
                    </Grid>
                    <Grid item xs={8} >
                        <Paper elevation={3} sx={{ padding: '10px' }}>
                            <h2>Inbox tasks</h2>
                            <DataGrid
                                getRowId={row => row.task_id}
                                columns={columns}
                                rows={inboxTasks}
                                autoHeight
                            />
                        </Paper>
                    </Grid>
                    {/* <div>
                        {<GenericList data={inboxTasks}/>}
                    </div> */}
                </Grid>
            </Box>
        </main>
    )
}