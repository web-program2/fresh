<template>
    <v-container>
        <v-row justify="center">
            <v-col cols='3'>
                <h1>상품 등록</h1>
            </v-col>
        </v-row>
        <v-row justify="center">
            <v-col cols='3'>
                <v-text-field
                    label="상품이름 입력"
                    v-model="name"
                    hide-details="auto"
                ></v-text-field>
            </v-col>
        </v-row>
        <v-row justify="center">
            <v-col cols='3'>
                <v-text-field
                    label="가격 입력"
                    v-model="price"
                    hide-details="auto"
                ></v-text-field>
            </v-col>
        </v-row>
        <v-row justify="center">
            <v-col cols='3'>
                <v-text-field
                    label="재고량 입력"
                    v-model="stock"
                    hide-details="auto"
                ></v-text-field>
            </v-col>
        </v-row>
        <v-row justify="center">
            <v-col cols='3'>
                <v-text-field
                    label="상품설명 입력"
                    v-model="content"
                    hide-details="auto"
                ></v-text-field>
            </v-col>
        </v-row>
        <v-row justify="center">
            <v-col cols="4" >
                <v-btn
                    elevation="2" block
                    v-on:click="createCatalog()" >
                    상품 등록하기
                </v-btn>
            </v-col>      
        </v-row>
    </v-container>
</template>

<script>
export default {
    computed : {
        userData : function(){
            return this.$store.getters.auth_get_data;
        },
    },
    data(){
        return {
            name : '',
            price : 0,
            stock : 0,
            content : '',
        }
    },
    methods : {
        async createCatalog(){
            //////////////////////// 전처리 파트 ////////////////////////
            // if(this.userData.role !== 'seller'){
            //     alert('판매자만 글을 올릴 수 있습니다!'); return;
            // }
            console.log()
            if(!!Number(this.price)=== false){
                alert('가격을 숫자로 입력해주세요.(1 이상 입력)'); return;
            }
            if(!!Number(this.stock) === false){
                alert('재고량을 숫자로 입력해주세요.(1 이상 입력)'); return;
            }
            //////////////////////////////////////////////////////////
            let res;
            try{
                res= await this.$store.dispatch('create_catalog_item', {
                    name : this.name,
                    price : this.price,
                    stock : this.stock,
                    content : this.content,
                    userIdx : this.userData.userIdx
                })
            }catch(err){
                console.log(err);
            }

        }
    }
}
</script>