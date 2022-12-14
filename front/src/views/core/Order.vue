<template>
    <v-container>
        <v-row justify='center'>
            <v-col cols='1' />
            <v-col cols='3' class="table">
                주문 번호
            </v-col>
            <v-col cols='2' class="table">
                주문 수량
            </v-col>
            <v-col cols='2' class="table">
                개당 가격
            </v-col>
            <v-col cols='3' class="table">
                총 금액
            </v-col>
            <v-col cols='1' class="table" />
        </v-row>
        <br><hr> <br>
        <v-row justify='center'>
            <v-col cols='12'>
                <OrderItem style="cursor:pointer"  v-for="(item, index) in orderList"  :key="index" 
                :orderIdx="item.orderIdx"
                :qty="item.qty"
                :unitPrice="item.unitPrice" 
                :userIdx="item.userIdx"
                :catalogIdx="item.catalogIdx"
                /> 
            </v-col>
        </v-row>
    </v-container>
</template>
<script>
import OrderItem from '../../components/OrderItem.vue';
export default {
    created(){
        this.settingOrderItems();
    },
    components : {
        OrderItem
    },
    data(){
        return {

        }
    },
    computed : {
        orderList : function(){
            return this.$store.getters.get_order_items;
        },
        userData : function(){
            return this.$store.getters.auth_get_data;
        },
    },
    methods : {
        async settingOrderItems(){
            let res;
            try{
                res= await this.$store.dispatch('setting_order_items', {
                    userIdx : this.userData.userIdx
                })
            }catch(err){
                console.log(err);
            }
            return true;
        },
    }
}
</script>