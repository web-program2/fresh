<template>
    <v-container>
        <hr><h1>주문 조회 내역</h1>
        <v-row>
            <v-col cols='3' class="table">
                주문 번호 : 
            </v-col>
            <v-col cols='2' class="table">
                {{orderData.order.orderIdx}}
            </v-col>
        </v-row>
        <v-row>
            <v-col cols='3' class="table">
                주문 수량 : 
            </v-col>
            <v-col cols='2' class="table">
                {{orderData.order.qty}}
            </v-col>
        </v-row>
        <v-row>
            <v-col cols='3' class="table">
                개당 가격 : 
            </v-col>
            <v-col cols='2' class="table">
                {{orderData.order.unitPrice}}원
            </v-col>
            <v-col cols='3' class="table">
                 -> 총 가격 : {{totalPrice}}원
            </v-col>
            <v-col cols='2' class="table">
                
            </v-col>
        </v-row>
        <br><br><hr><h1>상품 내역</h1>
        <v-row>
            <v-col cols='3' class="table">
                상품 번호 : 
            </v-col>
            <v-col cols='2' class="table">
                {{orderData.responseCatalog.catalogIdx}}
            </v-col>
        </v-row>
        <v-row>
            <v-col cols='3' class="table">
                상품 이름 : 
            </v-col>
            <v-col cols='2' class="table">
                {{orderData.responseCatalog.name}}
            </v-col>
        </v-row>
        <v-row>
            <v-col cols='3' class="table">
                상품 현재 재고량 : 
            </v-col>
            <v-col cols='2' class="table">
                {{orderData.responseCatalog.stock}}개
            </v-col>
        </v-row>
        
        <br><br><hr><h1>구매자 정보(본인정보)</h1>
        <v-row>
            <v-col cols='3' class="table">
                닉네임
            </v-col>
            <v-col cols='2' class="table">
                {{userData.nickName}}
            </v-col>
        </v-row>
        <v-row>
            <v-col cols='3' class="table">
                이메일
            </v-col>
            <v-col cols='2' class="table">
                {{userData.email}}
            </v-col>
        </v-row>
    </v-container>

</template>
<script>
export default {
    created(){
        this.initialize();
    },
    mounted(){
       
    },
    computed: {
        orderData : function(){
            return this.$store.getters.get_order_data;
        },
        userData : function(){
            return this.$store.getters.auth_get_data;
        },
        totalPrice : function(){
            return this.orderData.order.unitPrice*this.orderData.order.qty;
        }
    },
    data(){
        return {
            orderIdx: this.$route.params.orderIdx,
        }
    },
    methods : {
        async initialize(){
           await this.showOrder();
        },
        async showOrder(){
            try{
                await this.$store.dispatch('get_order', {
                    orderIdx : this.orderIdx
                })
            }catch(err){
                console.log(err);
            }
        }
    }

}
</script>