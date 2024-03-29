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
    const dispatch = useDispatch();
    useEffect(() =>  dispatch(fetchSnippets()), [])
    const inboxTasks = useSelector(state => {
        const inbx = state.tasksReducer.projects.find(project => project.name === 'inbox') || {subTasks: []};
        return inbx.subTasks;
    });
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
            field: 'priority',
            headerName: 'Priority',
            flex: 0.1,
            type: 'number'
        },
        {
            field: 'done',
            headerName: 'is done',
            flex: 0.1,
            minWidth: 10,
            type: 'boolean',
            hide: true
        },
        {
            field: 'actions',
            headerName: 'Actions',
            type: 'actions',
            flex: 0.05,

            getActions: (row) => [
                <CheckCircleOutlinedIcon key="Done" onClick={() => dispatch(updateTask({taskId: row.id, isDone: true}))} label="Done" />,
                <HighlightOffIcon key="Delete" label="Delete" onClick={() => dispatch(updateTask({taskId: row.id, archived: true}))}/>,
                <MovingIcon key="PriorityUp" onClick={() => {
                    dispatch(updateTask({taskId: row.id, priority: Number(row.row.priority)+10}))
                }}/>
            ]
        },
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
                                getRowId={row => row.taskId}
                                columns={columns}
                                rows={inboxTasks}
                                autoHeight
                                filterModel={{items: [
                                    {
                                        columnField: 'done',
                                        operatorValue: 'is',
                                        value: 'false'
                                    }
                                ]}}
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