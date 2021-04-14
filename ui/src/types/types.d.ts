import { Category } from "./enums";

export interface BookDto {
    id: number,
    name: string,
    authorName: string,
    authorCountry: string,
    category?: Category,
    numCopies: number
}

export interface HeadCell {
    id: string;
    label: string;
}