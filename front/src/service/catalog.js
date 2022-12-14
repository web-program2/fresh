import axios from '../lib/axios';

const getCatalogItems = async (data)=> {
    let res;
    try{
        res = await axios.get('http://localhost:8000/catalog-service/catalog_list', {
        })
    }catch(err){
        throw new Error(err.message);
    }
    return res;
}
const getCatalog = async (catalogIdx)=> {
    let res;
    console.log(catalogIdx)
    try{
        res = await axios.get(`http://localhost:8000/catalog-service/${catalogIdx}`, {
        })
    }catch(err){
        throw new Error(err.message);
    }
    return res;
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
    return res;
}
const deleteCatalog = async (catalogIdx)=> {
    let res;
    try{
        res = await axios.delete(`http://localhost:8000/catalog-service/${catalogIdx}`, {
            catalogIdx : catalogIdx
        })
    }catch(err){
        throw new Error(err.message);
    }
    return res;
}

export default {
    getCatalogItems, createCatalogItem,
    getCatalog, deleteCatalog
}