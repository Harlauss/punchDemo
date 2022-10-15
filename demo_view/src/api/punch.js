import axios from "axios";

const DevUrl = "http://localhost:8001";

const ProdUrl = "http://82.157.119.39:8001";

const CurrUrl = DevUrl;


export function queryAddress(){
    return axios({
        url: CurrUrl + "/api/address/query",
        method:"get",
        data:""
    })
}

export function submitUserForm(data){
    return axios({
        url: CurrUrl + "/api/punch/register",
        method:"post",
        data:data
    })
}

export function punchRecord(data){
    return axios({
        url: CurrUrl + "/api/record/search",
        method:"post",
        data:data
    })
}