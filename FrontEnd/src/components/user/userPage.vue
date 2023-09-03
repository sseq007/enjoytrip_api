<template>
<!-- [S] content -->
    <div class="container">
        <div>
            <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 50px" class="m-0 mt-5">마이 페이지</h2>
            <hr class="mb-3 mt-0" align="left" style="border: solid 3px brown; width: 50%" />
        </div>
        <div style="margin: auto; margin-top: 100px; margin-bottom: 100px; width: 60%; height: auto; border: 3px solid #ffb5a7; background-color: #ffe4e0; border-radius: 10px" class="text-center">
            <nav class="navbar navbar-expand-sm" style="background-color: #ffb5a7; font-family: 'Black Han Sans', sans-serif; opacity: 75%; text-align: center; font-size: 20px">
                <div class="collpase navbar-collapse justify-content-center">
                    <ul class="navbar-nav" style="justify-content: space-between;">
                        <li class="nav-item" style="margin-inline: 70px;">
                            <router-link :to="'/user/userPage'" class="nav-link active">회원 정보 수정</router-link>
                        </li>
                        <li class="nav-item" style="margin-inline: 70px">
                            <router-link :to="'/user/userDelete'" class="nav-link active">회원 탈퇴</router-link>
                        </li>
                    </ul>
                </div>
            </nav>

            <div id="modifyContents">
                <form action="" style="text-align: left" id="form-update" method="POST" encType="multipart/form-data">
                    <div class="m-5">
                        <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 20px; text-align: left" class="m-0 mt-4">회원 정보 수정</h2>
                        <hr class="mb-3 mt-0" align="left" style="border: solid 2px #ffb5a7; width: 30%; opacity: 100%" />
                        <div class="mb-3 mt-3" align="center" style="position: relative">
                                <div class="userProfile mb-3 mt-3">
                                    <img :src="image" class="profile_image" style="border-radius: 40%; width: 250px; height: 250px">
                                </div>
                                <div>
                                    <label className="input-file-button" for="updateProfile" style="left: 400px; bottom: 0px; height: 60px; width: 60px; background-color: #FFE4E0; border-radius: 40%;
                                    border:3.5px solid #ffb5a7; text-align: center; padding-top: 2px; position: absolute;
                                    font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 30px;">
                                        <i class="fi fi-rr-camera" style="font-size: 35px; color: #ffb5a7;"></i>
                                    </label>
                                    <input ref="image" type="file" id="updateProfile" name="file" accept=".jpg, .png, .jpeg" value="" style="display: none" @change="imageChange()"/>
                                </div>                                
                        </div>
                        <div style="display: flex; flex-direction: column; margin-top: 50px; height: 80px;">
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 17px; font-weight: 600;">
                                * 아이디
                            </label>
                            <input type="text" style="margin-top: 4px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 80%; border-radius: 5px;
                            width:100%; height:45px; padding-left: 15px; background-color: transparent;
                            border:4px solid #ffb5a7; float:left; font-size: 20px" :value="userInfo.userId" disabled="true"
                            />
                        </div>
                        <div style="display: flex; flex-direction: column; margin-top: 15px; height: 80px;">
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 17px; font-weight: 600;">
                                * 이름
                            </label>
                            <input type="text" style="margin-top: 4px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 80%; border-radius: 5px;
                            width:100%; height:45px; padding-left: 15px; background-color: transparent;
                            border:4px solid #ffb5a7; float:left; font-size: 20px" ref="userName" :value="userInfo.userName"
                            required/>
                        </div>
                        <div style="display: flex; flex-direction: column; margin-top: 15px; height: 80px;">
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 17px; font-weight: 600;">
                                * 닉네임
                            </label>
                            <div style="display: flex; margin-top: 4px;">
                                <input type="text" style=" font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 80%; border-radius: 5px;
                                width:65%; height:45px; padding-left: 15px; background-color: transparent;
                                border:4px solid #ffb5a7; float:left; font-size: 20px" ref="userNickname" :value="userInfo.userNickname"
                                required/>
                                <button class="button" style="margin-left: 10px; height: 45px; width: 35%; background-color: transparent; border-radius: 20px; 
                                    opacity: 80%; border:4px solid #ffb5a7;">
                                        <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px;">중복 확인</span>
                                </button>
                            </div>
                        </div>
                        <div style="display: flex; flex-direction: column; margin-top: 15px; height: 80px;">
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 17px; font-weight: 600;">
                                * 비밀번호
                            </label>
                            <input type="password" style="margin-top: 4px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 80%; border-radius: 5px;
                            width:100%; height:45px; padding-left: 15px; background-color: transparent;
                            border:4px solid #ffb5a7; float:left; font-size: 20px" ref="userPw" :value="userInfo.userPw"
                            required/>
                        </div>
                        <div style="display: flex; flex-direction: column; margin-top: 15px; height: 80px;">
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 17px; font-weight: 600;">
                                * 성별
                            </label>
                            <select ref="gender" style="margin-top: 4px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 80%; border-radius: 5px;
                            width:100%; height:45px; padding-left: 15px; background-color: transparent;
                            border:4px solid #ffb5a7; float:left; font-size: 20px; appearance: none; -moz-appearance: none;
                            -webkit-appearance: none;">
                                <option selected style="display: none;">{{ userInfo.gender }} </option>
                                <option value="남자" style="background-color: #FFE4E0;
                                font-family: 'Nixgon, sans-serif; font-weight:600; color: #939393;">남자</option>
                                <option value="여자" style="background-color: #FFE4E0;
                                font-family: 'Nixgon, sans-serif; font-weight:600; color: #939393;">여자</option>
                            </select>
                        </div>
                        <div style="display: flex; flex-direction: column; margin-top: 15px; height: 80px;">
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 17px; font-weight: 600;">
                                * 생년월일
                            </label>
                            <input type="date" style="margin-top: 4px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 80%; border-radius: 5px;
                            width:100%; height:45px; padding-left: 15px; padding-right: 15px; background-color: transparent;
                            border:4px solid #ffb5a7; float:left; font-size: 20px" ref="birth" :value="userInfo.birth"
                            required/>
                        </div>
                        <div style="display: flex; flex-direction: column; margin-top: 15px; height: 80px;">
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 17px; font-weight: 600;">
                                * 이메일
                            </label>
                            <div style="display: flex; margin-top: 8px;">
                                <input type="text" style="font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 80%; border-radius: 5px 0px 0px 5px;
                                width:250px; height:45px; padding-left: 15px; background-color: transparent;
                                border:4px solid #ffb5a7; float:left; font-size: 20px" ref="emailId" :value="userInfo.emailId"
                                required/>
                                <div style="width:45px; height:45px; opacity: 80%; background-color: #ffb5a7;">
                                    <label style="font-family: 'Nixgon, sans-serif; font-weight:600; font-size: 35px; color: #FFE4E0; padding-left: 5px; padding-bottom: 50px;">@</label>
                                </div>
                                <select ref="emailDomain" style="font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 80%; 
                                width:300px; height:45px; padding-left: 15px; background-color: transparent; appearance: none; border-radius: 0px 5px 5px 0px;
                                border:4px solid #ffb5a7; float:left; font-size: 20px; outline: 0 none; -moz-appearance: none;
                                -webkit-appearance: none;"
                                required>
                                    <option selected style="display: none;">{{ userInfo.emailDomain}}</option>
                                    <option value="naver.com" style="background-color: #FFE4E0;
                                    font-family: 'Nixgon, sans-serif; font-weight:600; color: #939393;">naver.com</option>
                                    <option value="gmail.com" style="background-color: #FFE4E0;
                                    font-family: 'Nixgon, sans-serif; font-weight:600; color: #939393;">gmail.com</option>
                                    <option value="daum.net" style="background-color: #FFE4E0;
                                    font-family: 'Nixgon, sans-serif; font-weight:600; color: #939393;">daum.net</option>
                                </select>
                                <button class="button" style="margin-left: 10px; height: 45px; width: 145px; background-color: transparent; border-radius: 20px; 
                                opacity: 80%; border:4px solid #ffb5a7;">
                                    <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px;">인증</span>
                                </button>
                            </div>
                        </div>
                        <div style="display: flex; flex-direction: column; margin-top: 15px; height: 80px;">
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 17px; font-weight: 600;">
                                * 등록일자
                            </label>
                            <input type="text" style="margin-top: 4px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 80%; border-radius: 5px;
                            width:100%; height:45px; padding-left: 15px; background-color: transparent;
                            border:4px solid #ffb5a7; float:left; font-size: 20px" :value="userInfo.joinDate" disabled="true"
                            />
                        </div>
                        <div style="text-align: center; margin-top: 30px;">
                            <button type="button" style="margin-right: 30px; height: 55px; width: 145px; background-color: transparent; border-radius: 20px; 
                            opacity: 80%; border:4px solid #ffb5a7;" id="btn-modify" @click="checkValue">
                                <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px;">수정</span>
                            </button>
                            <button type="button" style=" height: 55px; width: 145px; background-color: transparent; border-radius: 20px; 
                            opacity: 80%; border:4px solid #ffb5a7;">
                                <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px;">취소</span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>


<script>
import { mapState, mapActions } from "vuex";
import { updateMember } from "@/api/member";
// import router from '@/router';
// import axios from "axios";

const memberStore = "memberStore";
export default{
    name: "userPage",
    components:{
    },
    data(){
        return{
            image : '',
        }
    },
    created(){
        if(this.userInfo.profileImage == null){
            this.image = require('@/assets/img/noimg.jpg');
        }else{
            this.image = "http://localhost:8081/upload/" + this.userInfo.profileImage;
        }
    },
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    methods: {
        ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
        checkValue(){
            let err = true;
            let msg = "";

            err && !this.$refs.userPw.value && ((msg = "비밀번호를 입력해 주세요"), (err = false), this.$refs.userPw.focus());
            err && !this.$refs.userName.value && ((msg = "이름을 입력해 주세요"), (err = false), this.$refs.userName.focus());
            if (!err) alert(msg);
            // 만약, 내용이 다 입력되어 있다면 update 호출
            else this.update(); 
        },
        imageChange(){
            // console.log(this.$refs['image'].files[0]);
            // console.log(this.$refs.image.files[0]);
            var image = this.$refs['image'].files[0];
            const url = URL.createObjectURL(image);
            this.image = url;
            console.log(url);           
        },
        async update(){
            // 비동기
            const formdata = new FormData();
            formdata.append('userId', this.userInfo.userId);
            formdata.append('userName', this.$refs.userName.value);
            formdata.append('userPw', this.$refs.userPw.value);
            formdata.append('userNickname', this.$refs.userNickname.value);
            formdata.append('gender', this.$refs.gender.value);
            formdata.append('birth', this.$refs.birth.value);
            formdata.append('emailId', this.$refs.emailId.value);
            formdata.append('emailDomain', this.$refs.emailDomain.value);
            formdata.append('file', this.$refs.image.files[0]);

            for (let key of formdata.keys()) {
                console.log(key, ":", formdata.get(key));
            }

            updateMember(
                formdata,
                ({ data }) => {
                    let msg = "멤버 수정 시 문제가 발생했습니다.";
                    if(data.message === "success"){
                        msg = "수정이 완료되었습니다.";
                    }
                    alert(msg);
                    
                    this.moveMain();
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        async moveMain(){
            let token = sessionStorage.getItem("access-token");
            await this.getUserInfo(token);
            this.$router.push({ name: "home" });
        },
    },
};
</script>

<style scoped>
input:focus {outline: 2px solid #85c6d7;}
select:focus {outline: 2px solid #85c6d7;}
</style>
