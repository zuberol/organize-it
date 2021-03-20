import { React, useState } from 'react';
import useTreeView from './useTreeView';
import MockTree from '../../mock/MockTree';
import './ProjectsDashboard.css'

export default function ProjectsDashboardPage() {
    const [projects, setProjects] = useState(MockTree);
    const ProjectsTreeView = useTreeView(projects);
    return ProjectsTreeView;
}