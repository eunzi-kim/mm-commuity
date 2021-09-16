import 'bootstrap/dist/css/bootstrap.min.css';

import { BrowserRouter, Switch, Route } from 'react-router-dom';

import PrivateRoute from './lib/PrivateRoute';
import PublicRoute from './lib/PublicRoute';

import Home from "./components/Main/Home";
import Login from "./components/Login/Login";
import EduPro from "./components/EduPro/EduPro";

import './App.css';


function App() {
  return (
    <BrowserRouter>
      <Switch>
        <PrivateRoute path="/" component={Home} exact={true} />
        <PublicRoute restricted="true" path="/login" component={Login} />
        <PrivateRoute path="/EduPro" component={EduPro} exact={true} />
      </Switch>
    </BrowserRouter>
  );
}

export default App;
