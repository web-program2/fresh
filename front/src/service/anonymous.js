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
const sendEmail = async (email) => {
    let res;
    try{
        res = await axios.post('http://localhost:8000/user-service/send_mail', {
            email : email,
        })
    }catch(err){
        throw new Error(err.message);
    }
    return res;
}
const checkAuthEmail = async (email, no) => {
    let res;
    try{
        res = await axios.post('http://localhost:8000/user-service/check_mail_no', {
            email : email,
            no : no
        })
    }catch(err){
        throw new Error(err.message);
    }
    return res;
}
const signUp = async (data) => {
    let res;
    try{
        res = await axios.post('http://localhost:8000/user-service/sign_up', {
            id : data.id,
            pw : data.pw,
            nickName : data.nickName,
            email : data.email,
            role : data.role,
        })
    }catch(err){
        throw new Error(err.message);
    }
    return res;
}


export default {
    checkId, checkNickName, sendEmail,
    signIn, checkAuthEmail, signUp
}