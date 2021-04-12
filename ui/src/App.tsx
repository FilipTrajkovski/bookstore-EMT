import React from 'react';
import './App.css';
import { CssBaseline } from "@material-ui/core";
import Header from "./components/Header";
import Main from "./components/Main";

function App(): JSX.Element {
    return (
        <div className="App">
            <CssBaseline />
            <Header />
            <Main />
        </div>
    );
}

export default App;
