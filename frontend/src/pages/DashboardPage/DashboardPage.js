import { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { GenericList } from "../../common/presenters/ListView";
import { fetchInbox } from "../../store/tasks/actions";
import '../../common/styles/commons.scss';
import * as R from 'ramda';
import { ListItem } from "../../common/presenters/ListItem";
import { fetchSnippets } from "../../store/flashcards/actions";


// todo styles
export default function DashboardPage() {
    const [snippetId, setSnippet] = useState(0);
    const snippet = useSelector(state => state.flashcardReducer.snippets.find(s => s.id == snippetId), (left, right) => R.equals(left, right));
    useEffect(() => { dispatch(fetchSnippets()) })
    const inboxTasks = useSelector(state => state.tasksReducer.inboxTasks, (left, right) => R.equals(left, right));
    const dispatch = useDispatch();
    useEffect(() => {
       dispatch(fetchInbox());
    }, []);

    return (
        <main className="">
            <h3>Dashboard here</h3>
            <div className="">
                <div>
                    <ListItem snippet={snippet}/>
                    <button onClick={() => setSnippet(prev => prev+1)}>next</button>
                </div>
                <div>
                    <h4>inbox</h4>
                    <div>
                        {<GenericList data={inboxTasks}/>}
                    </div>
                </div>
            </div>
        </main>
    )
}