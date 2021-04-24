import DecksPage from './pages/DecksPage/DecksPage';
import AddMealPage from './pages/AddMealPage/AddMealPage';
import PlayFlashcardsPage from './pages/PlayFlashcardsPage/PlayFlashcardsPage';
import ProjectsDashboardPage from './pages/ProjectsPage/ProjectsDashboardPage';
import { Route, Switch, BrowserRouter } from 'react-router-dom';
import Header from './common/Header'

import EditTaskModal from './pages/ProjectsPage/EditTaskModal';
import AddTaskModal from './pages/PlayFlashcardsPage/AddFlashcardModal';

import DevPage from './pages/DevPage/DevPage';

export default function Routes() {
  return (
    <BrowserRouter>
      <Route path="/" component={Header}></Route>
      <Switch>
        <Route exact path="/" component={DecksPage} />
        <Route exact path="/decks" component={DecksPage} />
        <Route exact path="/play-flashcards/:deck_id" component={PlayFlashcardsPage}></Route>
        {/* <Route exact path="/dev" component={EditTaskModal} /> */}
        <Route exact path="/dev" component={DevPage} />
        <Route exact path="/meal/add" component={AddMealPage} />
        <Route exact path="/projects" component={ProjectsDashboardPage} />
      </Switch>
    </BrowserRouter>
  );
}