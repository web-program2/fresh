<template>
  <v-container>
    <v-container class="pc">
      <br>
      <v-row justify="center">
        <v-col cols='4'>
          <router-link to="/home">
            <div style="text-align: center;"> <h2 style="display : inline">FRESH</h2>&nbsp;&nbsp;&nbsp;깨끗한 농산물 도매</div>
          </router-link>
        </v-col>
      </v-row>
      <br>
      <router-view />
      <v-row justify="center">
        <v-col cols='4'>
          <div style="text-align: center;">© 2022 웹프로그래밍2</div>
        </v-col>
      </v-row>
    </v-container>

    <v-container class="mobile">
      <br>
      <v-row justify="center">
        <v-col cols='8'>
          <router-link to="/home">
            <div style="text-align: center;"> <h2 style="display : inline">FRESH</h2></div>
          </router-link>
        </v-col>
      </v-row>
      <br>
      <router-view />  

      <v-row justify="center">
        <v-col cols='12'>
          <div style="text-align: center;">© 2022 웹프로그래밍2 </div>
        </v-col>
      </v-row>
    </v-container>
  </v-container>
</template>
<style scoped>
@media all and (max-width:767px) {
    .pc{
        display: none;
    }
}
@media all and (max-width:1023px) and (min-width:767px) {
    .pc{
        display: none;
    }
    /* 태블릿은 잘보임 */
}
@media all and (min-width:1024px) {
    .mobile{
        display: none;
    }
}
</style>
<script>
export default {
  name: 'AuthLayout',
  components: {
  },
  data: () => ({
    //
  }),
  methods : {
      
    }
};
</script>
