<template>
    <div>

        <div class="container">
            <div>
                <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 50px;" class="m-0 mt-5">여행 동행
                    찾기</h2>
                <hr class="mb-3 mt-0" align="left" style="border: solid 3px brown; width: 50%" />
            </div>
            <div style="height: 20px;"></div>
            <div style="background-color: #ffe4e0; padding: 4vh; border-radius: 2vh;">
                <div>
                    <!-- 이미지 주소 수정 필요 -->
                    <img :src="'http://localhost:8081/upload/' + article.partnerImage" class=""
                        style="border-radius: 1vh; width: 100%; height: 400px">

                </div>
                <div style="height: 70px;"></div>



                <div class="row">

                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-11">
                                <div class="d-flex" style="justify-content: space-between;">
                                    <h2>{{ article.subject }}</h2>
                                    <div>
                                        <div class="d-flex">
                                            <h2 style="font-family: Nixgon, sans-serif; font-weight: bold;">{{ checked }}
                                            </h2>
                                            <div v-if="userInfo">
                                                <div  class="form-check form-switch" style="margin-left: 1vh;"
                                                    v-if="userInfo.userId == article.userId">
                                                    <input class="form-check-input" type="checkbox" role="switch"
                                                        id="flexSwitchCheckChecked" :checked="this.article.end"
                                                        style="width: 6vh; height: 3vh;" @change="toggleSwitch">
                                                </div>
                                            </div>


                                        </div>
                                    </div>

                                </div>
                                <div class="" style="border: 3px solid rgb(255, 181, 167); border-radius: 10px;">
                                    <div
                                        style="padding-left: 30px; padding-top: 20px; padding-right: 30px; padding-bottom: 20px;">
                                        <div class="d-flex">
                                            <p style="margin-right: 8px; font-family: Nixgon, sans-serif; font-weight: bold;">지역</p>
                                            <span style="margin-right: 8px;">{{ article.location }}</span>
                                            <p style="margin-right: 8px; font-family: Nixgon, sans-serif; font-weight: bold;" >모집인원 </p>
                                            <span>{{ article.partnerCount }}</span>

                                        </div>
                                        <div>
                                            <i class="fi fi-rr-calendar-days" style="margin-right: 2px;"></i>
                                            {{ article.startDate }} ~ {{ article.endDate }}
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>
                        <div style="margin-top: 40px;">
                            <!-- 키워드 선택한거 가져오자 -->
                            <div>저는 <span v-for="keyword in keywordOne" :key="keyword"
                                    style="color: #85c6d7; font-family: 'Nixgon, sans-serif; font-weight:600;">{{
                                        keyword.label
                                    }}, </span> 여행을 떠나려고 해요.</div>
                            <div>저는 <span v-for="keyword in keywordTwo" :key="keyword"
                                    style="color: #85c6d7; font-family: 'Nixgon, sans-serif; font-weight:600;">{{
                                        keyword.label
                                    }}, </span>여행자에요</div>


                        </div>
                        <div style="margin-top: 40px;">
                            <!-- 키워드 선택한거 가져오자 -->
                            <div>{{ article.content }}</div>



                        </div>
                        <div style="margin-top: 100px;">
                            <!-- 키워드 선택한거 가져오자 -->
                            <div class="d-flex">
                                <div style="margin-right: 10px;">{{ article.registerTime.substring(0, 10) }} {{
                                    article.registerTime.substring(11, 16) }}</div>
                                <div style="margin-right: 10px;">조회수 {{ article.hit }}</div>
                                <div v-if="userInfo">
                        
                                <div style="margin-right: 10px;" v-if="userInfo.userId === article.userId"><strong> <router-link :to="'/trippartner/modfiy/' + article.articleNo" style="text-decoration: none; color: inherit;">글수정</router-link></strong>
                                
                                </div>
                            </div>
                            <div v-if="userInfo">
                            <div style="margin-right: 10px;" v-if="userInfo.userId === article.userId">
                                <strong> <a href="#" @click="deleteArticle"
                                        style="text-decoration: none; color: inherit;">삭제하기</a></strong>
                            </div>
                        </div>
                            <div><a href="" style="text-decoration: none;">신고하기</a></div>

                        </div>



                    </div>
                </div>
                <div class="col-md-4">
                    <div class="" style="border: 3px solid rgb(255, 181, 167); border-radius: 10px;">
                        <div style="padding-left: 20px; padding-top: 20px; padding-right: 20px; padding-bottom: 20px;">

                            <div class="row">
                                <div class="col">
                                    <div class="d-flex" style="margin-bottom: 15px;">
                                        <img :src="profileImage" class="profile_image"
                                            style="padding:4px; border-radius: 40%; width: 58px; height: 58px; margin-left: 25px;">

                                        <div style="margin-left: 15px;">
                                            <h5>{{  user.userNickname }}</h5>
                                            <div>{{  user.age }}세 {{  user.gender }} 대한민국</div>
                                        </div>
                                    </div>
                                    <div style="padding-top: 20px; margin-left: 25px;">
                                        <i class="fi fi-rr-bulb"></i>
                                        동행 신청을 클릭해보세요!
                                        <button type="button"
                                            style="width: calc(100% - 15px); height: 60px; background-color: white; margin-top: 17px;"
                                            class="btn" @click="showModal">동행 신청하기</button>
                                    </div>
                                </div>


                            </div>
                        </div>
                    </div>

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


                <div class="row mb-5">

                    <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; margin-bottom: 3vh;">동행 정보</h2>
                    <div class="d-flex mb-3">

                        <img src="@/assets/img/user.png" style="width: 3vh; height: 3vh;">
                        <div style="margin-left: 2vh; font-size: 2vh;">{{ user.userNickname }}</div>
                    </div>
                    <div class="d-flex mb-3">

                        <img src="@/assets/img/marker.png" style="width: 3vh; height: 3vh;">
                        <div style="margin-left: 2vh; font-size: 2vh;">{{ article.location }}</div>
                    </div>
                    <div class="d-flex mb-3">

                        <img src="@/assets/img/user-add.png" style="width: 3vh; height: 3vh;">
                        <div style="margin-left: 2vh; font-size: 2vh;">{{ article.partnerCount }}</div>
                    </div>
                    <div class="d-flex mb-3">

                        <img src="@/assets/img/calendar.png" style="width: 3vh; height: 3vh;">
                        <div style="margin-left: 2vh; font-size: 2vh;"> {{ article.startDate.substring(5) }} ~ {{
                            article.endDate.substring(5) }} </div>
                    </div>

                </div>

                <div class="row">
                    <b-form-textarea id="textarea" v-model="text" placeholder="궁금한점 쪽지로 보내보세요.." rows="9"></b-form-textarea>
                </div>

                <template #modal-footer="{ hide }">
                    <button type="button" @click="hide"
                        style="width: calc(50% - 15px); background-color: #d3d3d3; height: 60px;" id="btn-login"
                        class="btn">취소</button>
                    <button type="button" style="width: calc(50% - 15px); height: 60px; background-color: #79CF9F;"
                        class="btn" @click="registNote">보내기</button>


                </template>
            </b-modal>
        </div>
    </div>

</div></template>

<script>
import axios from "axios";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
    name: "PartnerView",
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    data() {
        return {
            checked: '',
            check: true,
            modalVisible: false,
            article: [],
            keywordOne: [],
            keywordTwo: [],
            text: "",
            user: [],
            profileImage:''
        };
    },
    created() {


        axios.get(`http://localhost:8080/api/trippartner/view/${this.$route.params.articleNo}`)
            .then(response => {
                console.log(response.data);
                this.article = response.data;
                if (this.article.end) {
                    // console.log(this.article.end),
                    this.checked = '모집중'
                } else[
                    console.log(this.article.end),
                    this.checked = '모집종료'
                ]
                axios.get(`http://localhost:8080/api/trippartner/user/${this.article.userId}`)
            .then(response => {
                this.user = response.data;
                console.log("user 정보는" + this.user);
                if(this.user.profileImage == null){
                        this.profileImage = require('@/assets/img/none_profile_image.jpg');
                    }else{
                        this.profileImage = "http://localhost:8081/upload/" + this.user.profileImage;
                    }

            })
            .catch(error => {
                console.log(error);
            });
            })
            .catch(error => {
                console.log(error);
            });

        axios.get(`http://localhost:8080/api/trippartner/keyword/${this.$route.params.articleNo}`)
            .then(response => {
                console.log(response.data);
                this.keywordOne = response.data;
            })
            .catch(error => {
                console.log(error);
            });
        axios.get(`http://localhost:8080/api/trippartner/keyword2/${this.$route.params.articleNo}`)
            .then(response => {
                console.log(response.data);
                this.keywordTwo = response.data;
            })
            .catch(error => {
                console.log(error);
            });
            

    },
    methods: {
        deleteArticle() {
            if (confirm("삭제 하시겠습니까?")) {
                
                axios.delete(`http://localhost:8080/api/trippartner/delete/${this.$route.params.articleNo}`)
                    .then(response => {
                        console.log(response);
                        alert('삭제되었습니다.');
                        this.$router.push('/trippartner');
    
    
                    })
                    .catch(error => {
                        console.log(error);
                    });
            }
        },
        toggleSwitch() {

            var formData = {
                articleNo: this.article.articleNo,
                // fromuserId: this.note.touserId,
                // touserId: this.note.fromuserId,
                // content: this.note.content,
                isEnd: this.article.end
            }
            this.article.end = !this.article.end;
            if (this.article.end) {
                this.checked = "모집중";
                axios.put('http://localhost:8080/api/trippartner/updateisendOn', formData, {

                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                    .then(response => {
                        console.log('updateisendOn 결과:', response.data);
                        console.log("이글의 모집은" + this.article.end);
                        // location.href = `/user/note/${this.note.noteNo}`;

                    })
                    .catch(error => {
                        console.log(error);
                        alert('updateisendOn update 실패하였습니다.');
                    });

            } else {
                this.checked = "모집종료"
                axios.put('http://localhost:8080/api/trippartner/updateisendOff', formData, {

                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                    .then(response => {
                        console.log('updateisendOff 결과:', response.data);
                        console.log("이글의 모집은" + this.article.end);
                        // location.href = `/user/note/${this.note.noteNo}`;
                    })
                    .catch(error => {
                        console.log(error);
                        alert('updateisendOff update 실패하였습니다.');
                    });
            }
        },
        showModal() {
            console.log(this.userInfo);
            console.log(this.article.userId);

            if (this.userInfo != null) {
                if (this.article.userId != this.userInfo.userId) {
                    this.modalVisible = true;
                }
                else {
                    alert("쪽지를 보낼 수 없습니다");

                }
            }
            else {
                this.modalVisible = false; // 모달 표시 여부 변경
                alert("로그인을 해주세요");
            }
        },
        registNote() {
            var formData = {
                fromuserId: this.userInfo.userId,
                articleNo: this.article.articleNo,
                touserId: this.article.userId,
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

