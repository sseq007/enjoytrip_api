<template>
  <div class="container">
    <div>
        <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 50px" class="m-0 mt-5">공지 사항</h2>
        <hr class="mb-3 mt-0" align="left" style="border: solid 3px brown; width: 50%" />
    </div>

    <!-- 글 내용 -->
    <div style="margin-top: 5vmin; justify-content: center; align-items: center; display: flex;">
      <div style="width: 80%; height: 80vmin; padding: 3%; border: 4px solid #ffb5a7; border-radius: 10px; background-color: #ffe4e0;">
          <h3 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 38px">📢 상세 공지 보기</h3>
          <hr class="mb-3 mt-0" align="left" style="border: solid 2px #ffb5a7; opacity: 80%; width: 30%; opacity: 100%" />
          <div>
              <div>
                <h3 style="font-family: 'Nixgon, sans-serif; font-weight:bold; font-size: 40px;">{{article.subject}}</h3>
                  <p style="font-family: 'Nixgon, sans-serif; opacity: 70%; font-weight:600; font-size: 22px;">{{user.userNickname}}</p>
                  <p style="font-family: 'Nixgon, sans-serif; opacity: 70%; font-weight:600; font-size: 15px;">🕛{{article.registerTime}} · 👁️‍🗨️{{article.hit}}</p>
              </div>
          </div>
          <hr class="mb-2 mt-2" align="left" style="border: solid 2px #ffb5a7; width: 100%; opacity: 70%"/>
          <div style="margin: 3vmin; height: 30vmin;
          font-family: 'Nixgon, sans-serif; opacity: 70%; font-weight:600; font-size: 15px;">
          {{article.content}}
          </div>
    
          <!-- button -->
          <div style="justify-content: center; margin-top: 30px; display: flex;">
            <div v-if="userInfo" style="display:flex;">
              <button v-if="userInfo.userId == this.article.userId" type="button" style="margin-right: 30px; height: 55px; width: 145px; background-color: transparent; border-radius: 20px; 
              opacity: 100%; border:4px solid #ffb5a7;" @click="moveModify">
                <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px;" >수정</span>
              </button>
              <button v-if="userInfo.userId == this.article.userId" type="button" style="margin-right: 30px; height: 55px; width: 145px; background-color: transparent; border-radius: 20px; 
              opacity: 100%; border:4px solid #ffb5a7;" @click="deleteArticle">
                <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px;" >삭제</span>
              </button>
              </div>
                <button type="button" style=" height: 55px; width: 145px; background-color: transparent; border-radius: 20px; 
                opacity: 100%; border:4px solid #ffb5a7;" @click="moveList">
                  <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px;" >목록</span>
                </button>
            </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { getUser } from "@/api/member";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "NoticeView",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      article:[],
      user: []
    };
  },
  created() {
    // 비동기
    // TODO : 글번호에 해당하는 글정보 얻기.
    axios.get(`http://localhost:8080/api/sharetravel/view/${this.$route.params.articleNo}`)
    .then(response => {
      console.log(response.data);
      this.article = response.data;
      getUser(
        this.article.userId,
        ({ data }) => {
          if(data.message === "success"){
            this.user = data.userInfo;
          }
        },
        (error) => {
          console.log(error);
        }
      );
    })
    .catch(error => {
      console.log(error);
    });
    
  },
  methods: {
    deleteArticle(){
      axios.delete(`http://localhost:8080/api/sharetravel/delete/${this.$route.params.articleNo}`)
    .then(response => {
        console.log(response);
        alert('삭제되었습니다.');
        this.$router.push('/notice');
  

    })
    .catch(error => {
      console.log(error);
    });
    },
    moveList(){
      this.$router.push({name: "noticeList"});
    },
    moveModify(){
      this.$router.push('/notice/modify/' + this.article.articleNo);
    },
  }

  
};
</script>

