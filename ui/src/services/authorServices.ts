import axios, { CancelToken } from "axios";
import { AuthorDto } from "../types/types";


const {REACT_APP_API_BASE_URL: baseUrl} = process.env;
const apiUri = `${baseUrl}/api/authors`;

export const getAllAuthors = (token: CancelToken): Promise<Array<AuthorDto>> => {
    return axios
        .get(apiUri, {cancelToken: token})
        .then(response => response.data);
}