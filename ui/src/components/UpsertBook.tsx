import {
    Button,
    Container,
    FormControl,
    FormHelperText,
    Grid,
    Input,
    InputLabel,
    MenuItem,
    Select
} from "@material-ui/core";
import { RouteComponentProps, withRouter } from "react-router-dom";
import { Component, FormEvent } from "react";
import { convertEnumValue, tryParseToPositiveInt } from "../utils/utils";
import { AuthorDto, CountryDto, UpsertBookDto, UpsertBookResponseDto } from "../types/types";
import { addBook, editBook, getBookById } from "../services/bookServices";
import axios, { CancelToken } from "axios";
import { getAllAuthors } from "../services/authorServices";
import { getAllCountries } from "../services/countryServices";

enum Category {
    NOVEL = "Novel",
    THRILLER = "Thriller",
    HISTORY = "History",
    FANTASY = "Fantasy",
    BIOGRAPHY = "Biography",
    CLASSICS = "Classics",
    DRAMA = "Drama"
}

interface RouteParams {
    id?: string
}

interface Props extends RouteComponentProps<RouteParams> {

}

interface State {
    bookId?: number;
    book: UpsertBookDto;
    token: CancelToken;
    authors: Array<AuthorDto>
    countries: Array<CountryDto>
}

class UpsertBook extends Component<Props, State> {

    constructor(props: Props) {
        super(props);

        const {match, history} = props;
        const bookId: string | undefined = match.params.id;

        this.state = {
            token: axios.CancelToken.source().token,
            authors: [],
            countries: [],
            book: {
                name: "",
                category: Category.NOVEL,
                numCopies: 1
            }
        }

        if (bookId) {
            const possibleId = tryParseToPositiveInt(bookId);

            if (possibleId < 0) {
                history.push("/books");
            } else {
                this.fetchBook(possibleId)
                    .then(response => {
                        if (response.success && response.upsertBookDto) {
                            this.setState({
                                bookId: possibleId,
                                book: {
                                    ...response.upsertBookDto,
                                    category: convertEnumValue(response.upsertBookDto.category as string) as Category
                                }
                            });
                        } else {
                            history.push("/books");
                        }
                    });
            }
        }

        this.fetchAuthors();
        this.fetchCountries();
    }

    fetchBook = async (bookId: number): Promise<UpsertBookResponseDto> => {

        const {token} = this.state;

        return await getBookById(bookId, token);
    }

    fetchAuthors = () => {

        const {token} = this.state;

        getAllAuthors(token)
            .then(response => {
                this.setState(({book}) => ({
                    authors: response,
                    book: {
                        ...book,
                        authorId: response.length > 0 ? response[0].id : undefined
                    }
                }))
            })
    }

    fetchCountries = () => {

        const {token} = this.state;

        getAllCountries(token)
            .then(response => {
                this.setState({
                    countries: response
                })
            })
    }

    handleInputChange = (e: any) => {

        const {book} = this.state;
        const {name, value} = e.target;

        if (name !== "numCopies" || value as number > 0) {
            this.setState({
                book: {
                    ...book,
                    [name]: value
                }
            })
        }
    };

    handleSubmit = (event: FormEvent<any>) => {
        event.preventDefault();

        const {history} = this.props;
        const {bookId, book, token} = this.state;

        if (bookId) {
            editBook(bookId, book, token)
                .then(response => {
                    if (response.success) {
                        history.push("/");
                    }
                })
        } else {
            addBook(book, token)
                .then(response => {
                    if (response.success) {
                        history.push("/");
                    }
                });
        }
    }

    onCancelClick = () => {

        const {history} = this.props;

        history.push("/");
    }

    render() {

        const {book, authors} = this.state;

        const mappedAuthors = authors.map((author: AuthorDto) => (
            <MenuItem key={author.id} value={author.id}>
                {`${author.name} ${author.surname} (${author.countryName})`}
            </MenuItem>
        ));

        return (
            <Container>
                <h1>
                    Upsert Book
                </h1>
                <form onSubmit={this.handleSubmit}>
                    <Grid container alignItems="center" justify="center" direction="column" spacing={2}>
                        <Grid item>
                            <FormControl>
                                <InputLabel htmlFor="name">Name</InputLabel>
                                <Input
                                    id="name"
                                    name="name"
                                    value={book.name}
                                    aria-describedby="name-helper-text"
                                    required
                                    onChange={this.handleInputChange}
                                />
                                <FormHelperText id="name-helper-text">Name of the book</FormHelperText>
                            </FormControl>
                        </Grid>
                        <Grid item>
                            <FormControl>
                                <InputLabel htmlFor="category">Category</InputLabel>
                                <Select
                                    id="category"
                                    name="category"
                                    value={book.category}
                                    autoWidth
                                    required
                                    onChange={this.handleInputChange}
                                >
                                    <MenuItem value={Category.NOVEL}>{Category.NOVEL}</MenuItem>
                                    <MenuItem value={Category.THRILLER}>{Category.THRILLER}</MenuItem>
                                    <MenuItem value={Category.HISTORY}>{Category.HISTORY}</MenuItem>
                                    <MenuItem value={Category.FANTASY}>{Category.FANTASY}</MenuItem>
                                    <MenuItem value={Category.BIOGRAPHY}>{Category.BIOGRAPHY}</MenuItem>
                                    <MenuItem value={Category.CLASSICS}>{Category.CLASSICS}</MenuItem>
                                    <MenuItem value={Category.DRAMA}>{Category.DRAMA}</MenuItem>
                                </Select>
                                <FormHelperText id="category-helper-text">Book category</FormHelperText>
                            </FormControl>
                        </Grid>
                        <Grid item>
                            <FormControl>
                                <InputLabel htmlFor="author">Author</InputLabel>
                                {
                                    authors.length > 0 &&
                                    <Select
                                        id="author"
                                        name="authorId"
                                        value={book.authorId}
                                        autoWidth
                                        required
                                        onChange={this.handleInputChange}
                                    >
                                        {mappedAuthors}
                                    </Select>
                                }
                                <FormHelperText id="category-helper-text">Author of the book</FormHelperText>
                            </FormControl>
                        </Grid>
                        <Grid item>
                            <FormControl>
                                <InputLabel htmlFor="numOfCopies">Number of copies</InputLabel>
                                <Input
                                    id="numOfCopies"
                                    name="numCopies"
                                    aria-describedby="numOfCopies-helper-text"
                                    type="number"
                                    value={book.numCopies}
                                    onChange={this.handleInputChange}
                                />
                                <FormHelperText id="numOfCopies-helper-text">Number of copies the book
                                    has</FormHelperText>
                            </FormControl>
                        </Grid>
                        <Grid item>
                            <Button variant="contained" color="primary" type="submit" style={{marginRight: "2rem"}}>
                                Submit
                            </Button>
                            <Button variant="contained" color="secondary" type="button" onClick={this.onCancelClick}>
                                Cancel
                            </Button>
                        </Grid>
                    </Grid>
                </form>
            </Container>
        );
    }

}

export default withRouter(UpsertBook);