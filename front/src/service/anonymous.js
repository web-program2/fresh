/* eslint-disable */
import axios from '../lib/axios';

const checkId = async (id) => {
    let res;
    try{
        res = await axios.post('http://localhost:8000/user-service/check_id', {
            id : id
        })
    }catch(err){
        throw new Error(err.message);
    }
    return res.data;
}
const checkNickName = async(nickName) => {
    let res;
    try{
        res = await axios.post('http://localhost:8000/user-service/check_nickname', {
            nickName : nickName
        })
    }catch(err){
        throw new Error(err.message);
    }
    return res.data;
}
const signIn = async(id, pw, isForce) => {
    let res;
    try{
        res = await axios.post('http://localhost:8000/user-service/sign_in', {
            id : id,
            pw : pw,
            isForce : isForce
        })
    }catch(err){
        throw new Error(err.message);
    }
    return res;
}
const sendEmail = (email) => {

}


export default {
    checkId, checkNickName, sendEmail,
    signIn, 
}