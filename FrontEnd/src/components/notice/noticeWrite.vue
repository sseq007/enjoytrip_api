
<template>
    <!-- header 부분  필요-->
  <div class="container">
    <div>
        <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 50px" class="m-0 mt-5">공지 사항</h2>
        <hr class="mb-3 mt-0" align="left" style="border: solid 3px brown; width: 50%" />
    </div>
    <div style="margin-top: 5vmin; justify-content: center; align-items: center; display: flex;">
      <div style="width: 80%; padding: 3%; border: 4px solid #ffb5a7; border-radius: 10px; background-color: #ffe4e0;">
        <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 20px; text-align: left" class="m-0 mt-4">공지 사항 작성</h2>
        <hr class="mb-3 mt-0" align="left" style="border: solid 2px #ffb5a7; width: 30%; opacity: 100%" />
        <div style="margin-top: 2vmin;">
            <label >* 제목: </label>
            <input type="text" placeholder="Subject" name="board-write-title" ref="subject" />
        </div>
        <div style="margin-top: 10vmin;">
          <label>* 내용:</label>
          <textarea rows="5" id="board-write-content" name="board-write-content" placeholder="Content" ref="content"></textarea>
        </div>

        <div style="text-align: center; margin-top: 25vmin;">
          <button type="button" style="margin-right: 30px; height: 55px; width: 145px; background-color: transparent; border-radius: 20px; 
          border:4px solid #ffb5a7;" id="btn-write" @click="checkValue">
            <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px;">등록</span>
          </button>
          <button type="button" style=" height: 55px; width: 145px; background-color: transparent; border-radius: 20px; 
          border:4px solid #ffb5a7;">
            <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px;" @click="goList">취소</span>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import axios from "axios";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "NoticeWrite",
  components:{
  },
  data() {
    return {
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    checkValue() {
      let err = true;
      let msg = "";

      err && !this.$refs.subject.value && ((msg = "제목을 입력해 주세요."), (err = false), this.$refs.subject.focus());
      err && !this.$refs.content.value && ((msg = "내용을 입력해 주세요."), (err = false), this.$refs.content.focus());
      
      if (!err) alert(msg);
      else this.registArticle();
    },
    registArticle() {
      var formData = {
        userId : this.userInfo.userId,
        subject : this.$refs.subject.value,
        content: this.$refs.content.value
      }
      
      console.log(formData)
      axios.post('http://localhost:8080/api/sharetravel/write', formData, {
          headers: {
              'Content-Type': 'application/json'
          }
      })
      .then(response => {
          console.log('등록 결과:', response.data);
          alert('글 등록이 완료되었습니다.');
          this.$router.push({ name: "notice" });
      })
      .catch(error => {
          console.log(error);
          alert('글 등록에 실패하였습니다.');
      });
    },
    goList(){
      this.$router.push({ name: "notice" });
    }
  },
};
</script>

<style scoped>
  input{
    margin-top: 4px; 
    font-family: 'Nixgon, sans-serif';
    font-weight:600; 
    opacity: 80%; 
    border-radius: 5px;
    width:100%;
    height:45px;
    padding-left: 15px;
    background-color: transparent;
    border:4px solid #ffb5a7;
    float:left;
    font-size: 20px;
  }
  textarea{
    margin-top: 4px; 
    font-family: 'Nixgon, sans-serif';
    font-weight:600; 
    opacity: 80%; 
    border-radius: 5px;
    width:100%;
    height:180px;
    padding-left: 15px;
    background-color: transparent;
    border:4px solid #ffb5a7;
    float:left;
    font-size: 20px;
  }
  label{
    font-family: 'Nixgon, sans-serif'; opacity: 60%; font-size: 17px; font-weight: 600;
  }
  input:focus {outline: 2px solid #85c6d7;}
  textarea:focus {outline: 2px solid #85c6d7;}
</style>