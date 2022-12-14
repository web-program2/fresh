<template>
    <v-contatiner>
        <v-row justify='center'  v-on:click = "clickCatalog(catalogIdx)">
            <v-col cols='1' />
            <v-col cols='3' class="table">
                {{name}}
            </v-col>
            <v-col cols='2' class="table">
                {{price}}원
            </v-col>
            <v-col cols='2' class="table">
                {{stock}}개
            </v-col>
            <v-col cols='4' class="table">
                {{content}}
            </v-col>
        </v-row>

    </v-contatiner>
</template>
<script>
    export default{
        name : 'CatalogItem',
        props: [
            "catalogIdx",
            "name",
            "price",
            "stock",
            "content",
            "userIdx",
        ],
        methods : {
            clickCatalog(catalogIdx){
                this.$router.push({path: `catalog/${catalogIdx}`});
            },
        }
    }

</script>