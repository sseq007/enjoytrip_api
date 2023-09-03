<template>
    <div style="text-align:center; width: 285px;  height: 320px; margin-right: 3%; margin-top: 3%; background-color: #ffe4e0;
    box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.3); border: solid 5px #ffb5a7; border-radius: 5%; text-decoration: none;" display="inline-block">
        <div style="margin-top: 10px; margin-left: 40px; overflow: hidden; ">
            <div><img :src="profileImage" style="width: 40px; height: 40px; border-radius: 60%; float: left;"></div>
            <div><p style="font-family: 'Nixgon, sans-serif; color: #000000; opacity: 60%; font-size: 17px; font-weight: 600; float:left; margin-left: 10px; padding-top:7px;">{{ this.user.userNickname }}</p></div>
        </div>
        <router-link :to="'/hotplace/' + hotplace.articleNo"><img :src="mainImage" style="width: 200px; height: 200px; margin-top: 5px;"> </router-link>
        <div style="display: flex; margin-left: 13%; margin-top: 2px;">
            <div v-if="userInfo == null">
                <label style="font-size: 22px;" @click="alarmLogin">🤍</label>
            </div>
            <div v-else style="display: flex;">
                <div @click="likePost">
                    <label v-if="like" style="font-size: 22px;">❤️</label>
                    <label v-else style="font-size: 22px;">🤍</label>
                </div>
            </div>
            <label style="font-family: 'Nixgon, sans-serif; color: #000000; opacity: 60%;
            font-size: 20px; font-weight: 600; margin-left: 2px; margin-top: 2px;">{{ heart }}</label>
            <label style="font-size: 22px; margin-left: 2px;">💬</label>
            <label style="font-family: 'Nixgon, sans-serif; color: #000000; opacity: 60%;
            font-size: 20px; font-weight: 600; margin-left: 2px; margin-top: 2px;">{{ hotplace.reply_count }}</label>
        </div>
    </div>
</template>

<script>
import { getUser } from "@/api/member";
import { checkHeart, plusHeart, minusHeart } from "@/api/hotplace";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
    name: "HotplaceListItem",
    props: {
        hotplace: Object,
    },
    data() {
        return{
            user: [],
            profileImage: '',
            mainImage: '',
            like: false,
            heart: this.hotplace.heart_count,
        };
    },
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    created() {
        getUser(
            this.hotplace.userId,
            ({ data }) => {
                if(data.message === "success"){
                    this.user = data.userInfo;
                    if(this.user.profileImage == null){
                        this.profileImage = require('@/assets/img/none_profile_image.jpg');
                    }else{
                        this.profileImage = "http://localhost:8081/upload/" + this.user.profileImage;
                    }
                    
                    if(this.userInfo != null){
                        let formdata = new FormData();
                        formdata.append('articleNo', this.hotplace.articleNo);
                        formdata.append('userId', this.userInfo.userId);

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

                }
            },
            (error) => {
                console.log(error);
            }
        );
        this.mainImage = "http://localhost:8081/upload/" + this.hotplace.imageName;
    },
    methods: {
        alarmLogin(){
            alert("로그인 후 이용해 주세요.");
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
    },
}
</script>