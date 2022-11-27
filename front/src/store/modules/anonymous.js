/* eslint-disable */
import {anonymous as anonymousService} from '../../service'; 


const anonymous = {
    state:{

    },
    getter:{

    },
    mutations: {

    },
    actions:{
        async check_id({commit},id){
            let res;
            try{
                res = anonymousService.checkId(id);
            }catch(err){
                console.log(err);
            }
            // 존재하면 true, 안 존재하면 false
            return false;
        },
        async check_nick_name({commit},nickName){
            let res;
            try{
                res = anonymousService.checkNickName(nickName);
            }catch(err){
                console.log(err);
            }
            // 존재하면 true, 안 존재하면 false
            return false;
        },
        async send_email({commit},email){
            let res;
            try{
                res = anonymousService.sendEmail(email);
            }catch(err){
                console.log(err);
            }
            // 존재하면 true, 안 존재하면 false
            return false;
        },
        
    }
}

export default anonymous