<template>
    <div>
        <div class="modal fade" id="map_modal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
            aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">
                            <i class="fi fi-rr-star" id="map_modal_title"
                                ref="map_modal_title">`title:"positions[i].title"`</i>
                        </h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <!-- Modal Body -->
                    <div class="modal-body">
                        <form action="" class="was-validated">
                            <div class="mb-3 mt-3">
                                <label for="location_address" class="form-label">- 주소:</label>
                                <p id="map_modal_addr" ref="map_modal_addr">`addr:"positions[i].addr"`</p>
                            </div>
                            <div class="mb-3">
                                <label for="location_image" class="form-label">- 위치 이미지:</label>
                                <img id="map_modal_img" src="positions[i].image" alt="" style="width: 100%"
                                    ref="map_modal_img" />
                            </div>
                            <div class="form-check mb-3">
                                <label class="form-check-label">
                                    <input class="form-check-input" type="checkbox" name="remember" />
                                    Remember me
                                </label>
                            </div>
                        </form>
                    </div>

                    <!-- Modal Footer -->
                    <!-- <div class="modal-footer">
                    <button type="submit" class="btn btn-outline-secondary">로그인</button>
                    <button type="button" class="btn btn-outline-secondary">아이디 찾기</button>
                    <button type="button" class="btn btn-outline-secondary">비밀번호 찾기</button>
                </div> -->
                </div>
            </div>
        </div>
        
        <div>
            


            <div class="row" style="  background-color: #ffe4e0; height: 90vh;" >

                <div class="col-md-3">
                    <div style="margin-left: 1vh;">
                        <div style="margin-bottom: 3vh; margin-top: 2vh;">

                            <select id="search-area" class="form-select" @change="handleAreaChange"
                                v-model="selectarea">
                                <option value="0" selected>검색 할 지역 선택</option>
                                <option v-for="area in areas" :value="area.code" :key="area.code">{{ area.name }}
                                </option>
                            </select>

                        </div>

                        <div class="d-flex">
                            <b-form-datepicker id="example-datepicker" v-model="value_start" class="mb-2 custom-datepicker " :min="today"
                                date-format="MM-dd"></b-form-datepicker>

                            <b-form-datepicker id="example-datepicker" v-model="value_end" class="mb-2 custom-datepicker"
                                :min="value_start" date-format="MM-dd"></b-form-datepicker>


                        </div>
                        <form class="d-flex my-3" @submit="search" role="search">

                            <!-- <select id="search-area" class="form-select me-2">
                <option value="0" selected>검색 할 지역 선택</option>
            </select> -->
                            <select id="search-content-id" class="form-select me-2">
                                <option value="0" selected>관광지 유형</option>
                                <option value="12">관광지</option>
                                <option value="14">문화시설</option>
                                <option value="15">축제공연행사</option>
                                <option value="25">여행코스</option>
                                <option value="28">레포츠</option>
                                <option value="32">숙박</option>
                                <option value="38">쇼핑</option>
                                <option value="39">음식점</option>
                            </select>
                            <input id="search-keyword" class="form-control me-2" type="search" placeholder="검색어"
                                aria-label="검색어" style="width: 26vh;" />
                            <button id="btn-search" class="btn btn-outline-success" type="button" @click="search"
                                style="width: 15vh; ">
                                검색
                            </button>
                        </form>

                        <div style="overflow-y: scroll; max-height: 70vh; ">
                            <table class="table table-striped" v-show="showTable">
                                <thead>
                                    <tr>
                                        <th>대표이미지</th>
                                        <th>관광지명</th>
                                        <th>주소</th>
                                        <!-- <th class="table_riches">위도</th>
                            <th class="table_riches">경도</th> -->
                                    </tr>
                                </thead>
                                <tbody id="trip-list">
                                    <tr v-for="area in trips" :key="area.title"
                                        @click="moveCenter(area.mapy, area.mapx)">

                                        <td><img :src="area.firstimage" width="100px"></td>
                                        <td>{{ area.title }}</td>
                                        <td>{{ area.addr1 }} {{ area.addr2 }}</td>
                                        <td><button class="btn btn-secondary" style="width: 7vh;"
                                                @click="saveTrip(area)">추가</button></td>
                                        <!-- <td class="table_riches">{{ area.mapy }}</td>
                            <td class="table_riches">{{ area.mapx }}</td> -->
                                    </tr>
                                </tbody>

                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div id="map" style="width: 100%; height: 100%;"></div>

                </div>


                <div class="col-md-3">
                    <div class="row">
                        <div style="overflow-y: scroll; height: 90vh;">
                            <draggable v-model="savedTrips" @end="onDragEnd">
                                <div v-for="(area, index) in savedTrips" :key="area.title"
                                    class="card mb-3 shadow bg-gray rounded"
                                    style="text-align:center; width: 100%;  height: 16vh; margin-right: 3%; margin-top: 3%; background-color: #ffe4e0;
                box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.3); border: solid 5px #ffb5a7; border-radius: 5%; text-decoration: none;">
                                    <div class="row" style="padding: 1vh;">
                                        <div class="col-4">
                                            <img :src="area.firstimage" class="profile_image"
                                                style="border-radius: 1vh; width: 17vh; height: 13vh;">
                                        </div>
                                        <div class="col-8" style="padding: 1vh;">
                                            <div class="d-flex" style="justify-content: space-between; margin-bottom: 2vh;">
                                                <div style="margin-top: 3px; margin-left: 22px; font-size: 17px;"><strong
                                                        style="margin-right: 8vh;">{{ area.title }}</strong></div>
                                                <button class="btn" @click="removeTrip(index)"><img
                                                        src="@/assets/img/sub.png" style="width: 25px;"></button>

                                            </div>
                                            <div style="margin-right: 15px; font-size: 14px;">{{ area.addr1 }}</div>
                                        </div>

                                    </div>
                                </div>
                            </draggable>

                        </div>
                    </div>




                </div>
                <!-- [S] 관광지 검색 -->

                <!-- kakao map start -->
            </div>

        </div>
        <div class="container" style="height: 77px; margin-bottom: 7vh;">
            <div>
                <h2 style="font-family: 'Black Han Sans', sans-serif;
                                                                                                                                                    opacity: 75%;
                                                                                                                                                    font-size: 50px;
                                                                                                                "
                    class="m-0 mt-5">
                    여행 타임 라인
                </h2>
                <hr class="mb-3 mt-0" align="left" style="border: solid 3px brown; width: 50%" />
            </div>
        </div>
        <div class="container">
            <div class="row">

                <div>
                    <div v-for="(area, index) in savedTrips" :key="area.title"
                        :class="index % 2 === 0 ? 'card-left' : 'card-right'" class="card shadow bg-gray rounded"
                        style="text-align:center; width: 50vh;  height: 320px; margin-right: 3%; margin-top: 3%; background-color: #ffe4e0;
                box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.3); border: solid 5px #ffb5a7; border-radius: 5%; text-decoration: none;">
                        <div class="row" style="padding: 1vh;">
                            <div class="col-4">
                                <img :src="area.firstimage" class="profile_image"
                                    style=" width: 15vh; height: 12vh; border-radius: 1vh;">
                            </div>
                            <div class="col-8" style="padding: 1vh;">
                                <div style="font-size: 28px; margin-bottom: 2vh;">
                                    <div style="margin-bottom: 1vh;"><strong>{{ area.title }}</strong></div>

                                </div>
                                <div>{{ area.addr1 }}</div>
                            </div>
                        </div>
                        <textarea style="height: 20vh;" class="form-control" rows="5" id="board-write-content"
                            name="board-write-content" placeholder="장소 계획을 적어주세요" ref="tel"
                            v-model="savedTrips[index].tel"></textarea>
                    </div>

                </div>

            </div>
            <div style="text-align: end; margin-right: 5vh; margin-top: 3vh;">
                <button class="btn btn-outline-secondary" type="button" @click="movego" style="margin-right: 1vh; font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px; text-decoration-line: none" >취소</button>
                    <button class="btn btn-outline-secondary" type="button" @click="checkValue" style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px; text-decoration-line: none">수정완료</button>
                </div>

        </div>




    </div>
</template>

<script>
// import axios from "axios";
// import locationJS from "@/assets/js/location.js"
import draggable from 'vuedraggable';
import axios from "axios";
import { mapState } from "vuex";
import router from '@/router';
// import $ from 'jquery';
// import router from '@/router';
const memberStore = "memberStore";
export default {
    name: "travelPlanModify",
    components: {
        draggable
    },
    data() {
        return {
            selectarea: '',
            value_start: '',
            value_end: '',
            contentTypeId: 0,
            serviceKey: "lFRXRzaJlF6qZGI0Tx1HIXfLfwHwxUJs%2F5A375BAuh3QwK%2BWqFcxTjr4rIdc0o5uDqny0%2BPkTx6r3fetGeb3ig%3D%3D", // Set your service key here
            showTable: false,
            trips: [],
            positions: [],
            map: null,
            selectedArea: '0',
            selectedType: '0',
            searchKeyword: '',
            areas: [],
            today: new Date().toISOString().split('T')[0],
            selectedTrip: null, // 선택한 여행지 데이터
            savedTrips: [],
            selectedDay: null,
            saveDay: '',
            selectedAreaIndex: null,
            articles: [],
            article:[]
        };
    },
    created() {

        axios.get(`http://localhost:8080/api/travelplan/list/${this.$route.params.articleNo}`)
            .then(response => {
                console.log("여행계회수정DATA"+response.data);
                this.article = response.data;
                this.value_start = this.article.startDate;
                this.value_end = this.article.endDate;
            })
            .catch(error => {
                console.log(error);
            });

        axios.get(`http://localhost:8080/api/travelplan/listinfo/${this.$route.params.articleNo}`)
            .then(response => {
                console.log(response.data);
                this.articles = response.data;
                this.savedTrips=this.articles;
                // this.drawPath();
            })
            .catch(error => {
                console.log(error);
            });
  
    },
    computed: {
        selectedAreaName() {
            const selectedArea = this.areas.find(area => area.code === this.selectarea);
            return selectedArea ? selectedArea.name : '';
        },

        ...mapState(memberStore, ["userInfo"]),
    },


    mounted() {
        this.map = new window.kakao.maps.Map(document.getElementById("map"), {
            center: new window.kakao.maps.LatLng(37.500613, 127.036431),
            level: 13
        });
        const areaUrl =
            "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
            this.serviceKey +
            "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";

        fetch(areaUrl)
            .then((response) => response.json())
            .then((data) => this.makeOption(data));
        console.log(this.data);
    },
    methods: {
        
        checkValue() {
            // 사용자 입력값 체크하기
            // 작성자아이디, 제목, 내용이 없을 경우 각 항목에 맞는 메세지를 출력
            let err = true;
            let msg = "";
            //   !this.$refs.userid.value && ((msg = "작성자 입력해주세요"), (err = false), this.$refs.userid.focus());
            err && this.selectarea == '' && ((msg = "지역 입력해주세요"));
            err && this.value_start == '' && ((msg = "시작일 입력해주세요"));
            err && this.value_end == '' && ((msg = "종료일 입력해주세요"));
            err && this.selectedTrip == null && ((msg = "계획을 입력해주세요"));
            if (!err) alert(msg);
            // 만약, 내용이 다 입력되어 있다면 registArticle 호출
            else this.registPlan();
        },
        registPlan() {
            // 비동기
            // TODO : 글번호에 해당하는 글정보 등록.
            console.log("계획 작성 하러가자!!!!");
            // console.log(this.userInfo.userId);

            var formData = {
                articleNo: this.article.articleNo,
                userId: this.userInfo.userId,
                location: this.selectarea,
                startDate: this.value_start,
                endDate: this.value_end,
                localDto: this.savedTrips
            }

            console.log(formData.localDto)
            axios.put('http://localhost:8081/api/travelplan/modify', formData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    console.log('계획 수정 결과:', response.data);

                    router.push(`/travelplanview/${this.article.articleNo}`);
                })
                .catch(error => {
                    console.log(error);
                    alert('계획 등록에 실패하였습니다.');
                });


        },
        onDragEnd(event) {
            const movedElement = event.item; // 이동된 요소
            const oldIndex = event.oldIndex; // 이전 인덱스
            const newIndex = event.newIndex; // 새로운 인덱스

            // 선택한 요소를 이동한 위치로 조정
            if (movedElement.classList.contains('selected')) {
                const selectedArea = this.savedTrips.splice(oldIndex, 1)[0];
                this.savedTrips.splice(newIndex, 0, selectedArea);
            }
            console.log("이동 후 바뀐 " + this.savedTrips)
        },
        moveAreaToIndex(index) {
            const selectedArea = this.savedTrips.find(area => area.selected);
            if (selectedArea) {
                const currentIndex = this.savedTrips.indexOf(selectedArea);
                if (currentIndex !== index) {
                    this.savedTrips.splice(currentIndex, 1);
                    this.savedTrips.splice(index, 0, selectedArea);
                }
            }
        },
        removeTrip(index) {
            this.savedTrips.splice(index, 1);
        },
        handleOptionChange() {
            console.log('옵션 변경:', this.selectedDay);


            console.log('저장된 여행지 개수:', this.savedTrips);
        },
        saveTrip(area) {

            if (!this.savedTrips.includes(area)) {
                this.savedTrips.push(area);
                console.log('저장된 여행지:', this.savedTrips);

            }


        },
        getDurationInDays(start, end) {
            const startDate = new Date(start);
            const endDate = new Date(end);
            const timeDifference = endDate.getTime() - startDate.getTime();
            const durationInDays = Math.ceil(timeDifference / (1000 * 3600 * 24));
            this.saveDay = durationInDays + 1;
            // this.savedTrips = new Array(durationInDays + 1).fill(0).map(() => new Array());
            return durationInDays + 1;
        },
        search() {
            let searchUrl = `https://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey=${this.serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;

            const areaCode = document.getElementById("search-area").value;
            this.contentTypeId = document.getElementById("search-content-id").value;
            const keyword = document.getElementById("search-keyword").value;

            if (parseInt(areaCode)) searchUrl += `&areaCode=${areaCode}`;
            if (parseInt(this.contentTypeId)) searchUrl += `&contentTypeId=${this.contentTypeId}`;
            if (!keyword) {
                alert("검색어 입력 필수!!!");
                return;
            } else searchUrl += `&keyword=${keyword}`;

            this.map.setLevel(10);
            fetch(searchUrl)
                .then((response) => response.json())
                .then((data) => {

                    this.makeList(data);
                });
        },
        makeList(data) {
            if (!data.response.body.items) {
        alert("검색되는 정보가 없습니다!");
        return;
    }
            this.showTable = true;
            this.trips = data.response.body.items.item;
            this.positions = [];

            this.trips.forEach((area) => {
                if (!area.firstimage) {
                    console.log("이미지가 있어?" + data.firstimage);
                    area.firstimage = '/assets/img/logo.png'; // 원하는 기본 이미지 URL을 여기에 지정하세요
                }
                var markerInfo = {
                    title: area.title,
                    latlng: new window.kakao.maps.LatLng(area.mapy, area.mapx),
                    contenttypeid: area.contenttypeid,
                    addr: area.addr1,
                    image: area.firstimage
                };

                // console.log(markerInfo);
                this.positions.push(markerInfo);
            });

            this.displayMarker();
        },
        moveCenter(lat, lng) {
            this.map.setCenter(new window.kakao.maps.LatLng(lat, lng));
            this.map.setLevel(5);
        },
        makeOption(data) {
            this.areas = data.response.body.items.item;
        },
        handleAreaChange(event) {
            console.log("sido change");
            const selectedValue = event.target.value;
            if (selectedValue) {
                const regcode = selectedValue.substr(0, 2) + "*00000";
                this.sendRequest("gugun", regcode);
            } else {
                this.initOption("gugun");
                this.initOption("dong");
            }
        },
        displayMarker() {
            for (let i = 0; i < this.positions.length; i++) {
                const position = this.positions[i];
                const position_name = position.title;
                const position_address = position.addr;
                const position_image = position.image;

                // if (this.contentTypeId !== 0 && this.contentTypeId !== position.contenttypeid) {
                //     continue;
                // }

                const imageSrc = `/assets/img/location.png`;
                const imageSize = new window.kakao.maps.Size(30, 30);
                const markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize);

                const marker = new window.kakao.maps.Marker({
                    map: this.map,
                    position: position.latlng,
                    title: position.title,
                    image: markerImage
                });

                marker.addListener("click", () => {
                    document.querySelector("#map_modal_title").innerText = position_name;
                    document.querySelector("#map_modal_addr").innerText = position_address;
                    document.querySelector("#map_modal_img").src = position_image;
                    console.log(document.querySelector("#map_modal"));

                    var myModal = new window.bootstrap.Modal(document.getElementById("map_modal"), {
                        keyboard: false,
                    });
                    myModal.toggle();
                });
                this.map.setCenter(this.positions[0].latlng);
            }


        },
        movego(){
            router.push(`/travelplan/view/${this.article.articleNo}`);
        }

    }


};

</script>

<style scoped>
.card-left {
    margin-left: 10vh;
}

.card-right {
    /* CSS styles for right-positioned cards */
    margin-left: 85vh;
}
input,select {
    /* margin-top: 4px;  */
    font-family: 'Nixgon, sans-serif';
    font-weight:600; 
    opacity: 80%; 
    border-radius: 5px;
    width:100%;
    height:45px;
    padding-left: 15px;
    background-color: transparent;
    border:4px solid #ffb5a7;
    /* float:left; */
    font-size: 15px;
  }
  textarea{
    margin-top: 4px; 
    font-family: 'Nixgon, sans-serif';
    font-weight:600; 
    opacity: 80%; 
    border-radius: 5px;
    width:100%;
    height:180px;
    padding-left: 15px;
    background-color: transparent;
    border:4px solid #ffb5a7;
    float:left;
    font-size: 20px;
  }
  label{
    font-family: 'Nixgon, sans-serif'; opacity: 60%; font-size: 17px; font-weight: 600;
  }
  input:focus {outline: 2px solid #85c6d7;}
  textarea:focus {outline: 2px solid #85c6d7;}

  .custom-datepicker {
    /* margin-top: 4px;  */
    font-family: 'Nixgon, sans-serif';
    font-weight:600; 
    /* opacity: 80%;  */
    border-radius: 5px;
    /* width:100%;
    height:45px; */
    /* padding-left: 15px; */
    background-color: transparent;
    border:4px solid #ffb5a7;
    /* float:left; */
    font-size: 14px;
}
</style>
