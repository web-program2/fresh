<template>
    <v-container>
        <br>
        <v-row>
            <v-col>
                <h1>상품 주문하기</h1> 
            </v-col>
            <!-- <v-col cols="2" v-if="isMine()">
                <v-btn>
                    수정
                </v-btn>
            </v-col> -->
            <v-col cols="2" v-if="isMine()" v-on:click="deleteCatalog">
                <v-btn>
                    삭제
                </v-btn>
            </v-col>
        </v-row>
        <br>
        <hr><br><h1>상품 정보</h1><br>
        <v-row >
            <v-col cols='3' class="table">
                상품 번호 : 
            </v-col>
            <v-col cols='2' class="table">
                {{catalogData.catalog.catalogIdx}}
            </v-col>
        </v-row>
        <v-row >
            <v-col cols='3' class="table">
                상품 이름 : 
            </v-col>
            <v-col cols='2' class="table">
                {{catalogData.catalog.name}}
            </v-col>
        </v-row>
        <v-row >
            <v-col cols='3' class="table">
                상품 가격 : 
            </v-col>
            <v-col cols='2' class="table">
                {{catalogData.catalog.price}}
            </v-col>
        </v-row>
        <v-row >
            <v-col cols='3' class="table">
                상품 재고 : 
            </v-col>
            <v-col cols='2' class="table">
                {{catalogData.catalog.stock}}
            </v-col>
        </v-row>
        <v-row >
            <v-col cols='3' class="table">
                상품 내용 : 
            </v-col>
            <v-col cols='2' class="table">
                {{catalogData.catalog.content}}
            </v-col>
        </v-row>
        <br><hr><br>
        <h1> 판매자 정보 !!</h1><br>
        <v-row >
            <v-col cols='3' class="table">
                판매자 이름 : 
            </v-col>
            <v-col cols='2' class="table">
                {{catalogData.user.nickName}}
            </v-col>
        </v-row>
        <v-row >
            <v-col cols='3' class="table">
                이메일 : 
            </v-col>
            <v-col cols='2' class="table">
                {{catalogData.user.email}}
            </v-col>
        </v-row>
        <br><br>
        <v-row justify="center">
            <v-col cols="4">
                <v-text-field
                    label="구매할 수량 입력"
                    v-model="qty"
                    hide-details="auto"
                ></v-text-field>
            </v-col>
            <v-btn style="height : 60px" v-on:click="orderCatalog">
                구매하기
            </v-btn>
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
        catalogData : function(){
            return this.$store.getters.get_catalog_data;
        },
        userData : function(){
            return this.$store.getters.auth_get_data;
        }
    },
    data(){
        return {
            catalogIdx: this.$route.params.catalogIdx,
            qty : 1,
        }
    },
    methods : {
        async initialize(){
           await this.showCatalog();
        },
        isMine(){
            const nowUser = this.userData.userIdx;
            const catalogUser = this.catalogData.user.userIdx;
            if(nowUser === catalogUser){
                return true;
            }
            return false;
        },
        async showCatalog(){
            try{
                await this.$store.dispatch('get_catalog', {
                    catalogIdx : this.catalogIdx
                })
            }catch(err){
                console.log(err);
            }
        },
        async orderCatalog(){
            if(this.qty > this.catalogData.catalog.stock){
                alert('죄송합니다. 재고 수량이 부족합니다.'); return;
            }
            try{
                await this.$store.dispatch('create_order', {
                    catalogIdx : parseInt(this.catalogIdx),
                    userIdx : parseInt(this.userData.userIdx),
                    orderQty : this.qty,
                    price : this.catalogData.catalog.price,
                })
            }catch(err){
                console.log(err);
            }
            alert('성공적으로 주문 완료 하였습니다.');
            this.$router.push('/');
        },
        async deleteCatalog(){
            const catalogIdx =  catalogData.catalog.catalogIdx;
            let flag = confirm('정말 삭제하시겠습니까 ?');
            if(!flag) return;
            try{
                await this.$store.dispatch('delete_catalog', {
                    catalogIdx : catalogIdx
                })
            }catch(err){
                console.log(err.message);
            }
            alert('성공적으로 삭제 완료 하였습니다.');
            this.$router.push('/');
        }
    }
}
</script>