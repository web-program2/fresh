/* eslint-disable */
import {catalog as catalogService} from '../../service'; 


const catalog = {
    state:{

    },
    getter:{

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
    }
}

export default catalog