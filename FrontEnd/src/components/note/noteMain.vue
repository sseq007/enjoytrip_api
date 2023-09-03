
<template>
    <!-- header 부분  필요-->
    <div class="container">
    <div>
        <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 50px" class="m-0 mt-5">공지 사항</h2>
        <hr class="mb-3 mt-0" align="left" style="border: solid 3px brown; width: 50%" />
    </div>
    <div>
        <p style="font-family: 'Noto Sans KR', sans-serif; opacity: 75%">직접 다녀왔던 여행지 중 좋았던 곳에 대한 정보를 공유해 주세요!</p>
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
    <button type="button" class="btn btn-outline-secondary" style="float: right" v-if="userInfo.admin==1">
        <!-- <a href="/notice/write" style="background-color: transparent; color: #000; text-decoration-line: none">글쓰기</a> -->
        <router-link :to="'/notice/write'" style="background-color: transparent; color: #000; text-decoration-line: none" >글쓰기</router-link>
    </button>
</div>
</template>


<script>
// import axios from "axios";
// import NoticeListItem from './noticeListItem.vue';
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "NodeList",
  components:{
    // NoticeListItem
  },
  data() {
    return {
      articles :[]
    };
  },
  created() {
    // 비동기
    // TODO : 글목록 얻기.

//   axios.get('http://localhost:8080/api/sharetravel/list')
//     .then(response => {
//       console.log(response.data);
//       this.articles = response.data;
//     })
//     .catch(error => {
//       console.log(error);
//     });
  },
  computed: {

        ...mapState(memberStore, ["userInfo"]),
    },
  methods: {
    movePage() {

      // location.href="http://localhost:8080/boardwrite"

    },
  },
};
</script>

