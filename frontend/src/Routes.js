import DecksPage from './pages/DecksPage/DecksPage';
import MealPage from './pages/MealPage/MealPage';
import PlayFlashcardsPage from './pages/PlayFlashcardsPage/PlayFlashcardsPage';
import ProjectsPage from './pages/ProjectsPage/ProjectsPage';
import DashboardPage from './pages/DashboardPage/DashboardPage';
import { Route, Switch, BrowserRouter } from 'react-router-dom';
import Header from './common/Header'


import DevPage from './pages/DevPage/DevPage';
import DevPage3 from './pages/DevPage/DevPage3';
import DevPage4 from './pages/DevPage/DevPage4';
import DevStyles from './pages/DevPage/Dev-styles';


export default function Routes() {
  return (
  <BrowserRouter>
    <Route path="/" component={Header}></Route>
    <Switch>
    <Route exact path="/" component={DashboardPage} />
    <Route exact path="/decks" component={DecksPage} />
    <Route exact path="/play-flashcards/:deckId" component={PlayFlashcardsPage}></Route>
    <Route exact path="/dev" component={DevPage} />
    <Route exact path="/devs" component={DevStyles} />
    <Route exact path="/dev3" component={DevPage3} />
    <Route exact path="/dev4" component={DevPage4} />
    <Route exact path="/meal/add" component={MealPage} />
    <Route exact path="/projects" component={ProjectsPage} />
    </Switch>
  </BrowserRouter>
  );
}