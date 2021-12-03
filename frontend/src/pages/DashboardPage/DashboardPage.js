import { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { GenericList } from "../../common/presenters/ListView";
import { fetchInbox } from "../../store/tasks/actions";
import '../../common/styles/commons.scss';
import * as R from 'ramda';


// todo styles
export default function DashboardPage() {
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
                    <h4>inbox</h4>
                    <div>
                        {<GenericList data={inboxTasks}>

                        </GenericList>}
                    </div>
                </div>
            </div>
        </main>
    )
}