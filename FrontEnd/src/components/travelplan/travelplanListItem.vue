<template>
    <tr class="text-center">
      <th><router-link :to="'/travelplan/view/' + article.articleNo" style="background-color: transparent; color: #000; text-decoration-line: none">{{article.location}}</router-link></th>
      <th>{{user.userNickname}}</th>
      <th>{{article.hit}}</th>
      <th>{{article.registerTime}}</th>
    </tr>
  </template>
  
  
  
  
  
  <script>
  import axios from "axios";
  export default {
    name: "TravelPlanListItem",
    props:{
        article: Object,
       
    },
    data() {
      return {
        user:[]
      }
    },

    
    created() {
      axios.get(`http://localhost:8080/api/note/user/${this.article.userId}`)
            .then(response => {
                this.user = response.data;
                console.log("답장유저 내용정보" + this.user);
            })
            .catch(error => {
                console.log(error);
            });
    },
  };
  </script>
  
  <style></style>
  
  
  <style scoped>
tr{
  font-family: 'Nixgon, sans-serif';
  opacity: 80%;
  font-size: 17px;
  font-weight: 600;
}
</style>
  
  
  
  
  
  
  
  
  
  