import { Container } from "@material-ui/core";
import { Route, Switch } from 'react-router-dom'
import React from "react";
import Books from "./Books";
import Categories from "./Categories";

const Main = (): JSX.Element => {
    return (
        <Container>
            <Switch>
                <Route path='/books' component={Books}/>
                <Route path='/categories' component={Categories}/>
                <Route path='/' component={Books}/>
            </Switch>
        </Container>
    );
}

export default Main;