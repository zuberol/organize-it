import DecksPage from './pages/DecksPage/DecksPage';
import MealPage from './pages/MealPage/MealPage';
import FlashcardsPage from './pages/FlashcardsPage/FlashcardsPage';
import PlansPage from './pages/PlansPage/PlansPage';
import DashboardPage from './pages/DashboardPage/DashboardPage';
import { Route, Switch, BrowserRouter } from 'react-router-dom';
import Header from './common/Header'


import DevPage from './pages/DevPage/DevPage';
import DevPage3 from './pages/DevPage/DevPage3';
import DevPage4 from './pages/DevPage/DevPage4';
import DevIcons from './pages/DevPage/DevIcons';
import DevStyles from './pages/DevPage/Dev-styles';


export default function Routes() {
  return (
    <div style={{display: 'flex', minHeight: "100vh", minWidth: '100vw'}}>

      <BrowserRouter>
        <Route path="/" component={Header}></Route>
        <Switch>
          <Route exact path="/" component={DashboardPage} />
          <Route exact path="/decks" component={DecksPage} />
          <Route exact path="/play-flashcards/:id" component={FlashcardsPage}></Route>
          <Route exact path="/dev" component={DevPage} />
          <Route exact path="/devs" component={DevStyles} />
          <Route exact path="/dev2" component={DevIcons} />
          <Route exact path="/dev4" component={DevPage4} />
          <Route exact path="/meal/add" component={MealPage} />
          <Route exact path="/plans" component={PlansPage} />
        </Switch>
      </BrowserRouter>
    </div>
  );
}