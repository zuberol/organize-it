import React from 'react';
import MockTasks from '../../../mock/MockTree2';
import './fsTreeView.scss';

/**
 *
 * @param {Object} tasks
 * @return {JSX} filled tree container
 */
export default function useTreeView(tasks = MockTasks) {

  return (
    <div className="treeContainer">
      <div>
        {renderLeafs(tasks)}
      </div>
    </div>)

  function renderLeafs(root) {
    return (<>
        {LeafImpl(root)}
        {root.sub_tasks.length > 0 && 
          <div className="branch">
            <div className="vertical-linker"><span></span></div>
            <div className="trunk">
              {root.sub_tasks.map(task => renderLeafs(task))}  
            </div>  
          </div>}
      </>
    )
  }

  function LeafImpl(root) {
    return (
      <div className="file">
        <div className="leaf"></div>
        <div className="horizontal-linker"><span></span></div>
        <span>{root.note}</span>
      </div>
    )
  }
}