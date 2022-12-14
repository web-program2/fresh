/* eslint-disable */
import {anonymous as anonymousService} from '../../service'; 
import jwt_decode from 'jwt-decode';

const anonymous = {
    state:{
        userData : {},
    },
    getters:{
        auth_get_data (state) {
            return state.userData;
        },
    },
    mutations: {
        auth_set_data (state, authData) { // 로그인 한 유저 데이터 저장
            state.userData = {...authData};
        },
    },
    actions:{
        async check_id({commit},data){
            let res;
            try{
                res = anonymousService.checkId(data.id);
            }catch(err){
                console.log(err);
            }
            // 존재하면 true, 안 존재하면 false
            return res;
        },
        async check_nick_name({commit},data){
            let res;
            try{
                res = anonymousService.checkNickName(data.nickName);
            }catch(err){
                console.log(err);
            }
            // 존재하면 true, 안 존재하면 false
            return res;
        },
        async send_email({commit},data){
            let res;
            try{
                res = await anonymousService.sendEmail(data.email);
            }catch(err){
                console.log(err);
            }
            // 존재하면 true, 안 존재하면 false
            return res.data;
        },
        async check_auth_email({commit}, data){
            const {email, no} = data;
            let res;
            try{
                res = await anonymousService.checkAuthEmail(email, no);
            }catch(err){
                console.log(err.message);
            }
            return res.data
        },
        async sign_in({commit}, input){
            const {id, pw, isForce} = input;
            let res;
            try{
                res = await anonymousService.signIn(id, pw, isForce);
            }catch(err){
                console.log(err.message);
            }
            const resData = res.data;
            if(resData.message){
                const errMessage = resData.message;
                throw new Error(errMessage);
            }
            // 로그인성공 시
            localStorage.setItem("accessToken", resData.accessToken);
            localStorage.setItem("refreshToken", resData.refreshToken);
            await this.dispatch('get_user_data', resData.accessToken ); //login part라서 return값이 불 필요.
            history.back();
            return res;
            // 존재하면 true, 안 존재하면 false
        },
        async get_user_data({commit}, token){
            let userData;
            try{
                userData = await jwt_decode(token); // id, nickName, email, role, userIdx
            }catch(err){
                console.log(err.message);
            }
            commit('auth_set_data', userData);
        },
        async sign_up({commit}, data) {
            console.log(data);
            let res;
            try{
                res = await anonymousService.signUp(data);
            }catch(err){
                console.log(err);
            }
        }
        
    }
}

export default anonymous