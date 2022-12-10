<template>
    <v-contatiner>
        <v-row justify='center' id="ideaItem" v-on:click = "clickIdea(ideaIdx)">
            <v-col cols='1' />
            <v-col cols='3' class="table">
                {{name}}
            </v-col>
            <v-col cols='2' class="table">
                {{price}}
            </v-col>
            <v-col cols='3' class="table">
                {{userName}}
            </v-col>
            <v-col cols='2' class="table">
                {{stock}}
            </v-col>
            <v-col cols='1' class="table"></v-col>
        </v-row>

    </v-contatiner>
</template>
<script>
    
    export default{
        props: [
            "name",
            "price",
            "userName",
            "content",
            "stock",
        ],
    }

</script>