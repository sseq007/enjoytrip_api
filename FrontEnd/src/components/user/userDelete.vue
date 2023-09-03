<template>
<!-- [S] content -->
    <div class="container">
        <div>
            <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 50px" class="m-0 mt-5">마이 페이지</h2>
            <hr class="mb-3 mt-0" align="left" style="border: solid 3px brown; width: 50%" />
        </div>
        <div style="margin: auto; margin-top: 100px; margin-bottom: 100px; width: 50%; height: auto; border: 3px solid #ffb5a7; border-radius: 10px" class="text-center">
            <nav class="navbar navbar-expand-sm" style="background-color: #ffb5a7; font-family: 'Black Han Sans', sans-serif; opacity: 75%; text-align: center; font-size: 20px">
                <div class="collpase navbar-collapse justify-content-center">
                    <ul class="navbar-nav">
                        <li class="nav-item" style="margin-inline: 70px">
                            <router-link :to="'/user/userPage'" class="nav-link active">회원 정보 수정</router-link>
                        </li>
                        <li class="nav-item" style="margin-inline: 70px">
                            <router-link :to="'/user/userDelete'" class="nav-link active">회원 탈퇴</router-link>
                        </li>
                    </ul>
                </div>
            </nav>
            <div id="removeId">
                <form action="" style="text-align: left" id="form-delete" method="POST">
                    <div class="m-5">
                        <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 20px; text-align: left" class="m-0 mt-4">회원 탈퇴</h2>
                        <hr class="mb-3 mt-0" align="left" style="border: solid 2px #ffb5a7; width: 30%; opacity: 100%" />
                        <div class="mb-3">
                            <label for="pwdCheck" class="form-label" style="color: #663333">Password check:</label>
                            <input type="password" class="form-control" id="pwdCheck" placeholder="비밀번호 확인" name="pwdCheck" v-model="userPw"/>
                        </div>

                        <div style="text-align: center">
                            <button type="button" class="btn btn-outline-secondary m-0 mt-4 mb-3" id="btn-delete" @click="checkValue">탈퇴</button>
                            <button type="button" class="btn btn-outline-secondary m-0 mt-4 mb-3">취소</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { deleteMember } from "@/api/member";

const memberStore = "memberStore";
export default {
    name: "userDelete",
    components:{
    },
    data(){
        return{
            userPw: ''
        };
    },
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    methods:{
        ...mapActions(memberStore, ["userLogout"]),
        checkValue(){
            let err = true;
            let msg = "";
            console.log(this.userPw);
            console.log(this.userInfo.userPw);
            if(this.userPw != this.userInfo.userPw){
                msg = "비밀번호를 다시 확인해 주세요";
                err = false;
            }
            if (!err) alert(msg);
            else this.delete();
        },
        async delete(){
            let id = this.userInfo.userId;
            console.log(id);

            deleteMember(
                id,
                ({data}) => {
                    let msg = "멤버 삭제를 실패했습니다.";
                    if(data.message === "success"){
                        msg = "삭제가 완료되었습니다.";
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
            console.log(this.userInfo.userId);
			this.userLogout(this.userInfo.userId);
			sessionStorage.removeItem("access-token");
			sessionStorage.removeItem("refresh-token");
            this.$router.push({ name: "home" });
        },
    },
}
</script>