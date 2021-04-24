import { React } from 'react';
import useTreeView from './useTreeView';
import MockTree from '../../mock/MockTree2';
import { useTaskAdapter } from '../../Model/TaskAdapter';
import './ProjectsDashboard.css'


export default function ProjectsDashboardPage() {
    const projects = useTaskAdapter(MockTree);
    const [ProjectsTreeView, expandIndexes, rollIndexes] = useTreeView(projects);
    return (
        <div className="dashboard-wrapper">
            {ProjectsTreeView}
            <div className="dashboard-buttons">
                <button className="" onClick={expandIndexes}>colapse all</button>
                <button className="btn" onClick={rollIndexes}>expand all</button>
            </div>
        </div>
    )
}

