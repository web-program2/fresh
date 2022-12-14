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
            orderIdx: this.$route.params.orderIdx,
        }
    },
    methods : {
        async initialize(){
           await this.showOrder();
        },
        async showOrder(){
            console.log(this.orderIdx)
        }
    }

}
</script>