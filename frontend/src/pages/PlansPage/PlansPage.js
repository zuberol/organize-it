import { faBackward } from '@fortawesome/free-solid-svg-icons';
import Button from '@mui/material/Button';
import Drawer from '@mui/material/Drawer';
import * as R from 'ramda';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { NamedIcon } from '../../common/presenters/NamedIcon';
import { ModalBtn } from '../../common/presenters/ModalBtn';
import { LongTermPlanIcon } from '../../Model/LongTermPlan';
import { PlanForm, PlanIcon } from '../../Model/Plan/Plan';
import { PlanCard } from '../../Model/Plan/PlanPresenters';
import { TaskForm, TaskIcon } from "../../Model/Task";
import { fetchPlans, NEW_ACTIVE_PLAN } from "../../store/tasks/actions";
import { PlanDetails } from './PlanDetails';
import './Plans.scss';



export default function PlansPage() {
  const dispatch = useDispatch();
  const planList = useSelector(state => state.tasksReducer.plans, (left, right) => R.equals(left, right));
  const focusedPlan = useSelector(state => state.tasksReducer.activePlan, (left, right) => R.equals(left, right))
  useEffect(() => {
    dispatch(fetchPlans());
  }, []);
  const [onOffModals, setOnOffModals] = useState({
    newTask: false,
    modifyTask: false,
    newPlan: false,
    modifyPlan: false
  }); 


  // const treeData = d3.hierarchy(activeRootTask, task => task.subTasks); // todo moze to?
  return (
    <main>
      <div className="dashboard-nav">
        {!R.isEmpty(focusedPlan) && <Button
          onClick={() => dispatch({ type: NEW_ACTIVE_PLAN })}>
          <NamedIcon iconDef={faBackward} caption="return" />
        </Button>}
      </div>

      <div style={{ display: 'flex', gap: '30px', flexDirection: 'column', padding: '30px' }}>
        {R.isEmpty(focusedPlan) ?
          planList.map(plan => <PlanCard
            onTitleClick={() => dispatch({ type: NEW_ACTIVE_PLAN, activePlanId: plan.id })}
            canExpand key={plan.id}
            plan={plan} />)
          : <PlanDetails plan={focusedPlan} />
        }
      </div>

      <div className="dashboard-buttons">
        <ModalBtn
          btn={{ icon: <TaskIcon fontSize="large" />, title: "new" }}
        >
          <TaskForm newTask />
        </ModalBtn>
        <ModalBtn
          btn={{ icon: <PlanIcon fontSize="large" />, title: "new" }}
        >
          <PlanForm newPlan />
        </ModalBtn>
        <ModalBtn
          btn={{ icon: <LongTermPlanIcon fontSize="large" />, title: "new" }}
        >
        </ModalBtn>
      </div>
    </main>
  )

}

