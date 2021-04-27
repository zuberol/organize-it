import useFsTreeView from './../ProjectsPage//TreeView/useFsTreeView';

export default function dev4() {
  return (
  <div>
    <label htmlFor="command">Command:</label>
    <input name="command"></input>
    {useFsTreeView()}
  </div>);
}
