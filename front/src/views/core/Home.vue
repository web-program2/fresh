<template>
    <v-container style=""> 
        <!-- pc row -->
        <v-row id="pc_main" class="main">
            <v-col cols="'10">
                <v-row >
                    <v-col cols='1'>
                    </v-col >
                    <v-col cols='11'>
                        <v-col class="imageWrapper"  style="text-align: center;">
                            <div id="imageText">
                                <h1 id="mfi_mean">Fresh 장터</h1> <br><br>
                                <p class="subtitle">신선한 농산물은 싼 가격에 살 수 있는 장소입니다.</p>
                            </div>
                        </v-col>
                    </v-col>
                    <br> <br><br><br><br>
                </v-row >
                <v-row justify="center" > 
                    <v-col cols='1' />
                    <v-col col='11'>
                        <hr id="line">
                    </v-col>
                </v-row>
                <v-row justify="center" >
                    <v-col cols='3' />
                    <v-col cols="9">
                        <h2 style="font-size : 30px;">Fresh Highlight</h2> 
                        <h4>최근 올라온 상품을 확인해볼 수 있습니다.</h4>
                    </v-col>
                </v-row>
                <!-- 최근 상품 6개 가지고오기 -->
                <v-row justify="center" style="text-align : center" >
                    <v-col cols="12">
                        
                    </v-col>
                 </v-row>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
/* eslint-disable */
import MainCatalogItem from '../../components/MainCatalogItem.vue';

  export default {
    name: 'Home',
    components: {
        MainCatalogItem
    },
    created() {

    },
    computed : {

    },
    data() {
        return {
            userComponent : ConnectionUser,
            items : this.connectionUserList
        }
    },
    methods : {
       
    }

  }
</script>

<style scoped>
@media all and (max-width:767px) {
    #pc_main, .pc_margin, .pc_item{
        display: none;
    }
}
@media all and (max-width:1023px) {
    #pc_main, .pc_margin, .pc_item{
        display: none;
    }
}
@media all and (min-width:1024px) {
    .mobile_item{
        display: none;
    }
}
.imageWrapper{
    background-image: url("../../assets/fresh.png"); background-repeat : no-repeat;
    background-size : cover; opacity: 1;
}
.main{
    position: relative;
}
.chatting{
    position:fixed; bottom: 50px; left: 100px;
}

#mfi_mean{
   font-family: 'Chosunilbo_myungjo'; color: rgb(8, 0, 82); line-height: 200px;
    font-size:80px; 
}
.subtitle{
    font-family: 'Chosunilbo_myungjo';color: white; 
    font-size:22px;font-weight: 900;
}

#main_center{
    width: 75%;
}

#circle{
    width: 25px; display: inline-block; height: 25px; color: greenyellow;
    border-radius: 50%; background: greenyellow;
}
.vueSlider{
    font-size:30px;
}
image {
    -webkit-filter: blur(5px); -moz-filter: blur(5px); -o-filter: blur(5px); -ms-filter: blur(5px); filter: blur(5px);
}
.model {
    padding: 10px 10px 10px 10px; 
}
.modelContainer{
    height: 400px;
}
#line{
    border : solid 2px #43A047;
}
.mobile_container{
    height: 300px;
}
.mobile_img{
    width: 150px; height: 150px;
}
.mobile_article{
    width: 100%; height: 150px; margin:0 auto; text-align: center;
}
.mobile_article_content{
    width: 100%; text-align: center;
}
.div_article{
    width: 100%;
    height:150px;
    overflow:hidden;
    margin:0 auto;
}
.article{
    width:130px;
    height:150px;
    object-fit:cover;
}
.article_content{
    width: 100%;
    height:150px;
    text-align: center;
    /* display: table-cell; */
    vertical-align: middle;
    font-family: 'S-CoreDream-3Light';
    font-weight: bold;
}

@font-face {
    font-family: 'SLEIGothicTTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2104@1.0/SLEIGothicTTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'Chosunilbo_myungjo';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/Chosunilbo_myungjo.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
     font-family: 'S-CoreDream-3Light';
     src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff') format('woff');
     font-weight: normal;
     font-style: normal;
}
@font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
</style>