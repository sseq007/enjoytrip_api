<template>
    <div class="container">
        <div style="margin-bottom: 5vh;">

            <h2 style="font-family:'Black Han Sans',sans-serif; opacity: 75%; font-size: 50px" class="m-0 mt-5">쪽지 보기</h2>
            <hr class="mb-3 mt-0" align="left" style="border: solid 3px brown; width: 50%" />
        </div>
        <div class="row mb-5">
            <div class="d-flex" style="justify-content:space-between ;">

                <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; margin-bottom: 3vh;">쪽지 정보</h2>
                <div>
                    <button class="btn-go" variant="outline-secondary" @click="showModal" style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px; text-decoration-line: none">답장</button>
                    <button class="btn-go" variant="outline-secondary" style="margin-left: 1vh; margin-right: 3vh; font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px; text-decoration-line: none" @click="deleteNote">삭제</button>
                </div>
            </div>
            <div class="d-flex mb-4">

                <img src="@/assets/img/user.png" style="width: 3vh; height: 3vh;">
                <div style="margin-left: 2vh; font-size: 2vh;">{{ note.fromuserId }}</div>
            </div>
            <div class="d-flex mb-4">

                <img src="@/assets/img/clock.png" style="width: 3vh; height: 3vh;">
                <div style="margin-left: 2vh; font-size: 2vh;">{{ note.registerTime.substring(0, 10) }} {{
                    note.registerTime.substring(11, 16) }}</div>
            </div>
            <div class="d-flex mb-4">

                <img src="@/assets/img/edit.png" style="width: 3vh; height: 3vh;">
                <div style="margin-left: 2vh; font-size: 2vh;">{{ note.content }}</div>
            </div>


        </div>
        <b-modal id="modal-scrollable" size="lg" scrollable title="" v-model="modalVisible">
                <template #modal-header>
                    <!-- Emulate built in modal header close button action -->
                    <div class="container">
                        <!-- <button type="button" class="btn-close" data-bs-dismiss="modal" style="float: right;"></button> -->
                        <div class="row">
                            <div class="d-flex justify-content-center">
                                <h4 class="modal-title" style="text-align: center;">
                                    <i class="fi fi-rr-envelope"> 쪽지를 보내봐요</i>
                                </h4>
                            </div>
                        </div>
                    </div>
                </template>
                <div class="container" style="height:62vh; padding: 4vh;">

                    <div class="row mb-5">

                        <h2 style="font-family: 'Black Han Sans',sans-serif; opacity: 75%; margin-bottom: 3vh;">받는 사람 정보</h2>
                        <div class="d-flex mb-3">
                            <img src="@/assets/img/user.png" style="width: 3vh; height: 3vh;">
                    <div style="margin-left: 2vh; font-size: 2vh;">{{ user.userNickname }}</div>
                           
                        </div>
                        <div class="d-flex mb-3">
                            <img src="@/assets/img/age.png" style="width: 3vh; height: 3vh;">
                    <div style="margin-left: 2vh; font-size: 2vh;">{{ user.age }}세</div>
                           
                        </div>
                        <div class="d-flex mb-3">
                            <img src="@/assets/img/email.png" style="width: 3vh; height: 3vh;">
                    <div style="margin-left: 2vh; font-size: 2vh;">{{ user.emailId }}@{{ user.emailDomain }}</div>
                            
                        </div>
                        <div class="d-flex mb-3">

                           
                        </div>

                    </div>

                    <div class="row">
                        <b-form-textarea id="textarea" v-model="text" placeholder="답장을 쪽지로 보내보세요.." rows="9"
                            ></b-form-textarea>
                    </div>
                </div>
                <template #modal-footer="{ hide }">
                <button type="button" @click="hide"
                    style="width: calc(50% - 15px); background-color: #d3d3d3; height: 60px; " id="btn-login"
                    class="btn">취소</button>
                <button type="button" style="width: calc(50% - 15px); height: 60px; background-color: #79CF9F;" class="btn "
                 @click="registNote"  >보내기</button>


            </template>
            </b-modal>

    </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
    name: "NoteView",
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    data() {
        return {
            note: Object,
            modalVisible: false,
            text: '',
            user:[]
        };
    },
    created() {
        axios.get(`http://localhost:8080/api/note/view/${this.$route.params.noteNo}`)
            .then(response => {
                this.note = response.data;
                console.log("쪽지보기 내용정보" + this.note.fromuserId);
                axios.get(`http://localhost:8080/api/note/user/${this.note.fromuserId}`)
            .then(response => {
                this.user = response.data;
                console.log("답장유저 내용정보" + this.user);
            })
            .catch(error => {
                console.log(error);
            });
            })
            .catch(error => {
                console.log(error);
            });
            
            

    },
    methods: {
         showModal() {
            
            if (this.userInfo != null) {
                    this.modalVisible = true;
            }
            else {
                this.modalVisible = false; // 모달 표시 여부 변경
                alert("로그인을 해주세요");
            }
        },
        deleteNote() {
            if (confirm("정말로 삭제하시겠습니까?")) {
                axios.delete(`http://localhost:8080/api/note/delete/${this.$route.params.noteNo}`)
                    .then(response => {
                        console.log(response);
                        alert('삭제되었습니다.');
                        location.href="/main"
    
    
                    })
                    .catch(error => {
                        console.log(error);
                    });
                
            }

        },
        registNote(){
            var formData = {
                articleNo: this.note.articleNo,
                fromuserId: this.userInfo.userId,
                touserId: this.note.fromuserId,
                content: this.text
            }

            console.log(formData)
            axios.post('http://localhost:8080/api/note/write', formData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    console.log('쪽지 결과:', response.data);
                    alert('쪽지가 전송되었습니다.');
                    this.modalVisible = false;
                })
                .catch(error => {
                    console.log(error);
                    alert('쪽지 전송이 실패하였습니다.');
                });
        

        }
    }


};
</script>

<style scoped>
.btn-go{
  height: 55px;
  width: 145px;
  background-color: transparent;
  border-radius: 20px; 
  border:4px solid #ffb5a7;
}
</style>