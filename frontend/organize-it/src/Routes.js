import AvailableFlashcardSetsPage from './pages/AvailableFcSetsPage/AvailableFlashcardSetsPage';
import { Route } from "react-router";
import { BrowserRouter } from 'react-router-dom';
import Header from './common/Header'


function Routes() {
  return (
    <div className="app-wrapper">
      <BrowserRouter>
        <Route path="/" component={Header}></Route>
                <main>
                  <Route exact path="/" component={AvailableFlashcardSetsPage}></Route>
                </main>
      </BrowserRouter>
    </div>
  );
}

export default Routes;
