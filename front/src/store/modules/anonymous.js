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
                res = anonymousService.sendEmail(data.email);
            }catch(err){
                console.log(err);
            }
            // 존재하면 true, 안 존재하면 false
            return false;
        },
        async sign_in({commit},data){
            const {id, pw, isForce} = data;
            try{
                res = anonymousService.signIn(id, pw, isForce);
            }catch(err){
                if(err.message === 'wrong pw'){
                    throw new Error(err.message);
                }
                if(err.message === 'wrong id'){
                    throw new Error(err.message);
                }
                if(err.message === 'isLogin'){
                    throw new Error(err.message);
                }
            }
            // 로그인성공 시
            if(res){
                localStorage.setItem("accessToken", res.token);
                localStorage.setItem("refreshToken", res.refreshToken);
                await this.dispatch('get_user_data', res.token ); //login part라서 return값이 불 필요.
                history.back();
                return res;
            }
            // 존재하면 true, 안 존재하면 false
            return false;
        },
        
    }
}

export default anonymous