import { React, useState } from 'react';
import MockTasks from '../../../mock/MockTree2';
import './fsTreeView.scss';
import * as actionTypes from '../../../store/projectsActions';
import Task from '../../../Model/Task';

import { FontAwesomeIcon as Icon } from '@fortawesome/react-fontawesome';
import { faPlusCircle as plus } from '@fortawesome/free-solid-svg-icons';

/**
 *
 * @param {Project} project
 * @return {JSX} filled tree container
 */
export default function FsTreeView(project, dispatch) {
  if (typeof project === 'object' && Object.keys(project).length === 0) return <div>{"Invalid project data"}</div>;
  return (
    <div className="treeContainer">
        <div>
        {TreeNode(project.root_task, dispatch)}
        </div>
    </div>
  );

  function TreeNode(task) {
    let jsx = <span />;
    if (task) {
      jsx = (
        <>
          <div className="file">
            <div className="leaf"></div>
            <div className="horizontal-linker"><span></span></div>
            <p style={{ "marginLeft": "4px" }} >{task.note || ""}</p>
            <input defaultValue={task.note || ""} onBlur={updateTaskNote} style={{ "marginLeft": "4px" }} />
            <button className="transparent" type="button" onClick={newSubtask}><Icon icon={plus} /></button>
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


    function updateTaskNote(e) {
      dispatch(actionTypes.updateTask({
        task_id: task.task_id, 
        note: e.target.value,
        sub_tasks: [...task.sub_tasks],
        '@class': Task['@class']
      }));
    }

    function newSubtask() {
      dispatch(actionTypes.updateTask({
        task_id: task.task_id, 
        sub_tasks: [...task.sub_tasks, Task.empty()],
        '@class': Task['@class']
      }));
    }

  }
}