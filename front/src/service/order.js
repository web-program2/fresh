import axios from '../lib/axios';

const getOrderList = async (userIdx)=> {
    let res;
    try{
        res = await axios.post('http://localhost:8000/order-service/orders', {
            userIdx : userIdx,
        })
    }catch(err){
        throw new Error(err.message);
    }
    return res;
}
const getOrderData = async (orderIdx)=> {
    let res;
    console.log('aaaa', orderIdx)
    try{
        res = await axios.get(`http://localhost:8000/order-service/${orderIdx}`, {
        })
    }catch(err){
        throw new Error(err.message);
    }
    return res;
}
const createOrder = async (data)=> {
    let res;
    try{
        res = await axios.post('http://localhost:8000/order-service/order', {
            userIdx : data.userIdx,
            catalogIdx : data.catalogIdx,
            qty : data.orderQty,
            price : data.price,
        })
    }catch(err){
        throw new Error(err.message);
    }
    console.log(res);
    return res;
}

export default{
    getOrderList,
    createOrder,
    getOrderData
}