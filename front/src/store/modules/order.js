/* eslint-disable */
import {order as orderService} from '../../service'; 


const order = {
    state:{
        orderList : [],
    },
    getter:{
        get_order_items(state){
            return state.orderList;
        }
    },
    mutations: {
        order_set_items(state, data){
            state.catalogs= data;
        }
    },
    actions:{
        async setting_order_items({commit}, data){
            console.log(data);
            let res;
            try {
                res = await orderService.getOrderList(data.userIdx);
            }catch(err){
                console.log(err.message);
            }
            console.log(res);
            commit('order_set_items', res.data);
        },
    }
}

export default order