<template>
    <v-container>
        <hr><h1>상품 정보</h1>
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
        <hr>
        <h1> 판매자 정보 !!</h1>
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
        }
    },
    methods : {
        async initialize(){
           await this.showCatalog();
        },
        async showCatalog(){
            try{
                await this.$store.dispatch('get_catalog', {
                    catalogIdx : this.catalogIdx
                })
            }catch(err){
                console.log(err);
            }
        }
    }

}
</script>