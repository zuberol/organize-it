import { React, useState } from 'react';
import useTreeView from './useTreeView';
import MockTree from '../../mock/MockTree2';
import {useTaskAdapter} from '../../Model/TaskAdapter';
import './ProjectsDashboard.css'

export default function ProjectsDashboardPage() {
    const projects = useTaskAdapter(MockTree);
    const ProjectsTreeView = useTreeView(projects);
    // console.log(projects)
    return ProjectsTreeView;
}