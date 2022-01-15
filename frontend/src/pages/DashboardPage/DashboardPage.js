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

import { fetchPlans, updateTask } from "../../store/tasks/actions";
import { PlanCard } from "../../Model/Plan/PlanPresenters";



// todo styles
export default function DashboardPage() {
    const [snippetId, setSnippet] = useState(0);
    const snippet = useSelector(state => state.flashcardReducer.snippets.find(s => s.id == snippetId), (left, right) => R.equals(left, right));
    const dispatch = useDispatch();
    useEffect(() =>  dispatch(fetchSnippets()), [])
    useEffect(() =>  dispatch(fetchPlans()), [])

    const inboxTasks = useSelector(state => {
        const inbx = state.tasksReducer.plans.find(plan => plan.name === 'inbox') || {subTasks: []};
        return inbx.subTasks;
    });
    const plans = useSelector(state => state.tasksReducer.plans, (left, right) => R.equals(left, right));


    useEffect(() => {
        dispatch(fetchPlans());
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
                <CheckCircleOutlinedIcon key="Done" onClick={() => dispatch(updateTask({id: row.id, done: true}))} label="Done" />,
                <HighlightOffIcon key="Delete" label="Delete" onClick={() => dispatch(updateTask({id: row.id, archived: true}))}/>,
                <MovingIcon key="PriorityUp" onClick={() => {
                    dispatch(updateTask({id: row.id, priority: Number(row.row.priority)+10}))
                }}/>
            ]
        },
    ]

    return (
        <main>
            <h2>Dashboard here</h2>
            <Box padding={"20px"} display={"grid"}>
                <Grid container columnSpacing={{ xs: 1, sm: 2, md: 3 }} justifyItems={"center"}>
                    <Grid item xs={5} >
                        <Paper sx={{
                            display: 'flex',
                             flexDirection: 'column', 
                             justifyContent: "stretch",
                              padding: '10px',
                            // backgroundColor: 'blue'?
                                }}>
                            {plans.map(plan => <PlanCard plan={plan} key={plan.id}/>)}
                        </Paper>
                    </Grid>
                    <Grid item xs={7} >
                        <Paper elevation={3} sx={{ padding: '10px' }}>
                            <h2>Inbox tasks</h2>
                            <DataGrid
                                getRowId={row => row.id}
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
                <Paper elevation={3}>
                            <ListItem snippet={snippet} />
                            <button onClick={() => setSnippet(prev => prev + 1)}>next</button>
                        </Paper>
            </Box>
        </main>
    )
}