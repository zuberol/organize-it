import useFsTreeView from './../ProjectsPage/fsTreeView/useFsTreeView';

export default function dev4() {
  return (
  <div>
    <h1>use useFsTreeView test</h1>
    <label htmlFor="command">Command:</label>
    <input name="command"></input>
    {useFsTreeView()}
  </div>);
}
