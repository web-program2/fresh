/* eslint-disable */
import {catalog as catalogService, order as orderService} from '../../service'; 


const catalog = {
    state:{
        catalogList : [],
        catalogData : {
            
        },
    },
    getters:{
        get_catalog_items(state){
            return state.catalogList;
        },
        get_catalog_data(state){
            return state.catalogData;
        }
    },
    mutations: {
        catalog_set_items(state, data){
            state.catalogList= data;
        },
        set_catalog(state, data){
            state.catalogData = data;
        }
    },
    actions:{
        async delete_catalog({commit}, data){
            let res;
            try{
                res= await catalogService.deleteCatalog(data.catalogIdx);
            }catch(err){
                console.log(err.message);
            }
        },
        async setting_catalog_items({commit}, data){
            let res;
            try{
                res = await catalogService.getCatalogItems();
            }catch(err){
                throw new Error(err.message);
            }
            console.log(res.data)
            commit('catalog_set_items', res.data);
            return;
        },
        async get_catalog({commit}, data){
            let res;
            try{
                res = await catalogService.getCatalog(data.catalogIdx);
            }catch(err){
                throw new Error(err.message);
            }
            const tempData = {
                user : {...res.data.responseUser},
                catalog : {...res.data.catalog}
            }
            commit('set_catalog', tempData);
            return;
        },
        async create_catalog_item({commit}, data){
            console.log(data);
            let res;
            try{
                res = await catalogService.createCatalogItem(data);
            }catch(err){
                console.log(err);
            }
        },
        async create_order({commit}, data){
            try{
                await orderService.createOrder(data);
            }catch(err){
                console.log(err);
            }
            return true;
        }
    }
}

export default catalog