import { React, useState } from 'react';
import './fsTreeView.scss';
import { updateTask } from '../../../store/tasks/actions';
import Task, { TaskForm } from '../../../Model/Task';
import * as R from 'ramda';

import { FontAwesomeIcon as Icon } from '@fortawesome/react-fontawesome';
import { faPlusCircle as plus } from '@fortawesome/free-solid-svg-icons';
import { Button, IconButton, Paper } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';
import SubdirectoryArrowRightIcon from '@mui/icons-material/SubdirectoryArrowRight';
import BookmarkRemoveIcon from '@mui/icons-material/BookmarkRemove';
import { StyledModal } from '../../../common/presenters/StyledModal';
import { useDispatch } from 'react-redux';
import { NEW_ACTIVE_PLAN } from "../../../store/tasks/actions";

/**
 *
 * @param {Task} task
 * @return {JSX} filled tree container
 */
export default function FsTreeView(props) {
  const dispatch = useDispatch();
  const { task } = props;
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
            <span
              onClick={() => dispatch({ type: NEW_ACTIVE_PLAN, activePlanId: task.id })}
            >{task.name || "_"}</span>
            {nodeHover && <div>
              <StyledModal btn={{ icon: <EditIcon /> }} form={{ taskDefaults: task }} />
              <StyledModal btn={{ icon: <SubdirectoryArrowRightIcon /> }} form={{ newTask: true, parentTaskId: task.id }} />
              <BookmarkRemoveIcon onClick={() => dispatch(updateTask({ id: task.id, archived: true }))} />
            </div>}

          </div>
        </div>
        <div className="branch">
          <div className="vertical-linker"><span></span></div>
          <div className="trunk">
            {(task.subTasks || []).map((subTask, index) => <span key={index}><TreeNode task={subTask} /></span>)}
          </div>
        </div>
      </>
    );


    function newSubtask() {
      dispatch(updateTask({
        ...task,
        subTasks: [...task.subTasks, Task.empty()]
      }));
    }
  }
}