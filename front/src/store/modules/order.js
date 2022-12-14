/* eslint-disable */
import {order as orderService} from '../../service'; 


const order = {
    state:{
        orderList : [],
        orderData : {},
    },
    getters:{
        get_order_items(state){
            return state.orderList;
        },
        get_order_data(state){
            return state.orderData;
        }
    },
    mutations: {
        order_set_items(state, data){
            state.orderList= data;
        },
        set_order_data(state, data){
            state.orderData = data;
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
            return;
        },
        async get_order({commit}, data){
            console.log(data.orderIdx)
            let res;
            try{
                res = await orderService.getOrderData(data.orderIdx);
            }catch(err){
                console.log(err);
            }
            console.log(res.data)
            commit('set_order_data', res.data);
        }
    }
}

export default order