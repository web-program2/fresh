/* eslint-disable */
import {catalog as catalogService} from '../../service'; 


const catalog = {
    state:{
        catalogList : [],
    },
    getters:{
        get_catalog_items(state){
            return state.catalogList;
        }
    },
    mutations: {
        catalog_set_items(state, data){
            state.catalogList= data;
        }
    },
    actions:{
        async setting_catalog_items({commit}, data){
            let res;
            try{
                res = await catalogService.getCatalogItems();
            }catch(err){
                throw new Error(err.message);
            }
            commit('catalog_set_items', res.data);
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
        }
    }
}

export default catalog