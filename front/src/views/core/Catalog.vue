<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="8" />
            <v-col cols="2">
                <router-link to="/add-catalog">
                    <v-col cols='1'  >
                        <v-btn block outlined  style="height : 60px" v-on:click="createCatalog()">
                            상품 등록하기
                        </v-btn>
                    </v-col>
                </router-link>
            </v-col>
            <v-col cols="2" />
        </v-row>
        <v-row justify='center'>
            <v-col cols='1' />
            <v-col cols='3' class="table">
                상품이름
            </v-col>
            <v-col cols='2' class="table">
                가격
            </v-col>
            <v-col cols='2' class="table">
                재고량
            </v-col>
            <v-col cols='4' class="table">
                내용
            </v-col>
        </v-row>
        <v-row justify='center'>
            <v-col cols='12'>
                <CatalogItem style="cursor:pointer"  v-for="(item, index) in catalogList"  :key="index" 
                :catalogIdx="item.catalogIdx"
                :name="item.name" 
                :price="item.price" 
                :stock="item.stock"
                :content="item.content"
                :userIdx="item.userIdx" 
                /> 
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import CatalogItem from '../../components/CatalogItem.vue';
export default {
    name: 'catalog',
    created (){
        this.settingCatalogItems();
    },
    components : {
        CatalogItem
    },
    data(){
        return {
            
        }
    },
    computed : {
        catalogList : function(){
            return this.$store.getters.get_catalog_items;
        },
        userData : function(){
            return this.$store.getters.auth_get_data;
        },
    },
    methods : {
        async settingCatalogItems(){
            let res;
            try{
                res= await this.$store.dispatch('setting_catalog_items', {
                })
            }catch(err){
                console.log(err);
            }
            return true;
        },
        createCatalog(){
            if(this.userData.role !== 'seller'){
                alert('판매자만 상품을 판매할 수 있습니다.'); 
                this.$router.go(-1);  return;
            }
            
        }
    }
    
}
</script>