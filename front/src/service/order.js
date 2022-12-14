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
    console.log(res);
    return res;
}

export default{
    getOrderList
}