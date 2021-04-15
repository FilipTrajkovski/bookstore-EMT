import axios, { CancelToken } from "axios";
import { CountryDto } from "../types/types";

const {REACT_APP_API_BASE_URL: baseUrl} = process.env;
const apiUri = `${baseUrl}/api/countries`;

export const getAllCountries = (token: CancelToken): Promise<Array<CountryDto>> => {
    return axios
        .get(apiUri, {cancelToken: token})
        .then(response => response.data);
}