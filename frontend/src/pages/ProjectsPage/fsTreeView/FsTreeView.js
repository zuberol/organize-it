import { React, useState } from 'react';
import './fsTreeView.scss';
import { updateTask } from '../../../store/tasks/actions';
import Task, { TaskForm } from '../../../Model/Task';
import * as R from 'ramda';

import { FontAwesomeIcon as Icon } from '@fortawesome/react-fontawesome';
import { faPlusCircle as plus } from '@fortawesome/free-solid-svg-icons';
import { IconButton, Paper } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import SubdirectoryArrowRightIcon from '@mui/icons-material/SubdirectoryArrowRight';
import { StyledModal } from '../../../common/presenters/StyledModal';

/**
 *
 * @param {Task} task
 * @return {JSX} filled tree container
 */
export default function FsTreeView(props) {
  const { task, dispatch } = props;
  if (task === undefined || typeof task === 'object' && R.isEmpty(task)) return null;
  return (
    <Paper sx={{ padding: "10px", margin: '10px' }}>
      <div className="treeContainer">
        <div>
          <TreeNode task={task} />
        </div>
      </div>
    </Paper>
  );

  function TreeNode(props) {
    const { task, onClick } = props;
    if (task === undefined || typeof task === 'object' && R.isEmpty(task)) return null;
    const [nodeHover, setNodeHover] = useState(false);
    return (
      !task.archived && <>
        <div className="file">
          <div className="leaf"></div>
          <div className="horizontal-linker"><span></span></div>
          <div
            onMouseEnter={() => setNodeHover(true)}
            onMouseLeave={() => setNodeHover(false)}
            style={{
              display: 'flex',
              alignItems: 'center',
              backgroundColor: "salmon",
              borderRadius: '5px',
              padding: '3px'
            }}>
            <span>{task.name || "_"}</span>
            {nodeHover && <div>
              <StyledModal btn={{icon: <EditIcon/>}} form={{taskDefaults: task}} />
              <StyledModal btn={{icon: <SubdirectoryArrowRightIcon/>}} form={{newTask: true}} />
            </div>}

          </div>
        </div>
        <div className="branch">
          <div className="vertical-linker"><span></span></div>
          <div className="trunk">
            {(task.sub_tasks || []).map((subTask, index) => <span key={index}><TreeNode task={subTask} /></span>)}
          </div>
        </div>
      </>
    );


    function newSubtask() {
      dispatch(updateTask({
        ...task,
        sub_tasks: [...task.sub_tasks, Task.empty()]
      }));
    }
  }
}