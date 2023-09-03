<template>
    <div style="margin: auto; margin-top: 100px; margin-bottom: 100px; width: 50%; height: auto; border: 3px solid #ffb5a7; border-radius: 10px; background-color: #ffe4e0; " class="text-center">
            <nav class="navbar navbar-expand-sm" style="width: 100%; background-color: #ffb5a7; font-family: 'Black Han Sans', sans-serif; text-align: center; font-size: 20px;">
                <div class="height: 80px; width: 100%;">
                    <ul style="width: 50vmin; display: flex;">
                        <img :src="profileImage" style="border-radius: 50%; width: 80px; height: 80px; margin-left: 1vmin; margin-top: 1%; float:left;">
                        <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 30px; font-weight: 600; margin-top: 22px; margin-left: 13px; float: left;"
                        >{{this.user.userNickname}}</label>
                    </ul>
                </div>
            </nav>
            <div style=" margin-left: 5vmin;">
                <h2 style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 50px; font-weight: 600; margin-top: 20px; margin-left: 15px; text-align: left" class="m-0 mt-2">{{this.hotplace.subject}}</h2>
                <hr class="mb-2 mt-0" align="left" style="border: solid 3px #ffb5a7; width: 70%; opacity: 100%" />
            </div>
            
            <div id="Contents">
                <form action="" style="text-align: left" id="form-write" name="form-write" method="post" encType="multipart/form-data">
                    <div class="m-5">
                        <div class="mb-3" align="center" style="position: relative">
                                <div class="hotpleImg mb-3 mt-1">
                                    <img :src="mainImage" class="hotple_image" style="border-radius: 5%; width: 100%; height: 100%;">
                                </div>                               
                        </div>
                        <div style="display: flex; margin-top: 3px;">
                            <div v-if="userInfo == null">
                                <label style="font-size: 40px;" @click="alarmLogin">🤍</label>
                            </div>
                            <div v-else style="display: flex">
                                <div @click="likePost">
                                    <label v-if="like" style="font-size: 40px;">❤️</label>
                                    <label v-else style="font-size: 40px;">🤍</label>
                                </div>
                            </div>
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 60%;
                            font-size: 35px; font-weight: 600; margin-left: 2px; margin-top: 7px;">{{ heart }}</label>
                            <label style="font-size: 40px; margin-left: 2px;">💬</label>
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 60%;
                            font-size: 35px; font-weight: 600; margin-left: 2px; margin-top: 7px;">{{ hotplace.reply_count }}</label>
                        </div>
                        <hr class="mb-2 mt-2" align="left" style="border: solid 2px #ffb5a7; width: 100%; opacity: 70%" />
                        <div style=" margin-top: 15px; margin-left: 3%; margin-right: 3%; height: 70px;">
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 20px; font-weight: 600; width: 100%; word-wrap: break-word;">
                                {{this.hotplace.content}}
                            </label>
                        </div>
                        <div v-if="userInfo">
                            
                            <h2 style="font-family: 'Nixgon, sans-serif; opacity: 70%; font-size: 20px; font-weight: 600; text-align: left" class="m-0 mt-4">댓글 작성</h2>
                            <hr class="mb-2 mt-2" align="left" style="border: solid 2px #ffb5a7; width: 100%; opacity: 70%"/>
                            <div style="display: flex;">
                                <textarea v-model="content" style="margin: 20px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 80%; border-radius: 5px;
                                width:100%; height:auto; padding-left: 15px; background-color: transparent;
                                border:3px solid #ffb5a7; float:left; font-size: 20px" placeholder="댓글을 입력해 주세요."/>
                                <button type="button" style="margin-right: 20px; margin-top: 25px; height: 55px; width: 145px; background-color: transparent; border-radius: 20px; 
                                opacity: 100%; border:4px solid #ffb5a7;" @click="checkReply">
                                    <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px;" >등록</span>
                                </button>
                            </div>
                        </div>
                        <div v-if="reply > 0">
                            <h2 style="font-family: 'Nixgon, sans-serif; opacity: 70%; font-size: 20px; font-weight: 600; text-align: left" class="m-0 mt-2">댓글</h2>
                            <div>
                                <hotplace-reply-list-item v-for="reply in replies" :key="reply.replyNo" :reply="reply">
                                </hotplace-reply-list-item>
                            </div>
                        </div>
                        
                        <div style="justify-content: center; margin-top: 30px; display: flex;">
                            <div v-if="userInfo" style="display:flex;">
                                <button v-if="userInfo.userId == this.hotplace.userId" type="button" style="margin-right: 30px; height: 55px; width: 145px; background-color: transparent; border-radius: 20px; 
                                opacity: 100%; border:4px solid #ffb5a7;" @click="moveModify">
                                    <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px;" >수정</span>
                                </button>
                                <button v-if="userInfo.userId == this.hotplace.userId" type="button" style="margin-right: 30px; height: 55px; width: 145px; background-color: transparent; border-radius: 20px; 
                                opacity: 100%; border:4px solid #ffb5a7;" @click="deleteHot">
                                    <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px;" >삭제</span>
                                </button>
                            </div>
                            <button type="button" style=" height: 55px; width: 145px; background-color: transparent; border-radius: 20px; 
                            opacity: 100%; border:4px solid #ffb5a7;" @click="moveList">
                                <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px;" >목록</span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
    </div>
</template>

<script>
import { getHotplace, deleteHotplace, checkHeart, plusHeart, minusHeart, 
writeReply, listReply } from '@/api/hotplace';
import HotplaceReplyListItem from './hotplaceReplyListItem.vue';
import { getUser } from "@/api/member";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
    name: "hotplaceContent",
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    components: {
        HotplaceReplyListItem
    },
    data() {
        return{
            replies: [],
            hotplace: [],
            user: [],
            profileImage: '',
            mainImage: '',
            like: false,
            heart: '',
            reply: '',
            content: '',
        }
    },
    created(){
        getHotplace(
            this.$route.params.articleNo,
            ({data}) => {
                this.hotplace = data;
                console.log(this.hotplace);
                getUser(
                    this.hotplace.userId,
                    ({ data }) => {
                        if(data.message === "success"){
                            this.user = data.userInfo;
                            console.log(this.user);
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
                );
                if(this.userInfo != null){
                    const formdata = {
                        articleNo: this.hotplace.articleNo,
                        userId: this.userInfo.userId,
                    }
                    checkHeart(
                        formdata,
                        ({ data }) => {
                            if(data === 0){
                                this.like = false;
                            }else this.like = true;
                        },
                        (error) => {
                            console.log(error);
                        }
                    );
                }
                
                this.heart = this.hotplace.heart_count;
                this.reply = this.hotplace.reply_count;
                this.mainImage = "http://localhost:8081/upload/" + this.hotplace.imageName;
            },
            (error) => {
                console.log(error);
            }
        ),
        listReply(
            this.$route.params.articleNo,
            ({data}) => {
                this.replies = data;
            },
            (error) => {
                console.log(error);
            }
        )
    },
    methods: {
        alarmLogin(){
            alert("로그인 후 이용해 주세요.");
        },
        moveList(){
            this.$router.push({name: "hotplace"});
        },
        moveModify(){
            this.$router.push('/hotplace/modify/' + this.hotplace.articleNo);
        },
        deleteHot(){
            deleteHotplace(
                this.hotplace.articleNo,
                ({data}) => {
                    let msg = "글 삭제를 실패했습니다.";
                    if(data === "success"){
                        msg = "글 삭제가 완료되었습니다.";
                    }
                    alert(msg);

                    this.$router.push({ name: "hotplace" });
                },
                (error) => {
                    console.log(error);
                }
            )
        },
        likePost(){
            let formData = new FormData();
            formData.append('articleNo', this.hotplace.articleNo);
            formData.append('userId', this.userInfo.userId);
            if(!this.like){
                this.like= true;
                plusHeart(
                    formData,
                    ({ data }) => {
                        if(data === "success"){
                            this.heart++;
                        }
                    },
                    (error) => {
                        console.log(error);
                    }
                )
            }
            else{
                this.like = false;
                minusHeart(
                    formData,
                    ({ data }) => {
                        if(data === "success"){
                            this.heart--;
                        }
                    },
                    (error) => {
                        console.log(error);
                    }
                )
            } 
        },
        checkReply(){
            let err = true;
            let msg = "";

            err && !this.content && ((msg = "댓글을 입력해 주세요."), (err = false));

            if(!err) alert(msg);
            else this.registerReply();
        },
        registerReply(){
            let formData = {
                articleNo: this.hotplace.articleNo,
                userId: this.userInfo.userId,
                content: this.content,
                reply_depth: '0',
            }

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
    },
}
</script>

<style scoped>
textarea:focus {outline: 2px solid #85c6d7;}

</style>