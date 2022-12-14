import axios from '../lib/axios';

const getOrderList = async (userIdx)=> {
    let res;
    try{
        res = await axios.post('http://localhost:8000/order-service/order_list', {
            userIdx : userIdx
        })
    }catch(err){
        throw new Error(err.message);
    }
    return res
}

export default{
    getOrderList
}