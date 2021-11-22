import { useCallback, useEffect, useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import * as actionTypes from '../../store/projectsActions';
import FsTreeView from './fsTreeView/FsTreeView';
import './ProjectsDashboard.css'
import createProjectBtn from './widgets/createProjectWidget';
import useCardAwareListView from '../../common/presenters/CardAwareListView';
import withProjectPresenter from '../../Model/ProjectPresenter';
import * as R from 'ramda';
import * as d3 from 'd3';
import Drawer from '@mui/material/Drawer';
import Button from '@mui/material/Button';
import Modal from "@material-ui/core/Modal";
import Backdrop from "@material-ui/core/Backdrop";
import Fade from "@material-ui/core/Fade";
import {makeStyles} from "@material-ui/core/styles";
import EditTaskForm from "./EditTaskForm";


const useStyles = makeStyles((theme) => ({
  modal: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    color: 'blue'
  },
  paper: {
    backgroundColor: '#54494B',
    boxShadow: theme.shadows[5],
    padding: "2rem"
  },
}));



export default function ProjectsDashboardPage() {
  const classes = useStyles();
  const dispatch = useDispatch();
  const [translate, containerRef] = useCenterTree();
  const availableProjects = useSelector(state => state.projectsReducer.projects, (left, right) => R.equals(left, right));
  useEffect(() => {
    dispatch(actionTypes.fetchProjects()); //todo zoptymalizowac dzialanie, dziala teraz jak component did mount, NIE JEST ZALEZNE OD decks ze store!!!
  }, []);
  const [activeProject, setActiveProject] = useState({});
  useEffect(() => {
    const newActiveProject = availableProjects.find(project => project.project_id === activeProject.project_id);
    setActiveProject(newActiveProject || {});
  }, [availableProjects]);
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);
  const activeRootTask = activeProject.root_task || {};
  const [taskUnderModification, setTaskUnderModification] = useState(activeRootTask);


  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleClose = () => {
    setIsModalOpen(false);
  };



  const nodeSize = { x: 200, y: 200 }; //todo remove ti



  const treeData = d3.hierarchy(activeRootTask, (task) => task.sub_tasks);
  return (
    <div className="dashboard-wrapper" ref={containerRef}>
      <Drawer
        anchor='left'
        open={isDrawerOpen}
        onClose={()=>setIsDrawerOpen(!isDrawerOpen)}
      >
        {<ul>
          {availableProjects.map((project, idx) => 
            <span 
              onClick={() => {
                setActiveProject(project);
                setIsDrawerOpen(false);
            
              }}
            key={idx}>{useCardAwareListView(withProjectPresenter(project))}</span>
          )}
        </ul>
        }
      </Drawer>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        className={classes.modal}
        open={isModalOpen}
        onClose={handleClose}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 300
        }}
      >
        <Fade in={isModalOpen}>
          <div className={classes.paper}>
             <EditTaskForm setIsModalOpen={setIsModalOpen} task={taskUnderModification}/>
          </div>
        </Fade>
      </Modal>
      {/*<Tree*/}
      {/*  zoom={0.5}*/}
      {/*  data={treeData}*/}
      {/*  translate={translate}*/}
      {/*  // renderCustomNodeElement={(rd3tProps) =>*/}
      {/*  //   renderForeignObjectNode({ ...rd3tProps, foreignObjectProps })*/}
      {/*  // }*/}
      {/*  renderCustomNodeElement={TreeNode}*/}
      {/*/>*/}
      {FsTreeView(activeProject, dispatch)}
      <div className="dashboard-buttons">
        {/*<Button onClick={() => setIsModalOpen(!isModalOpen)}>new Task</Button>*/}
        <Button onClick={() => setIsDrawerOpen(true)}>Projects</Button>
        {createProjectBtn()}
      </div>
    </div>
  )

  // todo remove that
  function TreeNode({nodeDatum, toggleNode}) {
    return (
      <g>
        <rect width="20" height="20" x="-10" onClick={toggleNode} />
        <text fill="black" strokeWidth="1" x="20">
          #{nodeDatum.data.task_id}   {nodeDatum.data.name}
        </text>
      </g>
    )
  }

  function useCenterTree(defaultTranslate = {x: 0, y:0}) {
    const [translate, setTranslate] = useState(defaultTranslate);
    const containerRef = useCallback(containerElem => {
      if(containerElem !== null) {
        const { width, height } = containerElem.getBoundingClientRect();
        setTranslate({x: width * 0.1, y :height * 0.5});
      }
    }, [])
    return [translate, containerRef];
  }




}

