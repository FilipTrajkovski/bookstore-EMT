import { BookResponseDto } from "../types/types";
import axios, { CancelToken } from "axios";


const { REACT_APP_API_BASE_URL: baseUrl } = process.env;
const apiUri = `${baseUrl}/api/books`;

export const getBooksByPage = (page: number, token: CancelToken): Promise<BookResponseDto> => {
    return axios
        .get(`${apiUri}/page/${page}`, { cancelToken: token })
        .then(response => response.data);
}