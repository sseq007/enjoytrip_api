
<template>
    <!-- header 부분  필요-->
    <div>

        <div class="container">
            <div v-if="isLoading" class="spinner-container">
                <b-spinner label="Spinning"></b-spinner>
                <div>잠시만 기다려주세요</div>

            </div>
            <div>
                <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 50px" class="m-0 mt-5">여행 동행
                    찾기</h2>
                <hr class="mb-3 mt-0" align="left" style="border: solid 3px brown; width: 50%" />
            </div>
            <div style="height: 20px"></div>

            <div class="row p-3"
                style="border-radius: 1vh; background-color: #ffe4e0; border: 4px solid #ffb5a7; padding: 3vmin;">
                <div class="col-2">

                    <div class="list-group" id="list-tab" role="tablist" style="text-align: center;">
                        <div class="d-flex">
                            <div>
                                <a class="list-group-item list-group-item-action list-group-item-light"
                                    :class="{ active: selectedTab === '서울시' }" @click="selectTab('서울시')" href="#list-seoul"
                                    role="tab" aria-controls="list-seoul">
                                    서울
                                </a>
                                <a class="list-group-item list-group-item-action list-group-item-light"
                                    :class="{ active: selectedTab === '인천시' }" @click="selectTab('인천시')"
                                    href="#list-profile" role="tab" aria-controls="list-profile">
                                    인천
                                </a>
                                <a class="list-group-item list-group-item-action list-group-item-light"
                                    :class="{ active: selectedTab === '대전시' }" @click="selectTab('대전시')"
                                    href="#list-messages" role="tab" aria-controls="list-messages">
                                    대전
                                </a>
                                <a class="list-group-item list-group-item-action list-group-item-light"
                                    :class="{ active: selectedTab === '대구시' }" @click="selectTab('대구시')"
                                    href="#list-settings" role="tab" aria-controls="list-settings">
                                    대구
                                </a>
                                <a class="list-group-item list-group-item-action list-group-item-light"
                                    :class="{ active: selectedTab === '광주시' }" @click="selectTab('광주시')"
                                    href="#list-settings" role="tab" aria-controls="list-settings">
                                    광주
                                </a>
                                <a class="list-group-item list-group-item-action list-group-item-light"
                                    :class="{ active: selectedTab === '부산시' }" @click="selectTab('부산시')"
                                    href="#list-settings" role="tab" aria-controls="list-settings">
                                    부산
                                </a>
                                <a class="list-group-item list-group-item-action list-group-item-light"
                                    :class="{ active: selectedTab === '울산시' }" @click="selectTab('울산시')"
                                    href="#list-settings" role="tab" aria-controls="list-settings">
                                    울산
                                </a>
                                <a class="list-group-item list-group-item-action list-group-item-light"
                                    :class="{ active: selectedTab === '경기도' }" @click="selectTab('경기도')"
                                    href="#list-settings" role="tab" aria-controls="list-settings">
                                    경기도
                                </a>

                            </div>
                            <div>
                                <a class="list-group-item list-group-item-action list-group-item-light"
                                    :class="{ active: selectedTab === '강원도' }" @click="selectTab('강원도')"
                                    href="#list-settings" role="tab" aria-controls="list-settings">
                                    강원도
                                </a>
                                <a class="list-group-item list-group-item-action list-group-item-light"
                                    :class="{ active: selectedTab === '충청북도' }" @click="selectTab('충청북도')" href="#list-home"
                                    role="tab" aria-controls="list-home">
                                    충청북도
                                </a>
                                <a class="list-group-item list-group-item-action list-group-item-light"
                                    :class="{ active: selectedTab === '충청남도' }" @click="selectTab('충청남도')"
                                    href="#list-profile" role="tab" aria-controls="list-profile">
                                    충청남도
                                </a>
                                <a class="list-group-item list-group-item-action list-group-item-light"
                                    :class="{ active: selectedTab === '경상북도' }" @click="selectTab('경상북도')"
                                    href="#list-messages" role="tab" aria-controls="list-messages">
                                    경상북도
                                </a>
                                <a class="list-group-item list-group-item-action list-group-item-light"
                                    :class="{ active: selectedTab === '경상남도' }" @click="selectTab('경상남도')"
                                    href="#list-settings" role="tab" aria-controls="list-settings">
                                    경상남도
                                </a>
                                <a class="list-group-item list-group-item-action list-group-item-light"
                                    :class="{ active: selectedTab === '전라북도' }" @click="selectTab('전라북도')"
                                    href="#list-settings" role="tab" aria-controls="list-settings">
                                    전라북도
                                </a>
                                <a class="list-group-item list-group-item-action list-group-item-light"
                                    :class="{ active: selectedTab === '전라남도' }" @click="selectTab('전라남도')"
                                    href="#list-settings" role="tab" aria-controls="list-settings">
                                    전라남도
                                </a>
                                <a class="list-group-item list-group-item-action list-group-item-light"
                                    :class="{ active: selectedTab === '제주도' }" @click="selectTab('제주도')"
                                    href="#list-settings" role="tab" aria-controls="list-settings">
                                    제주도
                                </a>

                            </div>
                        </div>

                    </div>

                </div>

                <div class="col-9">


                    <div class="col-8">
                        <div class="d-flex">
                            <select class="form-select" aria-label="Default select example" v-model="purpose">
                                <option value="" disabled selected>모집목적</option>
                                <option value="카풀">카풀</option>
                                <option value="맛집">맛집</option>
                                <option value="기타">기타</option>

                            </select>

                            <!-- <label for="board-write-title" class="form-label">모집인원 </label> -->
                            <select class="form-select ms-1" aria-label="Default select example" v-model="recruitment">
                                <option value="" disabled selected>모집인원</option>
                                <option value="1명">1명</option>
                                <option value="2명">2명</option>
                                <option value="3명">3명</option>
                                <option value="4명">4명</option>
                                <option value="5명이상">5명이상</option>
                            </select>

                        </div>
                    </div>

                    <div class="col-8 mt-5">
                        <label for="example-datepicker">시작일</label>
                        <label for="example-datepicker" style="margin-left: 275px;">종료일</label>
                        <div class="d-flex">
                            <b-form-datepicker id="example-datepicker" v-model="value_start" class="mb-2 custom-datepicker"
                                :min="today"></b-form-datepicker>

                            <b-form-datepicker id="example-datepicker" v-model="value_end" class="mb-2 custom-datepicker"
                                :min="today"></b-form-datepicker>


                        </div>
                    </div>





                </div>
                <div style="height: 77px"></div>

                <div class="mb-3 mt-3" align="center" style="position: relative">
                    <div class="hotpleImg mb-3 mt-3">
                        <img :src="image" class="hotple_image" style="border-radius: 5%; width: 100%; height: 100%;">
                    </div>
                    <div>
                        <label className="input-file-button" for="uploadHotplace" style="left: 126vh; bottom: -9px; height: 60px; width: 60px; background-color: #FFE4E0; border-radius: 40%;
                                    border:3.5px solid #ffb5a7; text-align: center; padding-top: 2px; position: absolute;
                                    font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 30px;">
                                        <i class="fi fi-rr-camera" style="font-size: 35px; color: #ffb5a7;"></i>
                                    </label>
                        <input ref="fileInput" type="file" id="uploadHotplace" name="file" accept=".jpg, .png, .jpeg"
                            value="" style="display: none" @change="handleFileUpload" />
                    </div>
                </div>
                <div>


                    <input type="hidden" class="form-control" id="board-write-title"
                        placeholder="ex) 12월 3박4일 제주 바다 보러갈 동행 3명 구해요" name="board-write-title" ref="userid" />

                    <input type="text" class="form-control" id="board-write-title"
                        placeholder="ex) 6월 1일 제주에서 카풀 할 동행 3명 구해요" name="board-write-title" ref="subject" />
                </div>
                <div style="height: 40px"></div>
                <div class="mb-3">

                    <textarea style="height: 450px;" class="form-control" rows="5" id="board-write-content"
                        name="board-write-content" placeholder="1. 현재 동행이 있나요? 
            ex) 혼자에요 / 동행 1명이 있어요 
     
    
    2.어떤 동행을 찾고 있나요? 
            ex) 맛집 탐방을 좋아하는 20대 여성 동행을 찾아요!
            ex) 같이 여행지까지 타고 갈 동행 찾아요!! 


    3. 원하는 여행 코스가 있다면 알려주세요!
        (1000자 이내) " ref="content"></textarea>
                </div>

                <div class="mb-3" style="margin-top: 7px;">
                    <button class="btn-go me-2" @click="movepartnerList"
                        style="width: calc(50% - 8px);">취소</button>
                    <!-- <button class="btn btn-outline-secondary" style="width: calc(50% - 8px);" @click="showModal">작성완료</button> -->
                    <button @click="showModal" class="btn-go me-2" style="width: calc(50% - 8px);">작성완료</button>
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
                                <i class="fi fi-rr-key ps-3 fs">여행 키워드 선택하기(1/2)</i>
                                <p class="sc-a1969d70-4 jXoitQ">어떤 여행을 계획하고 있나요?</p>
                                <p class="sc-a1969d70-5 eNYpGj"><span style="color: red;">* 최소 1개~ 최대 3개</span>의
                                    키워드를 선택해주세요.</p>
                            </h4>
                        </div>
                    </div>
                </div>
            </template>
            <div class="container" style="height:70vh; ">

                <div class="row">
                    <div class="col-md-6">
                        <button v-for="button in buttons" :key="button.id" :sid="button.id" class="btn mb-2"
                            :class="{ 'btn-success': button.active }" style="width: 100%; height: 40%;"
                            @click="toggleButton(button)">
                            <div>
                                <img :src="button.imageSrc" class="sc-a1969d70-8 kmIVho">
                                <span>{{ button.label }}</span>
                            </div>
                        </button>
                    </div>
                    <div class="col-md-6">
                        <button v-for="button in buttons2" :key="button.id" :id="button.id" class="btn mb-2"
                            :class="{ 'btn-success': button.active }" style="width: 100%; height: 40%;"
                            @click="toggleButton(button)">
                            <div>
                                <img :src="button.imageSrc" class="sc-a1969d70-8 kmIVho">
                                <span>{{ button.label }}</span>
                            </div>
                        </button>
                    </div>
                </div>
            </div>
            <template #modal-footer="{ hide }">
                <button type="button" @click="hide"
                    style="width: calc(50% - 15px); background-color: #d3d3d3; height: 60px;" id="btn-login"
                    class="btn">취소</button>
                <b-button v-b-modal.modal-scrollable2
                    style="width: calc(50% - 15px); height: 60px; background-color: #79CF9F;">다음</b-button>


            </template>
        </b-modal>

        <b-modal id="modal-scrollable2" size="lg" scrollable title="">

            <template #modal-header>
                <div class="container">
                    <!-- <button type="button" class="btn-close" data-bs-dismiss="modal" style="float: right;"></button> -->
                    <div class="row">
                        <div class="d-flex justify-content-center">
                            <h4 class="modal-title" style="text-align: center;">
                                <i class="fi fi-rr-key ps-3 fs">여행 키워드 선택하기(2/2)</i>
                                <p class="sc-a1969d70-4 jXoitQ">나는 어떤 여행자인가요?</p>
                                <p class="sc-a1969d70-5 eNYpGj"><span style="color: red;">* 최소 1개~ 최대 3개</span>의
                                    키워드를 선택해주세요.</p>
                            </h4>
                        </div>
                    </div>
                </div>
            </template>
            <div class="container" style="height:70vh;">

                <div class="container">

                    <div class="row">
                        <div class="col">
                            <button v-for="button in buttons3" :key="button.id" :id="button.id" class="btn mb-2"
                                :class="{ 'btn-success': button.active }" style="width: 100%; height: 40%;"
                                @click="toggleButton2(button)">
                                <div>
                                    <img :src="button.imageSrc">
                                    <span>{{ button.label }}</span>
                                </div>
                            </button>
                        </div>

                    </div>
                </div>
            </div>
            <template #modal-footer="{ hide }">
                <button type="button" @click="hide"
                    style="width: calc(50% - 15px); background-color: #d3d3d3; height: 60px;" id="btn-login"
                    class="btn">이전</button>
                <button type="button" style="width: calc(50% - 15px); height: 60px; background-color: #79CF9F;" class="btn"
                    @click="registArticle">작성완료</button>


            </template>
        </b-modal>




    </div>
</template>


<script>
import router from '@/router';
import axios from "axios";

import { mapState } from "vuex";
// import $ from 'jquery';

const memberStore = "memberStore";
export default {
    name: "PartnerWrite",
    components: {

    },
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    data() {
        return {
            today: new Date().toISOString().split('T')[0],
            isLoading: false,
            selectedTab: '',
            articleno: '',
            modalVisible: false,
            uploadedFile: null,
            value_start: '',
            value_end: '',
            purpose: '',
            recruitment: '',
            articles: [],
            value: '',
            maxSelection: 3,
            selectedButtons: [],
            selectedButtons2: [],
            image: '',

            buttons: [
                {
                    id: "noplan",
                    imageSrc: require('@/assets/img/answer1_img01.png'),
                    label: "무계획",
                    active: false
                },
                {
                    id: "tour",
                    imageSrc: require('@/assets/img/answer1_img02.png'),
                    label: "관광",
                    active: false
                },
                {
                    id: "beach",
                    imageSrc: require("@/assets/img/answer1_img03.png"),
                    label: "바다",
                    active: false
                },
                {
                    id: "hiking",
                    imageSrc: require("@/assets/img/answer1_img04.png"),
                    label: "등산",
                    active: false
                },
                {
                    id: "food",
                    imageSrc: require("@/assets/img/answer1_img05.png"),
                    label: "맛집/카페 탐방",
                    active: false
                }
            ],
            buttons2: [
                {
                    id: "shopping",
                    imageSrc: require('@/assets/img/answer1_img07.png'),
                    label: "쇼핑",
                    active: false
                },
                {
                    id: "driving",
                    imageSrc: require('@/assets/img/answer1_img08.png'),
                    label: "드라이브",
                    active: false
                },
                {
                    id: "concert",
                    imageSrc: require("@/assets/img/answer1_img09.png"),
                    label: "전시회/공연 관람",
                    active: false
                },
                {
                    id: "hotplace",
                    imageSrc: require("@/assets/img/answer1_img06.png"),
                    label: "핫플레이스 탐방",
                    active: false
                },
                {
                    id: "gramping",
                    imageSrc: require("@/assets/img/answer1_img11.png"),
                    label: "글램핑",
                    active: false
                }
            ],
            buttons3: [
                {
                    id: "fun",
                    imageSrc: require('@/assets/img/answer2_img01.png'),
                    label: "즉흥적인",
                    active: false
                },
                {
                    id: "plan",
                    imageSrc: require('@/assets/img/answer2_img02.png'),
                    label: "계획적인",
                    active: false
                },
                {
                    id: "master",
                    imageSrc: require("@/assets/img/answer2_img03.png"),
                    label: "여행고수",
                    active: false
                },
                {
                    id: "first",
                    imageSrc: require("@/assets/img/answer2_img04.png"),
                    label: "여행초보",
                    active: false
                },
                {
                    id: "driver",
                    imageSrc: require("@/assets/img/answer1_img08.png"),
                    label: "드라이버",
                    active: false
                }

            ]
        };
    },
    watch: {
        purpose(newValue) {
            this.saveData();
            console.log(newValue);
        },
        recruitment(newValue) {
            this.saveData();
            console.log(newValue);
        },
        value_start(newValue) {
            this.saveData();
            console.log(newValue);
        },
        value_end(newValue) {
            this.saveData();
            console.log(newValue);
        },
    },
    created() {
        this.image = require('@/assets/img/carousel-1.png');
        // axios.get(`http://localhost:8080/api/trippartner/findarticleno/`)
        //     .then(response => {

        //         this.articleno = response.data;
        //     })
        //     .catch(error => {
        //         console.log(error);
        //     });


    },

    methods: {
        clickFileInput() {
            this.$refs.fileInput.click();
        },
        selectTab(tab) {
            this.selectedTab = tab;
            console.log("내가 선택한 지역은" + this.selectedTab)
        },
        showModal() {
            if (this.purpose && this.recruitment && this.value_start && this.value_end && this.$refs.subject.value && this.$refs.content.value && this.selectedTab) {
                this.modalVisible = true; // 모달 표시 여부 변경
            } else {
                // 필수 입력 항목이 하나 이상 누락되었을 때 처리
                alert("필수 입력 항목을 모두 입력해주세요.");
            }
        },
        handleFileUpload(event) {
            const file = event.target.files[0];
            this.uploadedFile = file;
            console.log(file);
            // 파일을 데이터에 저장하거나 추가적인 처리 로직을 수행할 수 있습니다.
            var image = this.$refs['fileInput'].files[0];
            const url = URL.createObjectURL(image);
            this.image = url;
        },
        saveData() {
            const data = {
                purpose: this.purpose,
                recruitment: this.recruitment,
                startDate: this.value_start,
                endDate: this.value_end
            };

            console.log(data);
        },

        movepartnerList() {

            router.push("/trippartner")
        },
        changeColor(id) {
            const button = document.querySelector("#" + id);
            button.style.backgroundColor = "#3CB371";
        },
        toggleButton(button) {
            if (button.active) {
                button.active = false;
                this.selectedButtons = this.selectedButtons.filter((btn) => btn !== button);
            } else {
                if (this.selectedButtons.length < this.maxSelection) {
                    button.active = true;
                    this.selectedButtons.push(button);
                } else {
                    // 선택 개수가 최대 개수를 넘은 경우
                    const firstSelectedButton = this.selectedButtons[0];
                    firstSelectedButton.active = false;
                    this.selectedButtons.shift();
                    button.active = true;
                    this.selectedButtons.push(button);
                }
            }
        },
        toggleButton2(button) {
            if (button.active) {
                button.active = false;
                this.selectedButtons2 = this.selectedButtons2.filter((btn) => btn !== button);
            } else {
                if (this.selectedButtons2.length < this.maxSelection) {
                    button.active = true;
                    this.selectedButtons2.push(button);
                } else {
                    // 선택 개수가 최대 개수를 넘은 경우
                    const firstSelectedButton2 = this.selectedButtons2[0];
                    firstSelectedButton2.active = false;
                    this.selectedButtons2.shift();
                    button.active = true;
                    this.selectedButtons2.push(button);
                }
            }
        },
        registArticle() {


            const formData = new FormData();
            formData.append('userId', this.userInfo.userId);
            formData.append('location', this.selectedTab);
            formData.append('subject', this.$refs.subject.value);
            formData.append('content', this.$refs.content.value);
            formData.append('partnerObject', this.purpose);
            formData.append('partnerCount', this.recruitment);
            formData.append('startDate', this.value_start);
            formData.append('endDate', this.value_end);
            formData.append('file', this.uploadedFile);

            // console.log(this.userInfo.userId);

            console.log(this.selectedButtons);
            console.log(this.selectedButtons2);

            axios.post('http://localhost:8081/api/trippartner/write', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
                .then(response => {
                    const sendData = {
                        articleNo: response.data.articleNo,
                        keywordOne: this.selectedButtons,
                        keywordTwo: this.selectedButtons2
                    }
                    console.log("sendData는 과연" + sendData.articleNo);
                    console.log("이 글 번호는 맞춰봐 " + response.data.articleNo);
                    axios.post('http://localhost:8081/api/trippartner/write/keyword', sendData, {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    })
                        .then(response2 => {
                            console.log('키워드 결과:', response2.data);
                            alert('글 등록이 완료되었습니다.');
                            this.isLoading = true;
                            router.push(`/trippartner/view/${response.data.articleNo}`).then(() => {
                                location.reload() // 페이지 새로고침
                                this.isLoading = false;
                            })
                            // router.go(`/trippartnerview/${response.data.articleNo}`);


                        })
                        .catch(error => {
                            console.log(error);
                            alert('키워드 등록에 실패하였습니다.');
                        });

                    console.log(response);
                    // alert('동행찾기 글 등록이 완료되었습니다.');

                })
                .catch(error => {
                    console.log(error);
                    alert('동행찾기 글 등록에 실패하였습니다.');
                });
        }

    },
};
</script>

<style scoped>
.spinner-container {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}

input,
select {
    /* margin-top: 4px;  */
    font-family: 'Nixgon, sans-serif';
    font-weight: 600;
    opacity: 80%;
    border-radius: 5px;
    width: 100%;
    height: 45px;
    padding-left: 15px;
    background-color: transparent;
    border: 4px solid #ffb5a7;
    /* float:left; */
    font-size: 15px;
}

textarea {
    margin-top: 4px;
    font-family: 'Nixgon, sans-serif';
    font-weight: 600;
    opacity: 80%;
    border-radius: 5px;
    width: 100%;
    height: 180px;
    padding-left: 15px;
    background-color: transparent;
    border: 4px solid #ffb5a7;
    float: left;
    font-size: 20px;
}

label {
    font-family: 'Nixgon, sans-serif';
    opacity: 60%;
    font-size: 17px;
    font-weight: 600;
}

input:focus {
    outline: 2px solid #85c6d7;
}

textarea:focus {
    outline: 2px solid #85c6d7;
}

.custom-datepicker {
    /* margin-top: 4px;  */
    font-family: 'Nixgon, sans-serif';
    font-weight: 600;
    /* opacity: 80%;  */
    border-radius: 5px;
    /* width:100%;
    height:45px; */
    /* padding-left: 15px; */
    background-color: transparent;
    border: 4px solid #ffb5a7;
    /* float:left; */
    font-size: 14px;
}

a {
    font-family: 'Nixgon, sans-serif';
    font-weight: 600;
    opacity: 80%;
    border-radius: 5px;
    width: 100%;
    height: 45px;
    padding-left: 15px;
    background-color: transparent;
    border: 4px solid #ffb5a7;
    /* float:left; */
    font-size: 15px;
}

.btn-go{
  height: 55px;
  width: 145px;
  background-color: transparent;
  border-radius: 20px; 
  border:4px solid #ffb5a7;
}
</style>