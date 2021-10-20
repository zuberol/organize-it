import FsTreeView from './../ProjectsPage/fsTreeView/FsTreeView';

export default function dev4() {
  return (
  <div>
  <h1>use FsTreeView test</h1>
  <label htmlFor="command">Command:</label>
  <input name="command"></input>
  {FsTreeView()}
  </div>);
}
