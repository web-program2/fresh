/* eslint-disable */
import axios from '../lib/axios';

const checkId = async (id) => {
    let res;
    try{
        res = await axios.post('http://localhost:8000/user-service/check_id', {
            id : id
        })
    }catch(err){
        console.log(err);
    }
    console.log(res);
}
const checkNickName = (nickName) => {

}
const sendEmail = (email) => {

}


export default {
    checkId, checkNickName, sendEmail
}