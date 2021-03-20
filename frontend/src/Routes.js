import AvailableFlashcardSetsPage from './pages/AvailableFcSetsPage/AvailableFlashcardSetsPage';
import AddMealPage from './pages/AddMealPage/AddMealPage';
import ProjectsDashboardPage from './pages/ProjectsPage/ProjectsDashboardPage';
import { Route } from "react-router";
import { BrowserRouter } from 'react-router-dom';
import Header from './common/Header'


function Routes() {
  return (
    <div className="app-wrapper">
      <BrowserRouter>
        <Route path="/" component={Header}></Route>
                <main>
                  <Route exact path="/" component={AvailableFlashcardSetsPage}/>
                  <Route exact path="/meal/add" component={AddMealPage}/>
                  <Route exact path="/projects" component={ProjectsDashboardPage}/>
                </main>
      </BrowserRouter>
    </div>
  );
}

export default Routes;
