import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { NEW_ACTIVE_PLAN } from "../../store/tasks/actions";
import { fetchPlans } from '../../store/tasks/actions';
import FsTreeView from './fsTreeView/FsTreeView';
import './Plans.scss'
import * as R from 'ramda';
import Drawer from '@mui/material/Drawer';
import Button from '@mui/material/Button';

import { StyledModal } from '../../common/presenters/StyledModal'
import { faAddressCard, faBackward } from '@fortawesome/free-solid-svg-icons'
import { TaskForm } from "../../Model/Task";
import { DetailedPlanView } from "../../Model/Plan/DetailedPlanView";
import { NamedIcon } from '../../common/presenters/NamedIcon';
import { Container } from '@mui/material';
import FiberNewIcon from '@mui/icons-material/FiberNew';
import { PlanForm } from '../../Model/Plan/Plan';
import AgricultureIcon from '@mui/icons-material/Agriculture';
import AirplanemodeActiveIcon from '@mui/icons-material/AirplanemodeActive';
import DirectionsRunIcon from '@mui/icons-material/DirectionsRun';
import { PlanCard } from '../../Model/Plan/PlanPresenters'


export default function PlansPage() {
  const dispatch = useDispatch();
  const planList = useSelector(state => state.tasksReducer.plans, (left, right) => R.equals(left, right));
  const activePlan = useSelector(state => state.tasksReducer.activePlan, (left, right) => R.equals(left, right))
  useEffect(() => {
    dispatch(fetchPlans());
  }, []);
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);
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
        {!R.isEmpty(activePlan) && <Button
          onClick={() => dispatch({ type: NEW_ACTIVE_PLAN })}>
          <NamedIcon iconDef={faBackward} caption="return" />
        </Button>}
      </div>

      <div style={{display: 'flex', gap: '30px', flexDirection: 'column', padding: '30px'}}>
        <FsTreeView {...{ task: activePlan, dispatch }} />
        {R.isEmpty(activePlan) && planList.map(
          plan => <PlanCard  onTitleClick={() => dispatch({type: NEW_ACTIVE_PLAN, activePlanId: plan.id})} canExpand key={plan.id} plan={plan}/>)}
      </div>

      <div className="dashboard-buttons">
        <StyledModal
          btn={{ icon: <DirectionsRunIcon fontSize="large" />, title: "new" }}
        >
        </StyledModal>
        <StyledModal

          isModalOpen={onOffModals.modifyPlan}
          setIsModalOpen={() => setOnOffModals({ ...onOffModals, modifyPlan: !onOffModals.modifyPlan })}
          btn={{ icon: <AgricultureIcon fontSize="large" />, title: "new" }}
        >
          <PlanForm newPlan />
        </StyledModal>
        <StyledModal
          btn={{ icon: <AirplanemodeActiveIcon fontSize="large" />, title: "new" }}
        >
        </StyledModal>
        <Button onClick={() => setIsDrawerOpen(true)}>Plans</Button>
      </div>

      <Drawer anchor='left' open={isDrawerOpen} onClose={() => setIsDrawerOpen(!isDrawerOpen)}>
          {<ul>
            {planList.map((plan) =>
              <li
                onClick={() => {
                  dispatch({ type: NEW_ACTIVE_PLAN, activePlanId: plan.id });
                  setIsDrawerOpen(false);
                }}
                key={plan.id}>
                {plan.name}
              </li>
            )}
          </ul>}
        </Drawer>
    </main>
  )

}

