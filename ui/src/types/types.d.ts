enum Category {
    NOVEL = "Novel",
    THRILLER = "Thriller",
    HISTORY = "History",
    FANTASY = "Fantasy",
    BIOGRAPHY = "Biography",
    CLASSICS = "Classics",
    DRAMA = "Drama"
}

export interface BookResponseDto {
    totalElements: number;
    books: Array<BookDto>;
}

export interface BookDto {
    id: number;
    name: string;
    authorName: string;
    authorCountry: string;
    category?: Category;
    numCopies: number;
}

export interface HeadCell {
    id: string;
    label: string;
}

export interface UpsertCountryDto {
    name: string;
    continent: string;
}

export interface UpsertAuthorDto {
    countryId?: number;
    upsertCountryDto?: UpsertCountryDto;
    name: string;
    surname: string;
}

export interface UpsertBookDto {
    name: string;
    category: Category;
    authorId?: number;
    upsertAuthorDto?: UpsertAuthorDto;
    numCopies: number;
}

export interface UpsertBookResponseDto {
    success: boolean;
    errorMessage?: string;
    upsertBookDto?: UpsertBookDto;
}

export interface AuthorDto {
    id: number;
    name: string;
    surname: string;
    countryName: string;
}

export interface CountryDto {
    id: number;
    name: string;
    continent: string;
}

export interface FieldErrorDto {
    model: string;
    field: string;
    errorMessage: string;
}

export interface ResponseDto {
    success: boolean;
    message: string;
    fieldErrors: Array<FieldErrorDto>;
}