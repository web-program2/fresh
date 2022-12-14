<template>
    <v-contatiner>
        <v-row justify='center' id="ideaItem" v-on:click = "clickOrder(orderIdx)">
            <v-col cols='1' />
            <v-col cols='3' class="table">
                {{orderIdx}}
            </v-col>
            <v-col cols='2' class="table">
                {{qty}}
            </v-col>
            <v-col cols='2' class="table">
                {{unitPrice}}
            </v-col>
            <v-col cols='3' class="table">
                {{totalPrice}}
            </v-col>
            <v-col cols='1' class="table" />
        </v-row>
    </v-contatiner>
</template>
<script>
    
    export default{
        props: [
            "orderIdx",
            "qty",
            "unitPrice",
            "userIdx",
            "catalogIdx",
        ],
        computed : {
            totalPrice : function(){
                return (this.qty*this.unitPrice);
            }
        },
        methods : {
            clickOrder(orderIdx){
                this.$router.push({path: `order/${orderIdx}`});
            },
        }
    }



</script>