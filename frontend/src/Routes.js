import AvailableFlashcardSetsPage from './pages/AvailableFcSetsPage/AvailableFlashcardSetsPage';
import AddMealPage from './pages/AddMealPage/AddMealPage';
import FlashcardsPage from './pages/FlashcardsPage/FlashcardsPage';
import ProjectsDashboardPage from './pages/ProjectsPage/ProjectsDashboardPage';
import { Route } from "react-router";
import { BrowserRouter } from 'react-router-dom';
import Header from './common/Header'

import EditTaskModal from './pages/ProjectsPage/EditTaskModal';

export default function Routes() {
  return (
    <div>
      <BrowserRouter>
        <Route path="/" component={Header}></Route>
                <main>
                  <Route exact path="/" component={AvailableFlashcardSetsPage}/>
                  <Route exact path="/meal/add" component={AddMealPage}/>
                  <Route exact path="/projects" component={ProjectsDashboardPage}/>
                  <Route exact path="/dev" component={EditTaskModal}/>
                  <Route exact path="/flashcards" component={AvailableFlashcardSetsPage}/>
                </main>
      </BrowserRouter>
    </div>
  );
}