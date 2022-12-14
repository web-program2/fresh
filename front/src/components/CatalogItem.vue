<template>
    <v-contatiner>
        <v-row justify='center' id="ideaItem" v-on:click = "clickCatalog(catalogIdx)">
            <v-col cols='1' />
            <v-col cols='3' class="table">
                {{name}}
            </v-col>
            <v-col cols='2' class="table">
                {{price}}
            </v-col>
            <v-col cols='3' class="table">
                {{stock}}
            </v-col>
            <v-col cols='2' class="table">
                {{content}}
            </v-col>
            <v-col cols='1' class="table"></v-col>
        </v-row>

    </v-contatiner>
</template>
<script>
    
    export default{
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