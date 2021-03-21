import { React, useEffect, useState } from 'react';
import MockTree from '../mock/MockTree';


export function useTaskAdapter(objectToAdapt) {
  const [tree, setTree] = useState({});
  useEffect(() => {
    fetch('http://localhost:8080/root', {
      method: 'GET',
      mode: 'cors'
    })
    .then(res => res.json())
    .then(taskAdapter)
    .then(setTree)
    .catch(err => {
      setTree(MockTree);
    })
  }, [tree == null])
  return tree;
}



function taskAdapter(toAdapt) {
  adapt(toAdapt);
  return toAdapt;
}

/**
 *
 * @param {Response} node
 * @return {Object} adapted object
 */
function adapt(node) {
  if (node == null) return;
  node.id = node.task_id+'';
  delete node.task_id;
  node.children = node.subTasks;
  delete node.subTasks;
  // console.log(node) TODO performance issue
  for (let i=0; i<node.children.length; ++i) {
    taskAdapter(node.children[i]);
  }
}