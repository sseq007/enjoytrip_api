
<template>
    <!-- header 부분  필요-->
    <div class="container">
        <div>
            <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 50px" class="m-0 mt-5">여행 계획</h2>
            <hr class="mb-3 mt-0" align="left" style="border: solid 3px brown; width: 50%" />
        </div>
        <div>
            <p style="font-family: 'Noto Sans KR', sans-serif; opacity: 75%; margin-bottom: 5vh;">여행 계획을 세워보세요!</p>
            <div class="col-lg-8 col-md-12 col-sm-12 align-self-end">
                <div class="row">
                    <div class="col-md-7">
                    </div>
                </div>
            </div>
            <table class="table">
                <thead>
                    <tr class="text-center">
                        <th>지역</th>
                        <th>작성자</th>
                        <th>조회수</th>
                        <th>작성일</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- <notice-list-item v-for="article in articles" :key="article.articleNo" :article="article"> </notice-list-item> -->
                    <plan-list-item v-for="article in articles" :key="article.articleNo" :article="article"></plan-list-item>
                </tbody>
            </table>
        </div>
        <button type="button" style="float: right" v-if="userInfo != null">
            <!-- <a href="/notice/write" style="background-color: transparent; color: #000; text-decoration-line: none">글쓰기</a> -->
            <router-link :to="'/travelplan/write'"
            style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px; text-decoration-line: none">글쓰기</router-link>
        </button>
    </div>
</template>


<script>
import axios from "axios";
import PlanListItem from './travelplanListItem.vue';
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
    name: "TravelPlanList",
    components: {
        PlanListItem
    },
    data() {
        return {
            articles: []
        };
    },
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    created() {
        axios.get('http://localhost:8080/api/travelplan/list')
    .then(response => {
      console.log(response.data);
      this.articles = response.data;
    })
    .catch(error => {
      console.log(error);
    });
    },
    methods: {
        movePage() {

            // location.href="http://localhost:8080/boardwrite"

        },
    },
};
</script>

<style scoped>
table{
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.3);
  border: solid 4px #ffb5a7;
  border-collapse: separate;
  border-radius: 10px;
  background-color: #ffe4e0;
}
button{
  height: 55px;
  width: 145px;
  background-color: transparent;
  border-radius: 20px; 
  border:4px solid #ffb5a7;
}
</style>