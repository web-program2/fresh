import axios from '../lib/axios';

const getCatalogItems = async (data)=> {
    let res;
    try{
        res = await axios.post('http://localhost:8000/user-service/~~~~', {
        })
    }catch(err){
        throw new Error(err.message);
    }
    return res
}
const createCatalogItem = async (data)=> {
    let res;
    try{
        res = await axios.post('http://localhost:8000/catalog-service/', {
            name : data.name,
            price : data.price,
            stock : data.stock,
            content : data.content,
            userIdx : data.userIdx,
        })
    }catch(err){
        throw new Error(err.message);
    }
    return res
}

export default {
    getCatalogItems, createCatalogItem
}