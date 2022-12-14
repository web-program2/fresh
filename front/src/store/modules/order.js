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
            console.log(state.catalogs)
        }
    },
    actions:{
        async setting_order_items({commit}, data){
            let res;
            try {
                res = await orderService.getOrderList(data.userIdx);
            }catch(err){
                console.log(err.message);
            }
            commit('order_set_items', res.data);
        },
    }
}

export default order