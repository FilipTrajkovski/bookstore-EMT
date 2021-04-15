import React from "react";
import {
    Container,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Toolbar,
    Typography,
    withStyles
} from "@material-ui/core";
import { createStyles, makeStyles, Theme } from "@material-ui/core/styles";

const StyledTableRow = withStyles((theme: Theme) =>
    createStyles({
        root: {
            '&:nth-of-type(odd)': {
                backgroundColor: theme.palette.action.hover
            }
        }
    })
)(TableRow);

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        root: {
            width: `100%`
        },
        table: {
            minWidth: 400
        },
        toolbar: {
            paddingLeft: theme.spacing(2),
            paddingRight: theme.spacing(1)
        },
        toolbarTitle: {
            flex: `1 1 100%`
        }
    })
);

const Categories = (): JSX.Element => {
    const classes = useStyles();

    return (
        <Container className={classes.root}>
            <TableContainer component={Paper}>
                <Toolbar className={classes.toolbar}>
                    <Typography className={classes.toolbarTitle} variant="h6" id="tableTitle" component="div">
                        Categories
                    </Typography>
                </Toolbar>
                <Table className={classes.table} aria-labelledby="tableTitle" size="small">
                    <TableHead>
                        <TableRow>
                            <TableCell>Category</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        <StyledTableRow>
                            <TableCell component="th" scope="row">Novel</TableCell>
                        </StyledTableRow>
                        <StyledTableRow>
                            <TableCell component="th" scope="row">Thriller</TableCell>
                        </StyledTableRow>
                        <StyledTableRow>
                            <TableCell component="th" scope="row">History</TableCell>
                        </StyledTableRow>
                        <StyledTableRow>
                            <TableCell component="th" scope="row">Fantasy</TableCell>
                        </StyledTableRow>
                        <StyledTableRow>
                            <TableCell component="th" scope="row">Biography</TableCell>
                        </StyledTableRow>
                        <StyledTableRow>
                            <TableCell component="th" scope="row">Classics</TableCell>
                        </StyledTableRow>
                        <StyledTableRow>
                            <TableCell component="th" scope="row">Drama</TableCell>
                        </StyledTableRow>
                    </TableBody>
                </Table>
            </TableContainer>
        </Container>
    )
}

export default Categories;