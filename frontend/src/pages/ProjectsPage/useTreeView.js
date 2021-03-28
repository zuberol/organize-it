import React, { useState } from 'react';
import TreeView from '@material-ui/lab/TreeView';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import TreeItem from '@material-ui/lab/TreeItem';
import { createMuiTheme, ThemeProvider } from "@material-ui/core/styles";


const theme = createMuiTheme({
    overrides: {
      // Style sheet name ⚛️
      MuiTreeView: {
        // Name of the rule
        root: {
          // Some CSS
          background: "linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)",
          borderRadius: 3,
          border: 0,
          color: "red",
          height: 48,
          boxShadow: "0 3px 5px 2px rgba(255, 105, 135, .3)"
        }
      }
    }
});




/**
 *
 * @param {Object} props
 * @return {JSX} something
 */
export default function useTreeView(model) {
  const [expandIndexes, setExpandIndexes] = useState(getNestedObjectsIndexes(model));

  return [(
    <ThemeProvider theme={theme}>
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
    </ThemeProvider>
  ),
  () => setExpandIndexes([]),
  () => setExpandIndexes(getNestedObjectsIndexes(model))
  ];


  /**
   *
   * @param {Object} nodes
   * @return {Array.<TreeItem>} tree
   */
  function renderTree(nodes) {
    if(Object.keys(nodes).length === 0) return null;  //if nodes is {}
    return (
      <TreeItem
        key={nodes.id}
        nodeId={nodes.id}
        label={<p>{`${nodes.id} __ ${nodes.note}`}</p>}
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
  function getNestedObjectsIndexes(item) {
    if (item == null) return [];
    else if (item.children == null) return [item.id + ''];
    const childrenIdentifiers = item.children.map((child) => {
      const grandchildrenIndentifiers = getNestedObjectsIndexes(child);
      return [...grandchildrenIndentifiers, child.id + ''];
    }).flat();
    return [...childrenIdentifiers, item.id + ''];
  }
}