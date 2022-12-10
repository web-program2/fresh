<template>
    <v-container>
        <v-row justify='center'>
            <v-col cols='1' />
            <v-col cols='3' class="table">
                상품이름
            </v-col>
            <v-col cols='2' class="table">
                가격
            </v-col>
            <v-col cols='3' class="table">
                작성자
            </v-col>
            <v-col cols='2' class="table">
                재고
            </v-col>
            <v-col cols='1' class="table"></v-col>
        </v-row>
        <v-row justify='center'>
            <v-col cols='12'>
                <CatalogItem style="cursor:pointer"  v-for="(item, index) in catalogItem"  :key="index" 
                :name="item.name" 
                :price="item.price" 
                :userName="item.user.name" 
                :stock="item.stock"
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
        catalogItem : function(){
            return this.$store.getters.get_catalog_items;
        }
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
        }
    }
    
}
</script>