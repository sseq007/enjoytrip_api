import { apiInstance } from "./index.js";

const api = apiInstance();

function listSido(success, fail) {
    api.get(`/api/sido/list`).then(success).catch(fail);
}

export { listSido };