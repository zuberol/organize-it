import { React, useEffect, useState } from 'react';
import MockTree from '../mock/MockTree2';


// export function useTaskAdapter(objectToAdapt = MockTree) {
//   const [tree, setTree] = useState(
//   {
//     "task_id": 1,
//     "note": "",
//     "parentTask": null,
//     "sub_tasks": []
//   }
//   );
//   useEffect(() => {
//   fetch('http://localhost:8080/api/root?id=1', {
//     method: 'GET',
//     mode: 'cors'
//   })
//   .then(res => res.json())
//   .then(o => {
//     if(o == null) throw "null task returned from backend, mock is loaded";
//     return o;
//   })
//   // .then(taskAdapter)
//   .then(setTree)
//   .catch(err => {
//     console.error(err);
//     setTree(MockTree);
//   })
//   }, [tree == null])
//   return tree;
// }


// todo usunac? wylaczone
// function taskAdapter(toAdapt) {
//   adapt(toAdapt);
//   return toAdapt;
// }

// /**
//  *
//  * @param {Response} node
//  * @return {Object} adapted object
//  */
// function adapt(node) {
//   if (node == null) return;
//   node.id = node.taskId+'';
//   delete node.taskId;
//   node.children = node.subTasks;
//   delete node.subTasks;
//   // console.log(node) TODO performance issue
//   for (let i=0; i<node.children.length; ++i) {
//   taskAdapter(node.children[i]);
//   }
// }