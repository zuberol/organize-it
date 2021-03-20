import React, {useState} from 'react';
import TreeView from '@material-ui/lab/TreeView';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import TreeItem from '@material-ui/lab/TreeItem';

/**
 *
 * @param {Object} props
 * @return {JSX} something
 */
export default function useTreeView(model) {
  const [expandIndexes, setExpandIndexes] = useState(getNestetObjectsIndexes(model));

  return (
    <>
      <button onClick={() => setExpandIndexes([])}>colapse all</button>
      <button onClick={() => setExpandIndexes(getNestetObjectsIndexes(model))}>expand all</button>
      <TreeView
        defaultCollapseIcon={<ExpandMoreIcon />}
        defaultExpandIcon={<ChevronRightIcon />}
        // defaultExpanded={expandIndexes}
        expanded={expandIndexes}
        onNodeToggle={(event, nodeIds) => {
          setExpandIndexes(nodeIds);
        }}>
        {renderTree(model)}
      </TreeView>
    </>
  );


  /**
   *
   * @param {Object} nodes
   * @return {Array.<TreeItem>} tree
   */
  function renderTree(nodes) {
    return (
      <TreeItem
        key={nodes.id}
        nodeId={nodes.id}
        label={nodes.id}
      >
        {Array.isArray(nodes.children) ? nodes.children.map((node) => renderTree(node)) : null}
      </TreeItem>
    );
  }

  /**
   *
   * @param {TreeNode} item
   * @return {String[]} indexes
   */
  function getNestetObjectsIndexes(item) {
    if (item == null) return [];
    else if (item.children == null) return [item.id + ''];
    const childrenIdentifiers = item.children.map((child) => {
      const grandchildrenIndentifiers = getNestetObjectsIndexes(child);
      return [...grandchildrenIndentifiers, child.id + ''];
    }).flat();
    return [...childrenIdentifiers, item.id + ''];
  }
}
