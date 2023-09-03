
<template>
    <!-- header 부분  필요-->
    <div class="container">
    <div>
        <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 50px" class="m-0 mt-5">공지 사항</h2>
        <hr class="mb-3 mt-0" align="left" style="border: solid 3px brown; width: 50%" />
    </div>
    <div>
        <p style="font-family: 'Noto Sans KR', sans-serif; opacity: 75%">공지사항입니다!</p>
        <div class="col-lg-8 col-md-12 col-sm-12 align-self-end">
          <div class="row">
			<div class="col-md-7">
            </div>
          </div>
        </div>
        <table class="table">
            <thead>
                <tr class="text-center">
                    <th>글번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>작성일</th>
                </tr>
            </thead>
            <tbody>
            <notice-list-item v-for="article in articles" :key="article.articleNo" :article="article"> </notice-list-item>
            </tbody>
        </table>
    </div>
    <div v-if="userInfo">
      <button v-if="userInfo.admin == 1" type="button" style="float: right">
          <router-link :to="'/notice/write'" style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px; text-decoration-line: none">글쓰기</router-link>
      </button>
    </div>
</div>
</template>


<script>
import axios from "axios";
import { mapState } from "vuex";
import NoticeListItem from './noticeListItem.vue';

const memberStore = "memberStore";
export default {
  name: "NoticeList",
  components:{
    NoticeListItem
  },
  data() {
    return {
      articles :[]
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    // 비동기
    // TODO : 글목록 얻기.

  axios.get('http://localhost:8080/api/sharetravel/list')
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

