<template>
    <div>
        <div v-if="reply.reply_depth == 0">
            <hr  class="mb-2 mt-2" align="left" style="border: solid 2px #ffb5a7; width: 100%; opacity: 70%"/>
            <div style="display: flex; justify-content: space-between; margin-left: 2%; margin-top: 5px">
                <div style="display: flex; width: 80%;">
                    <img :src="profileImage" style="width: 70px; height: 70px; border-radius: 60%; float: left;">
                    <div style="float: left; margin-left: 2%; width: 100%;">
                        <div style="display: flex; flex-direction: column; width: 100%;">
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 70%; font-size: 14px; font-weight: 600; margin-bottom: 0;">{{this.user.userNickname}}</label>
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 10px; margin-top: 0;">{{this.reply.register_time}}</label>
                            <label v-if="!modifyVer" style="font-family: 'Nixgon, sans-serif; opacity: 70%; font-size: 15px; font-weight: 600; margin-top: 1px; word-wrap: break-word;">{{this.reply.content}}</label>
                            <textarea v-else v-model="content" style="margin-top: 2px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 80%; border-radius: 5px;
                            width:100%; height:auto; padding-left: 5px; background-color: transparent;
                            border:2px solid #ffb5a7; float:left; font-size: 15px"/>
                        </div>
                    </div>
                </div>
                <div v-if="userInfo != null">
                    <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 18px; margin-right: 10px;" @click="rereply" >답글</span>
                    <div v-if="userInfo.userId == reply.userId" style="float: right;">
                        <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 18px; margin-right: 10px;" @click="goModify" >수정</span>
                        <span v-if="modifyVer" style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 18px;" @click="cancleModify" >취소</span>
                        <span v-else style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 18px;" @click="deleteRe" >삭제</span>
                    </div>
                </div>
            </div>
            <div v-if="rere">
                <hr class="mb-2 mt-2" align="left" style="border: solid 1px #ffb5a7; width: 100%; opacity: 50%"/>
                <div style="display: flex; margin-left: 7%; margin-right: 7%;">
                    <textarea v-model="recontent" style="margin: 20px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 80%; border-radius: 5px;
                    width:100%; height:auto; padding-left: 15px; background-color: transparent;
                    border:3px solid #ffb5a7; float:left; font-size: 15px" placeholder="답글을 입력해 주세요."/>
                    <button type="button" style="margin-right: 20px; margin-top: 20px; height: 50px; width: 125px; background-color: transparent; border-radius: 20px; 
                    opacity: 80%; border:3px solid #ffb5a7;">
                        <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px;" @click="checkRereply" >등록</span>
                    </button>
                </div>
            </div>
        </div>
        <div v-else>
            <hr class="mb-2 mt-2" align="left" style="border: solid 1px #ffb5a7; width: 100%; opacity: 50%"/>
            <div style="display: flex; justify-content: space-between; margin-left: 7%; margin-top: 5px;">
                <div style="display: flex; width: 85%;">
                    <img :src="profileImage" style="width: 60px; height: 60px; border-radius: 60%; float: left;">
                    <div style="float: left; margin-left: 2%; width: 100%;">
                        <div style="display: flex; flex-direction: column; width: 100%; margin-top: 3px;">
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 70%; font-size: 13px; font-weight: 600; margin-bottom: 0;">{{this.user.userNickname}}</label>
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 9px; margin-top: 0;">{{this.reply.register_time}}</label>
                            <label v-if="!modifyVer" style="font-family: 'Nixgon, sans-serif; opacity: 70%; font-size: 14px; font-weight: 600; margin-top: 1px; word-wrap: break-word;">{{this.reply.content}}</label>
                            <textarea v-else v-model="content" style="margin-top: 2px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 80%; border-radius: 5px;
                            width:100%; height:auto; padding-left: 5px; background-color: transparent;
                            border:2px solid #ffb5a7; float:left; font-size: 15px"/>
                        </div>
                    </div>
                </div>
                <div v-if="userInfo != null">
                    <div v-if="userInfo.userId == reply.userId" style="float: right;">
                        <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 18px; margin-right: 10px;" @click="goModify" >수정</span>
                        <span v-if="modifyVer" style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 18px;" @click="cancleModify" >취소</span>
                        <span v-else style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 18px;" @click="deleteRe" >삭제</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { getUser } from "@/api/member";
import { modifyReply, deleteReply, getMax, writeReply } from '@/api/hotplace';
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
    name: "HotplaceReplyListItem",
    props: {
        reply: Object,
    },
    data() {
        return{
            user: [],
            profileImage: '',
            content: this.reply.content,
            modifyVer: false,
            rere: '',
            recontent: '',
        };
    },
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    created() {
        this.rere = false;
        getUser(
            this.reply.userId,
            ({ data }) => {
                if(data.message === "success"){
                    this.user = data.userInfo;
                    if(this.user.profileImage == null){
                        this.profileImage = require('@/assets/img/none_profile_image.jpg');
                    }else{
                        this.profileImage = "http://localhost:8081/upload/" + this.user.profileImage;
                    }
                }
            },
            (error) => {
                console.log(error);
            }
        )
    },
    methods: {
        goModify(){
            if(!this.modifyVer) this.modifyVer = true;
            else{
                this.modifyVer = false;
                let formData = {
                    replyNo: this.reply.replyNo,
                    articleNo: this.reply.articleNo,
                    reply_depth: this.reply.reply_depth,
                    userId: this.userInfo.userId,
                    content: this.content
                }
                modifyReply(
                    formData,
                    ({data}) => {
                        let msg = "댓글 수정을 실패했습니다.";
                        if(data === "success"){
                            msg = "댓글 수정이 완료되었습니다.";
                        }
                        alert(msg);
                        this.$router.go(0);
                    },
                    (error) => {
                        console.log(error);
                    }
                )
            }
        },
        cancleModify(){
            this.modifyVer = false;
        },
        deleteRe(){
            let formData = {
                replyNo: this.reply.replyNo,
                articleNo: this.reply.articleNo,
                reply_depth: this.reply.reply_depth,
                userId: this.userInfo.userId,
                content: this.content
            }
            deleteReply(
                formData,
                ({data}) => {
                    let msg = "댓글 삭제를 실패했습니다.";
                    if(data === "success"){
                        msg = "댓글 삭제가 완료되었습니다.";
                    }
                    alert(msg);
                    this.$router.go(0);
                },
                (error) => {
                    console.log(error);
                }
            )
        },
        rereply(){
            console.log(this.rere);
            this.rere = !this.rere;
        },
        checkRereply(){
            let err = true;
            let msg = "";

            err && !this.recontent && ((msg = "답글을 입력해 주세요."), (err = false));

            if(!err) alert(msg);
            else this.registerRereply();
        },
        registerRereply(){
            var max;
            getMax(
                this.reply.articleNo,
                this.reply.replyNo,
                ({data}) => {
                    max = data;
                        let formData = {
                        replyNo: this.reply.replyNo,
                        articleNo: this.reply.articleNo,
                        userId: this.userInfo.userId,
                        content: this.recontent,
                        reply_depth: max+1,
                    }
                    console.log(formData);

                    writeReply(
                        formData,
                        ({data}) => {
                            let msg = "댓글 작성을 실패했습니다.";
                            if(data === "success"){
                                msg = "댓글 작성이 완료되었습니다.";
                            }
                            alert(msg);

                            this.$router.go(0);
                        },
                        (error) => {
                            console.log(error);
                        }
                    )
                },
                (error) => {
                    console.log(error);
                }
            )

            

            

            
        },
    }
}
</script>

<style scoped>
textarea:focus {outline: 2px solid #85c6d7;}

</style>