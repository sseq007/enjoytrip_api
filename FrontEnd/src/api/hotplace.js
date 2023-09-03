import { apiInstance } from "./index.js";
import axios from "axios";

const api = apiInstance();

function writeHotplace(hotplace, success, fail) {
    axios.post('http://localhost:8081/api/hotplace/write', hotplace, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }).then(success).catch(fail);
}

function modifyHotplace(hotplace, success, fail) {
    axios.put('http://localhost:8081/api/hotplace/update', hotplace, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }).then(success).catch(fail);
}

function getHotplace(articleNo, success, fail) {
    api.get(`/api/hotplace/view/${articleNo}`).then(success).catch(fail);
}

function listHotplace(success, fail) {
    api.get(`/api/hotplace/list`).then(success).catch(fail);
}

function deleteHotplace(articleNo, success, fail) {
    api.delete(`/api/hotplace/delete/${articleNo}`).then(success).catch(fail);
}

function writeReply(hotplaceReply, success, fail){
    api.post(`/api/hotplace/write/reply`, JSON.stringify(hotplaceReply)).then(success).catch(fail);
}

function modifyReply(hotplaceReply, success, fail){
    api.put(`/api/hotplace/modify/reply`, JSON.stringify(hotplaceReply)).then(success).catch(fail);
}

function listReply(articleNo, success, fail){
    api.get(`/api/hotplace/reply/${articleNo}`).then(success).catch(fail);
}

function deleteReply(hotplaceReply, success, fail){
    api.post(`/api/hotplace/delete/reply`, JSON.stringify(hotplaceReply)).then(success).catch(fail);
}

function getMax(articleNo, replyNo, success, fail){
    api.get(`/api/hotplace/max/${articleNo}/${replyNo}`).then(success).catch(fail);
}

function checkHeart(heartCommentDto, success, fail){
    axios.post('http://localhost:8081/api/hotplace/like/get', heartCommentDto, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }).then(success).catch(fail);
}

function plusHeart(heartCommentDto, success, fail){
    axios.post('http://localhost:8081/api/hotplace/like', heartCommentDto, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }).then(success).catch(fail);
}

function minusHeart(heartCommentDto, success, fail){
    axios.post('http://localhost:8081/api/hotplace/dislike', heartCommentDto, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }).then(success).catch(fail);
}


export { writeHotplace, modifyHotplace, getHotplace, listHotplace, deleteHotplace,
    writeReply, listReply, modifyReply, deleteReply, getMax, checkHeart, plusHeart, minusHeart };