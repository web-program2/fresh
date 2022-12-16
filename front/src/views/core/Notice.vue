<template>
    <v-container>
        공지사항
    </v-container>
</template>

<script>
export default {
    name: 'notice',
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