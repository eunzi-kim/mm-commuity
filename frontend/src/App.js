import 'bootstrap/dist/css/bootstrap.min.css';

import { BrowserRouter, Switch, Route } from 'react-router-dom';

import Home from "./components/Main/Home";
import Login from "./components/Login/Login";
import EduPro from "./components/EduPro/EduPro";

import './App.css';


function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" component={Home} exact={true} />
        <Route path="/login" component={Login} />
        <Route path="/EduPro" component={EduPro} exact={true} />
      </Switch>
    </BrowserRouter>
  );
}

export default App;
