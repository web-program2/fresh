/* eslint-disable */
import {anonymous as anonymousService} from '../../service'; 


const anonymous = {
    state:{

    },
    getters:{

    },
    mutations: {

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
            console.log(res);
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
        async sign_up({commit}, data) {
            console.log(data);
        }
        
    }
}

export default anonymous