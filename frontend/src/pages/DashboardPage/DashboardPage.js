import CheckCircleOutlinedIcon from '@mui/icons-material/CheckCircleOutlined';
import HighlightOffIcon from '@mui/icons-material/HighlightOff';
import MovingIcon from '@mui/icons-material/Moving';
import SettingsSuggestIcon from '@mui/icons-material/SettingsSuggest';
import { Box, Grid, Paper } from "@mui/material";
import { DataGrid } from '@mui/x-data-grid';
import * as R from 'ramda';
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { ListItem } from "../../common/presenters/ListItem";
// import { fetchInbox } from "../../store/tasks/actions";
import '../../common/styles/commons.scss';
import { PlanCard } from "../../Model/Plan/PlanPresenters";
import { fetchSnippets } from "../../store/flashcards/actions";
import { fetchInbox, fetchPlans, updateTask } from "../../store/tasks/actions";
import { StyledModal } from '../../common/presenters/StyledModal';
import { TaskForm } from '../../Model/Task';
import { width } from '@mui/system';



export default function DashboardPage() {
    const [snippetId, setSnippet] = useState(0);
    const snippet = useSelector(state => state.flashcardReducer.snippets.find(s => s.id == snippetId), (left, right) => R.equals(left, right));
    const dispatch = useDispatch();
    useEffect(() => dispatch(fetchSnippets()), []);
    useEffect(() => dispatch(fetchInbox()), []);
    useEffect(() =>  dispatch(fetchPlans()), [])


    const inboxTasks = useSelector(state => state.tasksReducer.inboxTasks);
    const plans = useSelector(state => state.tasksReducer.plans, (left, right) => R.equals(left, right));


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
            flex: 0.0,

            getActions: (row) => [
                <CheckCircleOutlinedIcon key="Done" onClick={() => dispatch(updateTask({ id: row.id, done: true }))} label="Done" />,
                <HighlightOffIcon key="Delete" label="Delete" onClick={() => dispatch(updateTask({ id: row.id, archived: true }))} />,
                <MovingIcon key="PriorityUp" onClick={() => {
                    dispatch(updateTask({ id: row.id, priority: Number(row.row.priority) + 10 }))
                }} />
            ]
        },
    ]

    return (
        <main>
            <h1>Dashboard here</h1>
            <Box margin={"20px"} display={"grid"} >
                <Grid  container spacing={1} >
                    <Grid item xs={5} >
                        <div style={{
                            display: 'flex',
                            flexDirection: 'column',
                            justifyContent: "stretch",
                            padding: '10px',
                            backgroundColor: '#f5dfbb',
                            gap: '10px',
                            borderRadius: '4px',
                            minHeight: '300px'
                        }}>
                            {plans.map(plan => <PlanCard plan={plan} key={plan.id} />)}
                        </div>
                    </Grid>
                    <Grid item xs={7} >
                        <div elevation={3} style={{
                             padding: '10px',
                            backgroundColor: '#f5dfbb',
                            minHeight: '300px',
                            borderRadius: '4px'
                        }}>
                            <h2>Inbox tasks</h2>
                            <Paper>
                                <DataGrid
                                    getRowId={row => row.id}
                                    columns={columns}
                                    rows={inboxTasks}
                                    autoHeight
                                    filterModel={{
                                        items: [
                                            {
                                                columnField: 'done',
                                                operatorValue: 'is',
                                                value: 'false'
                                            }
                                        ]
                                    }}
                                />
                            </Paper>
                        </div>
                    </Grid>
                </Grid>
                <Paper elevation={3}>
                    {/* <ListItem snippet={snippet} />
                    <button onClick={() => setSnippet(prev => prev + 1)}>next</button> */}
                </Paper>
            </Box>
            <div className="dashboard-buttons">
                <StyledModal
                    btn={{ icon: <SettingsSuggestIcon fontSize="large" />, title: "new task" }}
                >
                    <TaskForm newTask />
                </StyledModal>

            </div>
        </main>
    )
}