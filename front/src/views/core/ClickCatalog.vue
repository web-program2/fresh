<template>
    <v-container>
        aaa
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
        // ideaData : function(){
        //     return this.$store.getters.click_idea_get_data;
        // },
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
            console.log(this.catalogIdx)
        }
    }

}
</script>