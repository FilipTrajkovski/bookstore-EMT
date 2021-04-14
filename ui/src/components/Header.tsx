import * as React from "react"
import { useCallback } from "react"
import {
    AppBar,
    Container,
    IconButton,
    Link,
    List,
    ListItem,
    ListItemText,
    makeStyles,
    Toolbar
} from "@material-ui/core"
import { Home } from "@material-ui/icons";
import { useHistory } from "react-router-dom";

const useStyles = makeStyles({
    navbarDisplayFlex: {
        display: `flex`,
        justifyContent: `space-between`
    },
    navDisplayFlex: {
        display: `flex`,
        justifyContent: `space-between`,
        float: `right`
    },
    linkText: {
        textDecoration: `none !important`,
        textTransform: `uppercase`,
        color: `white`
    },
    appBar: {
        marginBottom: `2rem`
    }
});

const Header = (): JSX.Element => {

    const classes = useStyles();
    const { push } = useHistory();

    const goToHome = useCallback(() => {
        push("/");
    }, [push]);

    const goToRoute = useCallback((route) => {
        push(route)
    }, [push])

    return (
        <AppBar position="static" className={classes.appBar}>
            <Toolbar>
                <Container maxWidth="xl" className={classes.navbarDisplayFlex}>
                    <IconButton edge="start" color="inherit" aria-label="home" onClick={goToHome}>
                        <Home fontSize="large" />
                    </IconButton>
                    <List
                        component="nav"
                        aria-labelledby="main navigation"
                        className={classes.navDisplayFlex}
                    >
                        <Link className={classes.linkText} onClick={() => goToRoute("/books")}>
                            <ListItem button>
                                <ListItemText primary={"Books"} />
                            </ListItem>
                        </Link>
                        <Link className={classes.linkText} onClick={() => goToRoute("/categories")}>
                            <ListItem button>
                                <ListItemText primary={"Categories"} />
                            </ListItem>
                        </Link>
                    </List>
                </Container>
            </Toolbar>
        </AppBar>
    )
}

export default Header;