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
const checkNickName = async(nickName) => {
    let res;
    try{
        res = await axios.post('http://localhost:8000/user-service/check_nickname', {
            nickName : nickName
        })
    }catch(err){
        console.log(err);
    }
    console.log(res);
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
        console.log(err);
    }
    console.log(res);
}
const sendEmail = (email) => {

}


export default {
    checkId, checkNickName, sendEmail,
    signIn, 
}