import { BookResponseDto, ResponseDto, UpsertBookDto, UpsertBookResponseDto } from "../types/types";
import axios, { CancelToken } from "axios";


const {REACT_APP_API_BASE_URL: baseUrl} = process.env;
const apiUri = `${baseUrl}/api/books`;

export const getBooksByPage = (page: number, token: CancelToken): Promise<BookResponseDto> => {
    return axios
        .get(`${apiUri}/page/${page}`, {cancelToken: token})
        .then(response => response.data);
}

export const getBookById = (id: number, token: CancelToken): Promise<UpsertBookResponseDto> => {
    return axios
        .get(`${apiUri}/${id}`, {cancelToken: token})
        .then(response => response.data);
}

export const addBook = (upsertBookDto: UpsertBookDto, token: CancelToken): Promise<ResponseDto> => {
    return axios
        .post(`${apiUri}`, {...upsertBookDto, category: upsertBookDto.category.toUpperCase()}, {cancelToken: token})
        .then(response => response.data);
}

export const editBook = (id: number, upsertBookDto: UpsertBookDto, token: CancelToken): Promise<ResponseDto> => {
    return axios
        .put(`${apiUri}/${id}`, {
            ...upsertBookDto,
            category: upsertBookDto.category.toUpperCase()
        }, {cancelToken: token})
        .then(response => response.data);
}

export const deleteBook = (id: number, token: CancelToken): Promise<ResponseDto> => {
    return axios
        .delete(`${apiUri}/${id}`, {cancelToken: token})
        .then(response => response.data);
}

export const markBook = (id: number, token: CancelToken): Promise<ResponseDto> => {
    return axios
        .get(`${apiUri}/mark/${id}`, {cancelToken: token})
        .then(response => response.data);
}