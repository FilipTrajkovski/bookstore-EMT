import React, { useCallback, useEffect } from "react";
import { createStyles, makeStyles, Theme } from "@material-ui/core/styles";
import AddIcon from '@material-ui/icons/Add';
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';
import {
    Backdrop,
    CircularProgress,
    Container,
    IconButton,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TablePagination,
    TableRow,
    TableSortLabel,
    Toolbar,
    Tooltip,
    Typography
} from "@material-ui/core";
import { BookDto, HeadCell } from "../types/types";
import { deleteBook, getBooksByPage, markBook } from "../services/bookServices";
import axios, { CancelToken } from "axios";
import { useHistory } from "react-router-dom";
import { convertEnumValue } from "../utils/utils";
import BookmarksIcon from '@material-ui/icons/Bookmarks';

const headCells: HeadCell[] = [
    {id: "id", label: "ID"},
    {id: "name", label: "Name"},
    {id: "authorName", label: "Author name"},
    {id: "authorCountry", label: "Author country"},
    {id: "category", label: "Category"},
    {id: "numCopies", label: "Number of copies"},
    {id: "actions", label: "Actions"}
];

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        root: {
            width: `100%`
        },
        paper: {
            width: `100%`,
            marginBottom: theme.spacing(2),
            position: `relative`
        },
        table: {
            minWidth: 750
        },
        visuallyHidden: {
            border: 0,
            clip: `rect(0 0 0 0)`,
            height: 1,
            margin: -1,
            overflow: `hidden`,
            padding: 0,
            position: `absolute`,
            top: 20,
            width: 1
        },
        backdrop: {
            position: `absolute`,
            zIndex: theme.zIndex.drawer - 1,
            color: '#fff'
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

type MouseEvent = React.MouseEvent<HTMLButtonElement> | null

let initialLoad = true;

const Books = (): JSX.Element => {
    const classes = useStyles();

    const {push} = useHistory();
    const [token] = React.useState<CancelToken>(axios.CancelToken.source().token);
    const [books, setBooks] = React.useState<Array<BookDto>>([]);
    const [total, setTotal] = React.useState<number>(0);
    const [page, setPage] = React.useState<number>(0);
    const [loadingData, setLoadingData] = React.useState<boolean>(true);

    const loadBooks = useCallback((page: number) => {
        setLoadingData(true);

        getBooksByPage(page, token)
            .then(response => {
                setTotal(response.totalElements);
                setBooks(response.books);
                setPage(page);
            })
            .finally(() => {
                setLoadingData(false);
            });
    }, [setLoadingData, token, setBooks, setTotal, setPage]);

    const handleChangePage = useCallback((event: MouseEvent, page: number) => {
        loadBooks(page);
    }, [loadBooks]);

    const onAddBookClicked = useCallback(() => {
        push("/books/upsert");
    }, [push]);

    const onEditBookClicked = useCallback((bookId: number) => {
        push(`/books/upsert/${bookId}`);
    }, [push]);

    const onDeleteBookClicked = useCallback((bookId: number) => {

        setLoadingData(true);

        deleteBook(bookId, token)
            .then(response => {
                setPage(0);
                loadBooks(0);
            })
    }, [token, setPage, setLoadingData, loadBooks]);

    const onMarkBookClicked = useCallback((bookId: number, index: number) => {

        setLoadingData(true);

        markBook(bookId, token)
            .then(response => {
                const modifiedBooks = [
                    ...books
                ]

                modifiedBooks[index].numCopies -= 1;

                setBooks(modifiedBooks);
            })
            .finally(() => {
                setLoadingData(false);
            })
    }, [token, books, setBooks, setLoadingData]);

    const emptyRows = books.length - 5;

    useEffect(() => {
        if (initialLoad) {
            loadBooks(page);
            initialLoad = false;
        }
    }, [loadBooks, page]);

    useEffect(() => {
        return () => {
            initialLoad = true;
        };
    }, []);

    return (
        <Container className={classes.root}>
            <Paper className={classes.paper}>
                <Toolbar className={classes.toolbar}>
                    <Typography className={classes.toolbarTitle} variant="h6" id="tableTitle" component="div">
                        Books
                    </Typography>
                    <Tooltip title="Add new book">
                        <IconButton aria-label="add-book" onClick={onAddBookClicked}>
                            <AddIcon/>
                        </IconButton>
                    </Tooltip>
                </Toolbar>
                <TableContainer>
                    <Table
                        className={classes.table}
                        aria-labelledby="tableTitle"
                        size="medium"
                        aria-label="book table"
                    >
                        <TableHead>
                            <TableRow>
                                {
                                    headCells.map((headCell: HeadCell) => (
                                        <TableCell key={headCell.id} align="right">
                                            <TableSortLabel>
                                                {headCell.label}
                                            </TableSortLabel>
                                        </TableCell>
                                    ))
                                }
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {
                                books.map((book: BookDto, index: number) => {
                                    return (
                                        <TableRow key={book.id}>
                                            <TableCell component="th" scope="row" align="right">
                                                {book.id}
                                            </TableCell>
                                            <TableCell align="right">{book.name}</TableCell>
                                            <TableCell align="right">{book.authorName}</TableCell>
                                            <TableCell align="right">{book.authorCountry}</TableCell>
                                            <TableCell
                                                align="right">{convertEnumValue(book.category as string)}</TableCell>
                                            <TableCell align="right">{book.numCopies}</TableCell>
                                            <TableCell align="right">
                                                <Tooltip title="Mark book">
                                                    <IconButton
                                                        aria-label="mark-book"
                                                        size="small"
                                                        disabled={book.numCopies === 0}
                                                        onClick={() => onMarkBookClicked(book.id, index)}
                                                    >
                                                        <BookmarksIcon fontSize="small"/>
                                                    </IconButton>
                                                </Tooltip>
                                                <Tooltip title="Edit book">
                                                    <IconButton
                                                        aria-label="edit-book"
                                                        size="small"
                                                        onClick={() => onEditBookClicked(book.id)}
                                                    >
                                                        <EditIcon fontSize="small"/>
                                                    </IconButton>
                                                </Tooltip>
                                                <Tooltip title="Delete book">
                                                    <IconButton
                                                        aria-label="delete-book"
                                                        size="small"
                                                        onClick={() => onDeleteBookClicked(book.id)}
                                                    >
                                                        <DeleteIcon fontSize="small"/>
                                                    </IconButton>
                                                </Tooltip>
                                            </TableCell>
                                        </TableRow>
                                    );
                                })
                            }
                            {emptyRows > 0 && (
                                <TableRow>
                                    <TableCell colSpan={6}/>
                                </TableRow>
                            )}
                        </TableBody>
                    </Table>
                </TableContainer>
                <TablePagination
                    rowsPerPageOptions={[5]}
                    component="div"
                    count={total}
                    rowsPerPage={5}
                    page={page}
                    onChangePage={handleChangePage}
                />
                <Backdrop className={classes.backdrop} open={loadingData}>
                    <CircularProgress color="inherit"/>
                </Backdrop>
            </Paper>
        </Container>
    );
}

export default Books;