import { Container } from "@material-ui/core";
import { Route, Switch } from 'react-router-dom'
import React from "react";
import Books from "./Books";
import Categories from "./Categories";
import UpsertBook from "./UpsertBook";

const Main = (): JSX.Element => {
    return (
        <Container>
            <Switch>
                <Route path='/books' exact={true} component={Books}/>
                <Route path='/books/upsert/:id' exact={true} component={UpsertBook}/>
                <Route path='/books/upsert' exact={true} component={UpsertBook}/>
                <Route path='/categories' exact={true} component={Categories}/>
                <Route path='/' exact={true} component={Books}/>
            </Switch>
        </Container>
    );
}

export default Main;