<template>
    <div class="container">
        <div style="background-image:url('../assets/img/registerDiv.png');
        background-repeat: no-repeat;
        background-size: contain; position : relative;
        height: 1700px; margin-top: 70px; margin-left: 10%;  display: flex; justify-content: center;">
            <img :src="image" class="profile_image" width="310px" height="310px" style="display: block; margin-right: 103px; margin-top: 16px; border-radius: 50%; position: absolute;">
            <div style="flex-direction: column;">
                <div style="margin-right: 200px; margin-top: 120px; padding-top: 1px; display: flex; height: 200px;">
                    <h2 style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 100px; font-weight: 600; " class="m-0 mt-5">SIGN</h2>
                    <div style="vertical-align: middle;">
                        <label className="input-file-button" for="updateProfile" style="margin-left: 280px; margin-right: 30px; margin-top: 110px; height: 80px; width: 80px; background-color: #FFE4E0; border-radius: 40%; opacity: 60%;
                        border:4px solid #85c6d7; float: right; text-align: center; padding-top: 2px;
                        font-family: 'Nixgon, sans-serif; color: #85c6d7; font-weight:600; font-size: 30px;">
                            <i class="fi fi-rr-camera" style="font-size: 50px; color: #85c6d7;"></i>
                        </label>
                        <input ref="image" type="file" id="updateProfile" name="file" accept=".jpg, .png, .jpeg" value="" style="display: none" @change="imageChange()"/>
                    </div>
                    <h2 style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 100px; font-weight: 600;" class="m-0 mt-5">UP</h2>
                </div>
                <div style="display: flex; flex-direction: column; margin-left: 3%; margin-top: 50px; height: 95px;">
                    <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 20px; font-weight: 600;">
                        * 아이디
                    </label>
                    <div style="display: flex; margin-top: 8px;">
                        <input type="text" v-model="userId" style=" font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 60%; 
                        width:760px; height:55px; padding-left: 15px; background-color: transparent;
                        border:4px solid #85c6d7; float:left; font-size: 28px" placeholder="ID" @input="checkId"
                        required/>
                    </div>
                    <label v-if="idInput" style="font-family: 'Nixgon, sans-serif; opacity: 70%; font-size: 15px; font-weight: 600;">
                        {{this.idMessage}}
                    </label>
                </div>
                <div style="display: flex; flex-direction: column; margin-left: 3%; margin-top: 15px; height: 95px;">
                    <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 20px; font-weight: 600;">
                        * 비밀번호
                    </label>
                    <input type="password" v-model="userPw" style="margin-top: 8px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 60%; 
                    width:760px; height:55px; padding-left: 15px; background-color: transparent;
                    border:4px solid #85c6d7; float:left; font-size: 28px" placeholder="Password"
                    required/>
                </div>
                <div style="display: flex; flex-direction: column; margin-left: 3%; margin-top: 15px; height: 95px;">
                    <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 20px; font-weight: 600;">
                        * 비밀번호 재확인
                    </label>
                    <input type="password" v-model="pwCheck" style="margin-top: 8px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 60%; 
                    width:760px; height:55px; padding-left: 15px; background-color: transparent;
                    border:4px solid #85c6d7; float:left; font-size: 28px" placeholder="Password Check"
                    required/>
                </div>
                <div style="display: flex; flex-direction: column; margin-left: 3%; margin-top: 15px; height: 95px;">
                    <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 20px; font-weight: 600;">
                        * 이름
                    </label>
                    <input type="text" v-model="userName" style="margin-top: 10px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 60%; 
                    width:760px; height:55px; padding-left: 15px; background-color: transparent;
                    border:4px solid #85c6d7; float:left; font-size: 28px" placeholder="Name"
                    required/>
                </div>
                <div style="display: flex; flex-direction: column; margin-left: 3%; margin-top: 15px; height: 95px;">
                    <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 20px; font-weight: 600;">
                        * 닉네임
                    </label>
                    <input type="text" v-model="userNickname" style="margin-top: 8px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 60%; 
                    width:760px; height:55px; padding-left: 15px; background-color: transparent;
                    border:4px solid #85c6d7; float:left; font-size: 28px" placeholder="Nickname" @input="checkNickname"
                    required/>
                    <label v-if="nickInput" style="font-family: 'Nixgon, sans-serif; opacity: 70%; font-size: 15px; font-weight: 600;">
                        {{this.nickMessage}}
                    </label>
                </div>
                <div style="display: flex; flex-direction: column; margin-left: 3%; margin-top: 15px; height: 95px;">
                    <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 20px; font-weight: 600;">
                        * 성별
                    </label>
                    <select ref="gender" style="margin-top: 8px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 60%; 
                    width:760px; height:55px; padding-left: 15px; background-color: transparent; appearance: none;
                    border:4px solid #85c6d7; float:left; font-size: 28px; outline: 0 none; -moz-appearance: none;
                    -webkit-appearance: none;"
                    required>
                        <option disabled selected style="display: none;">성별 선택</option>
                        <option value="남자" style="background-color: #FFE4E0;
                        font-family: 'Nixgon, sans-serif; font-weight:600; color: #939393;">남자</option>
                        <option value="여자" style="background-color: #FFE4E0;
                        font-family: 'Nixgon, sans-serif; font-weight:600; color: #939393;">여자</option>
                    </select>
                </div>
                <div style="display: flex; flex-direction: column; margin-left: 3%; margin-top: 15px; height: 95px;">
                    <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 20px; font-weight: 600;">
                        * 생년월일
                    </label>
                    <input type="date" v-model="birth" style="margin-top: 8px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 60%; 
                    width:760px; height:55px; padding-left: 15px; padding-right: 15px; background-color: transparent;
                    border:4px solid #85c6d7; float:left; font-size: 28px" placeholder="Nickname"
                    required/>
                </div>
                <div style="display: flex; flex-direction: column; margin-left: 3%; margin-top: 15px; height: 95px;">
                    <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 20px; font-weight: 600;">
                        * 이메일
                    </label>
                    <div style="display: flex; margin-top: 8px;">
                        <input type="text" v-model="emailId" style="font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 60%; 
                        width:250px; height:55px; padding-left: 15px; background-color: transparent;
                        border:4px solid #85c6d7; float:left; font-size: 28px" placeholder="example"
                        required/>
                        <div style="width:55px; height:55px; opacity: 60%; background-color: #85c6d7;">
                            <label style="font-family: 'Nixgon, sans-serif; font-weight:600; font-size: 35px; color: #FFE4E0; padding-left: 10px; padding-bottom: 10px;">@</label>
                        </div>
                        <select ref="emailDomain" style="font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 60%; 
                        width:300px; height:55px; padding-left: 15px; background-color: transparent; appearance: none;
                        border:4px solid #85c6d7; float:left; font-size: 28px; outline: 0 none; -moz-appearance: none;
                        -webkit-appearance: none;"
                        required>
                            <option disabled selected style="display: none;">도메인 선택</option>
							<option value="naver.com">naver.com</option>
							<option value="gmail.com">gmail.com</option>
							<option value="daum.net">daum.net</option>
                        </select>
                        <button class="button" style="margin-left: 10px; height: 55px; width: 145px; background-color: transparent; border-radius: 20px; 
                        opacity: 60%; border:4px solid #85c6d7;">
                            <span style="font-family: 'Nixgon, sans-serif; color: #85c6d7; font-weight:600; font-size: 30px;">인증</span>
                        </button>
                    </div>
                </div>
                <div style="display: flex; position: absolute; margin-top: 20px; left: 570px; bottom: 300px; margin-right: 18%; float: right;">
                    <button class="button" style="height: 60px; width: 320px; background-color: transparent; border-radius: 20px; 
                    opacity: 60%; border:4px solid #85c6d7;" @click="checkValue">
                        <span style="font-family: 'Nixgon, sans-serif; color: #85c6d7; font-weight:600; font-size: 30px;">가입하기</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { registerMember, idCheck, nickCheck } from "@/api/member";

export default {
    name: "userRegister",
    components:{

    },
    data(){
        return{
            userId: '',
            userPw: '',
            pwCheck: '',
            userName: '',
            userNickname: '',
            birth: '',
            emailId: '',
            image: '',
            idMessage: '',
            idInput: false,
            nickMessage: '',
            nickInput: false,
        }
    },
    created(){
        this.image = require('@/assets/img/noimg.jpg');
    },
    methods:{
        imageChange(){
            var image = this.$refs['image'].files[0];
            const url = URL.createObjectURL(image);
            this.image = url;
            console.log(url);          
        },
        checkValue() {
			let err = true;
			let msg = "";

			if(err && !this.userId){
                msg = "ID를 입력해 주세요";
                err = false;
            }else if(err && this.idMessage != "사용 가능한 ID입니다."){
                msg = "다른 ID를 입력해 주세요";
                err = false;
            }else if(err && !this.userPw){
                msg = "비밀번호를 입력해 주세요";
                err = false;
            }else if (err && this.userPw != this.pwCheck) {
				err = false;
				msg = "비밀번호를 다시 확인해 주세요";
			}else if(err && !this.userName){
                msg = "이름을 입력해 주세요";
                err = false;
            }else if(err && !this.userNickname){
                msg = "닉네임을 입력해 주세요";
                err = false;
            }else if(err && this.nickMessage != "사용 가능한 닉네임입니다."){
                msg = "다른 닉네임을 입력해 주세요";
                err = false;
            }else if(err && !this.$refs.gender.value){
                msg = "성별을 선택해 주세요";
                err = false;
            }else if(err && !this.birth){
                msg = "생년월일을 선택해 주세요";
                err = false;
            }else if(err && (!this.emailId || !this.$refs.emailDomain.value)){
                msg = "이메일을 입력해 주세요";
                err = false;
            }
            

			
			if (!err) alert(msg);
			else this.register();
		},
        async register() {
			const formdata = new FormData();
            formdata.append('userId', this.userId);
            formdata.append('userName', this.userName);
            formdata.append('userPw', this.userPw);
            formdata.append('userNickname', this.userNickname);
            formdata.append('gender', this.$refs.gender.value);
            formdata.append('birth', this.birth);
            formdata.append('emailId', this.emailId);
            formdata.append('emailDomain', this.$refs.emailDomain.value);
            formdata.append('file', this.$refs.image.files[0]);

            for (let key of formdata.keys()) {
                console.log(key, ":", formdata.get(key));
            }

			registerMember(
				formdata,
				({ data }) => {
					let msg = "멤버 등록을 실패했습니다.";
					if (data.message === "success") {
						msg = "등록이 완료되었습니다.";
					}
					alert(msg);

					this.$router.push({ name: "home"});
				},
				(error) => {
					console.log(error);
				}
			);
		},
        checkId(){
            if(!this.idInput) this.idInput = !this.idInput;
            if(this.userId == '') this.idInput = false;
            idCheck(
                this.userId,
                ({data}) => {
                    this.idMessage = "사용 불가능한 ID입니다."
                    if(data === 0 ){
                        this.idMessage = "사용 가능한 ID입니다."
                    }
                },
                (error) => {
                    console.log(error);
                }
            )
        },
        checkNickname(){
            if(!this.nickInput) this.nickInput = !this.nickInput;
            if(this.userNickname == '') this.nickInput = false;
            nickCheck(
                this.userNickname,
                ({data}) => {
                    this.nickMessage = "사용 불가능한 닉네임입니다."
                    if(data === 0 ){
                        this.nickMessage = "사용 가능한 닉네임입니다."
                    }
                },
                (error) => {
                    console.log(error);
                }
            )
        }
    }
}
</script>

<style scoped>
input:focus {outline: 2px solid #ffb5a7;}
textarea:focus {outline: 2px solid #ffb5a7;}
select:focus {outline: 2px solid #ffb5a7;}
.select option{
    background-color: #FFE4E0;
    font-family: 'Nixgon, sans-serif';
    font-weight:600;
    color: #939393;
}
</style>