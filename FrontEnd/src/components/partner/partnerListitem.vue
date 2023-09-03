
<template>
    <div class="col-lg-3 col-md-4 col-sm-12">
        <div class="card mb-3 shadow bg-gray rounded" :style="{ 'background-color': article.end ? '#ffe4e0' : '#c4c4c4' } " style="border: 4px solid #ffb5a7;">
            <div>
                <div class="row" style="padding-top: 8px; padding-bottom: 8px; padding-left: 14px; padding-right: 14px;">
                    <div class="col-6">
                        <div class="d-flex">
                            <img  :src="profileImage" class="profile_image"
                                style="padding:4px; border-radius: 40%; width: 40px; height: 40px; margin-right: 8px; ">

                            <div>
                                <div>{{ user.userNickname }}</div>
                                <div style="color: #80B5ff;">{{  user.age }}세 {{  user.gender }}</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-6" style="padding-left: 18px;">

                        <div>
                            <i class="fi fi-rr-calendar-days">여행 기간</i>
                            <div style="color: #25E09A;">
                                {{ article.startDate.substring(5) }} ~ {{ article.endDate.substring(5) }}
                            </div>
                        </div>

                    </div>
                </div>
                <!-- <div class="img"> -->
                <div class="img-expension" @mouseenter="showDiv" @mouseleave="hideDiv">
                    <router-link :to="'/trippartner/view/' + article.articleNo" style="text-decoration: none;">
                        <!-- <img :src="require(`@/assets/img/partnerimg/${article.partnerImage}`)" style="border-radius: 5%; width: 100%; height: 144px"> -->
                        <img :src="'http://localhost:8081/upload/' + article.partnerImage"
                            style="border-radius: 5%; width: 100%; height: 144px">

                        <div class="d-flex"
                            style="position: absolute; top: 15%; left: 16%; transform: translate(-50%, -50%); background-color: white; padding: 8px; border-radius: 1vh; fon">
                            <img src="@/assets/img/marker.png" style="width: 2vh; height: 2vh;">
                            <div style="font-size: 11px;">{{ article.location }}</div>
                        </div>
                        <div v-if="isHovered" class="hover-div">
                            <!-- Content of the div to show on hover -->
                            <div class="row">
                                <div class="" style="display:flex; justify-content: space-around;">

                                    <div>모집중 : {{ article.partnerCount }}</div>
                                    <div class="d-flex">

                                        <i class="fi fi-rr-eye" style="margin-right: 1vh;"> {{ article.hit }}</i>
                                        <i class="fi fi-rr-paper-plane" style="margin-right: 3vh;">{{ sendcount }}</i>

                                    </div>

                                </div>


                            </div>
                        </div>
                    </router-link>
                </div>
                <!-- </div> -->
            </div>
            <!-- <img src="..." class="card-img-top" alt="Card image cap"> -->
            <div class="p-2">

                <p style="font-family: Nixgon, sans-serif; font-weight: bold; margin-left: 1vh; font-size: 25px;">
                    {{ article.subject }}
                </p>
                <p style="margin-left: 1vh;">
                    {{ article.content.substring(0,20) }}..
                </p>
            </div>
        </div>
    </div>
</template>
  
  
  
  
  
<script>
import axios from "axios";
export default {
    name: "PartnerListItem",
    props: {
        article: Object,

    },
    data() {
        return {
            isHovered: false,
            sendcount: '',
            user: [],
            profileImage:''
        };
    },
    created() {
        axios.get(`http://localhost:8080/api/note/countsend/${this.article.articleNo}`)
            .then(response => {
                console.log(response.data);
            
                this.sendcount = response.data;
            })
            .catch(error => {
                console.log(error);
            });
            axios.get(`http://localhost:8080/api/trippartner/user/${this.article.userId}`)
            .then(response => {
                this.user = response.data;
                console.log("user22 정보는" + this.user);
                if(this.user.profileImage == null){
                        this.profileImage = require('@/assets/img/noimg.jpg');
                    }else{
                        this.profileImage = "http://localhost:8081/upload/" + this.user.profileImage;
                    }

            })
            .catch(error => {
                console.log(error);
            });
            

    },
    methods: {
        showDiv() {
            this.isHovered = true;
        },
        hideDiv() {
            this.isHovered = false;
        },
    },
};
</script>
  
<style scoped>
.img-expension {
    transform: scale(1);
    transition: all 0.3s ease-in-out;
    position: relative;
    overflow: hidden;
    /* 부드러운 모션을 위해 추가*/
}

.img-expension:hover {
    transform: scale(1.1);
}

.img-expension:hover .hover-div {
    opacity: 1;
}

.hover-div {
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 30px;
    background-color: rgba(0, 0, 0, 0.7);
    color: white;
    opacity: 0;
    transition: opacity 0.3s ease-in-out;
}

/* .img {width:100%; height:144px; overflow:hidden } */
</style>
  
  
  
  
  
  
  
  
  
  
  
  