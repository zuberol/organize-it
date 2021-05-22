import { React } from 'react';
import MockTasks from '../../../mock/MockTree2';
import './fsTreeView.scss';
import * as actionTypes from '../../../store/projectsActions';

import { FontAwesomeIcon as Icon } from '@fortawesome/react-fontawesome';
import { faPlusCircle as plus } from '@fortawesome/free-solid-svg-icons';

/**
 *
 * @param {Object} tasks
 * @return {JSX} filled tree container
 */
export default function useFsTreeView(tasks = MockTasks, dispatch) {
  // console.log(tasks)
  return (
  <div className="treeContainer">
    <div>
    {renderLeafs(tasks, dispatch)}
    </div>
  </div>)

  function renderLeafs(root, dispatch) {
  return (<>
    {LeafImpl(root, dispatch)}
    {root.sub_tasks.length > 0 && 
      <div className="branch">
      <div className="vertical-linker"><span></span></div>
      <div className="trunk">
        {root.sub_tasks.map((task, index) => <span key={index}>{renderLeafs(task, dispatch)}</span>)}  
      </div>  
      </div>}
    </>
  )
  }

  function LeafImpl(root, dispatch) {
  return (
    <div className="file">
    <div className="leaf"></div>
    <div className="horizontal-linker"><span></span></div>
    <span style={{"margin-left": "4px"}}>{root.note}</span>
    <button className="transparent" type="button" onClick={() => dispatch(actionTypes.saveTask(
      {
      // '@class': 'com.zuber.organizeit.Model.Task',
      parentTask: root.task_id,
      note: "dziala to?", 
      sub_tasks: []
      }))
    }><Icon icon={plus}/>
      </button>
    </div>
  )
  }
}