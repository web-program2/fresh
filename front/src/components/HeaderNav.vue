<template>
    <v-card class="mx-auto overflow-hidden">
        <v-app-bar app="app" color="white" >
            <!-- pc version -->
            <v-row justify='center' >
                <v-col cols='3'>
                    <router-link to="/home">
                        <v-img  src="@/assets/fresh_logo.png" width="310" height="50" />
                    </router-link>
                </v-col>
                <v-col cols='1' >
                    <router-link to="/about">
                        <v-btn text="text" rounded="rounded"  style="margin-top:15px; font-size:15px; font-weight: 600; ">
                            FRESH 소개
                        </v-btn>
                    </router-link>
                </v-col>
                <v-col cols='1' >
                    <router-link to="/catalog">
                        <v-btn text="text" rounded="rounded"  style="margin-top:15px; font-size:15px; font-weight: 600; ">
                            장터
                        </v-btn>
                    </router-link>
                </v-col>
                <v-col cols='1' >
                    <router-link to="/notice">
                        <v-btn text="text" rounded="rounded"  style="margin-top:15px; font-size:15px; font-weight: 600; ">
                            공지
                        </v-btn>
                    </router-link>          
                </v-col>
                <v-spacer />
                
                <v-col cols='2' id="loginWelcomeWrapper"   v-if="isLogin()">
                    <span id="loginWelcome" >{{nickName}} <p id="showRole">[{{role}}]</p></span>&nbsp;&nbsp;&nbsp;
                </v-col>
                <v-col cols='1'   v-if="isLogin()">
                    <router-link to="/order">
                        <v-btn id="infoBtn" elevation="2" outlined="outlined" rounded="rounded" >주문내역</v-btn>
                    </router-link>
                </v-col>
                <v-col cols='1'   v-if="isLogin()">
                    <v-btn id="logoutBtn" elevation="2" outlined="outlined" rounded="rounded" v-on:click="logout" >로그아웃</v-btn>
                </v-col>
                <v-col cols='1'  v-if="isLogin()">
                    <router-link to="/admin">
                        <v-btn id="admin" v-if="admin()" elevation="2" outlined="outlined" rounded="rounded">관리자</v-btn>
                    </router-link>
                </v-col>
                
                <v-col cols='3' v-else>
                    <router-link to="/auth/signin"   >
                        <v-btn elevation="2" class="btn_loginEnter" outlined="outlined" rounded="rounded">로그인</v-btn>
                    </router-link>
                    <router-link to="/auth/signup"  >
                        <v-btn elevation="2" class="btn_loginEnter" outlined="outlined" rounded="rounded">회원가입</v-btn>
                    </router-link>
                </v-col>    
            </v-row>
        </v-app-bar>
    </v-card>


</template>

<script>

    export default {
        name: 'HeaderNav',
        props: {
            
        },
        created(){
            this.initialize();
        },
        data(){
            return{
                nickName : '',
                role : '',
                drawer: false,
                userDrawer: false,
                group: null,
            }
        },
        computed: {
            userData : function(){
                return this.$store.getters.auth_get_data;
            },
            accessToken : function(){
                return localStorage.getItem('accessToken');
            }
        },
        methods : {
            initialize(){
               if(this.accessToken !== null){
                   return true;
               } else{
                   return false;
               }
            },
            async logout(){
                localStorage.removeItem("accessToken");
                localStorage.removeItem("refreshToken");
                try{
                    
                }catch(err){
                    console.log(err);
                }
                this.$router.go();//새로고침
            },

            isLogin(){
                if(this.accessToken != null){
                    this.nickName = this.userData.nickName;
                    this.role = this.userData.role;
                    return true;
                }else {
                    return false;
                }
            },
            admin(){
                if(this.userData.role === 'admin'){
                    return true;
                }else {
                    return false;
                }
                
            }
            
        }
    }
</script>

<style scoped>

@media all and (max-width:767px) {
    #pc{
        display: none; 
    }
}
@media all and (max-width:1023px) {
    #pc{
        display: none;
    }
}
@media all and (min-width:1024px) {
   .mobile {
        display: none;
    }
}

    .menus{
        font-family: 'ROKABold';
    }
    #title {
        display: inline; 
        padding-left: 50px;
        font-family: 'Recipekorea';
        font-size:35px;
        cursor:pointer;
    }
    #subtitle{
        padding-left:5px;
        display: inline;
        font-family: 'S-CoreDream-3Light';
    }
   
    .btn_menu{
        display: table-cell;
        vertical-align: middle;
    }
    .btn_loginEnter, #infoBtn, #logoutBtn, #admin{
        margin-top:10px;
         color:#546E7A;
    }
    .btn_loginEnter:hover{
        background-color: #F0F4C3;
        color:#3E2723;
    }
    #infoBtn, #logoutBtn, #admin{
        margin-top:15px;
    }
    #loginWelcomeWrapper{
         /* padding-top: 1.5%; */
        line-height: 64px;   text-align: center;

    }
    #loginWelcome{
        vertical-align: middle;
        font-family: 'Chosunilbo_myungjo';
        font-weight: 400;
        font-weight: normal;
    }
    #showRole{
        font-size: 0.6em; display: inline; color: darkgreen;
    }
@font-face {
    font-family: 'ROKABold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2104@1.1/ROKABold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'Recipekorea';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Recipekorea.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
     font-family: 'S-CoreDream-3Light';
     src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff') format('woff');
     font-weight: normal;
     font-style: normal;
}
</style>
