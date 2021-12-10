import { React } from 'react';
import './fsTreeView.scss';
import { updateTask } from '../../../store/tasks/actions';
import Task from '../../../Model/Task';

import { FontAwesomeIcon as Icon } from '@fortawesome/react-fontawesome';
import { faPlusCircle as plus } from '@fortawesome/free-solid-svg-icons';

/**
 *
 * @param {Task} task
 * @return {JSX} filled tree container
 */
export default function FsTreeView(task, dispatch) {
  if (typeof task === 'object' && Object.keys(task).length === 0) return <div></div>;
  return (
    <div className="treeContainer">
        <div>
        {TreeNode(task, dispatch)}
        </div>
    </div>
  );

  function TreeNode(task) {
    let jsx = <span />;
    if (task) {
      jsx = (
        !task.archived && <>
          <div className="file">
            <div className="leaf"></div>
            <div className="horizontal-linker"><span></span></div>
              <div style={{backgroundColor:"salmon", borderRadius: '5px'}}>
                <span># {task.task_id}</span>
                <span>Name: {task.name}</span>
                <span>About: {task.description}</span>
              </div>
          </div>
          <div className="branch">
            <div className="vertical-linker"><span></span></div>
            <div className="trunk">
              {(task.sub_tasks || []).map((subTask, index) => <span key={index}>{TreeNode(subTask)}</span>)}
            </div>
          </div>
        </>
      );
    }
    return jsx;

    function newSubtask() {
      dispatch(updateTask({
        ...task,
        sub_tasks: [...task.sub_tasks, Task.empty()]
      }));
    }

  }
}