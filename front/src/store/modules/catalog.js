/* eslint-disable */
import {catalog as catalogService} from '../../service'; 


const catalog = {
    state:{
        catalogs : [
            {
                name : 'aaaa',
                price : '12312',
                user : {
                    name : 'afdaadsfasaa'
                },
                stock : 11
            },
            {
                name : 'bbb',
                price : '12312',
                user : {
                    name : 'dfas'
                },
                stock : 11
            },
        ],
    },
    getters:{
        get_catalog_items(state){
            return state.catalogs;
        }
    },
    mutations: {
        catalog_set_items(state, data){
            state.catalogs= data;
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