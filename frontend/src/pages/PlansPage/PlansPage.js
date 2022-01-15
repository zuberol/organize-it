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

export default function PlansPage() {
  const dispatch = useDispatch();
  const planList = useSelector(state => state.tasksReducer.plans, (left, right) => R.equals(left, right));
  const activePlan = useSelector(state => state.tasksReducer.activePlan, (left, right) => R.equals(left, right))
  useEffect(() => {
    dispatch(fetchPlans());
  }, []);
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);


  // const treeData = d3.hierarchy(activeRootTask, task => task.subTasks); // todo moze to?
  return (
    <div className="dashboard-wrapper">
      <div className="dashboard-nav">
        {!R.isEmpty(activePlan) && <Button
          onClick={() => dispatch({ type: NEW_ACTIVE_PLAN })}>
          <NamedIcon iconDef={faBackward} caption="return" />
        </Button>}
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
      </div>
      <Container>
        <FsTreeView {...{task: activePlan, dispatch}}/>
        {R.isEmpty(activePlan) && planList.map(
          plan => <FsTreeView key={plan.id} {...{task: plan, dispatch}} />)}
      </Container>
      
      <div className="dashboard-buttons">
        <StyledModal icon={faAddressCard} title="Edit task" isModalOpen={isModalOpen}
          setIsModalOpen={setIsModalOpen}>
          <TaskForm />
        </StyledModal>
        <StyledModal icon={faAddressCard} title="New task" isModalOpen={isModalOpen}
          setIsModalOpen={setIsModalOpen}>
          <TaskForm newTask={true} />
        </StyledModal>
        <Button onClick={() => setIsDrawerOpen(true)}>Plans</Button>
      </div>
    </div>
  )

}

