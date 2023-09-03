<template>
    <div style="background-image:url('../assets/img/loginDiv.png');
    background-repeat: no-repeat;
    background-size: contain;
    height: 700px; margin-top: 100px; margin-left: 250px;">
        <div style="margin-left: 5%; padding-top: 1px">
            <h2 style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 100px; font-weight: 600; " class="m-0 mt-5">LOGIN</h2>
            <hr class="mb-3 mt-0" align="left" style="border: solid 5px #85c6d7; width: 20%" />
        </div>
        <div style="display: flex; margin-top: 50px;">
            <div style="margin-left: 5%; width:70px; height:70px; opacity: 60%; background-color: #85c6d7; float:left; text-align: center; align-items: center;">
                <i class="fi fi-rr-world" style="font-size: 50px; color: #FFE4E0;"></i>
            </div>
             <input type="text" style="font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 60%; 
             width:350px; height:70px; padding-left: 15px; background-color: transparent; 
             border:4px solid #85c6d7; float:left; font-size: 30px" placeholder="ID"
             v-model="user.userId" required/>
        </div>
        <div style="display: flex; margin-top: 15px;">
            <div style="margin-left: 5%; width:70px; height:70px; opacity: 60%; background-color: #85c6d7; float:left; text-align: center; align-items: center;">
                <i class="fi fi-rr-key" style="font-size: 50px; color: #FFE4E0;"></i>
            </div>
            <input type="password" style="font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 60%; 
            width:350px; height:70px; padding-left: 15px; background-color: transparent;
            border:4px solid #85c6d7; float:left; font-size: 30px" placeholder="Password"
            v-model="user.userPw" required/>
        </div>
        <div style="display: flex; margin-top: 40px; margin-left: 5%">
            <button class="button" style="height: 60px; width: 420px; background-color: transparent; border-radius: 20px;
            opacity: 60%; border:4px solid #85c6d7;" @click="confirm"
            ><span style="font-family: 'Nixgon, sans-serif; color: #85c6d7; font-weight:600; font-size: 30px">LOGIN</span>
            </button>
        </div>
        <div style="display: flex; margin-top: 15px; margin-left: 5%">
            <button class="button" style="height: 60px; width: 420px; background-color: transparent; border-radius: 20px; opacity: 60%; border:4px solid #85c6d7;">
                <span style="font-family: 'Nixgon, sans-serif; color: #85c6d7; font-weight:600; font-size: 30px">FIND PW</span>
            </button>
        </div>
        <div style="display: flex; margin-top: 15px; margin-left: 5%">
            <button class="button" style="height: 60px; width: 200px; background-color: transparent; border-radius: 20px; 
            opacity: 60%; border:4px solid #85c6d7;" @click="moveRegister()">
                <span style="font-family: 'Nixgon, sans-serif; color: #85c6d7; font-weight:600; font-size: 30px">JOIN</span>
            </button>
            <button class="button" style="margin-left: 20px; height: 60px; width: 200px; background-color: transparent; border-radius: 20px; opacity: 60%; border:4px solid #85c6d7;">
                <span style="font-family: 'Nixgon, sans-serif; color: #85c6d7; font-weight:600; font-size: 30px">KAKAO</span>
            </button>
        </div>
    </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
export default {
    name: "userLogin",
    data(){
        return{
            user: {
                userId: null,
                userPw: null,
            },
        };
    },
    computed: {
        ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
    },
    methods: {
        ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
        async confirm(){
			await this.userConfirm(this.user);
			let token = sessionStorage.getItem("access-token");
			console.log("1. confirm() token >>" + token);
			if(this.isLogin){
				await this.getUserInfo(token);
				console.log("4. confirm() userinfo :: ", this.userInfo);
				this.$router.push({ name: "home"});
			}
		},
        moveRegister(){
            this.$router.push({name: "userRegister"});
        },
    }
    
}
</script>