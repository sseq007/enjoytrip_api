<template>
    <div>
        <div class="modal fade" id="map_modal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
            aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">
                            <i class="fi fi-rr-star" id="map_modal_title" ref="map_modal_title">`title:"positions[i].title"`</i>
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
                                <img
                                    id="map_modal_img"
                                    src="positions[i].image"
                                    alt=""
                                    style="width: 100%"
                                    ref="map_modal_img"
                                />
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
        <div class="container">
            <div>
                <h2 style="font-family: 'Black Han Sans', sans-serif;
                                                                opacity: 75%;
                                                                font-size: 50px;
                                                            " class="m-0 mt-5">
                    관광 명소 검색
                </h2>
                <hr class="mb-3 mt-0" align="left" style="border: solid 3px brown; width: 50%" />
            </div>
            <!-- [S] 관광지 검색 -->
            <form class="d-flex my-3" @submit="search" role="search">
                <!-- <select id="search-area" class="form-select me-2">
                    <option value="0" selected>검색 할 지역 선택</option>
                </select> -->
                <select id="search-area" class="form-select me-2" @change="handleAreaChange">
                    <option value="0" selected>검색 할 지역 선택</option>
                    <option v-for="area in areas" :value="area.code" :key="area.code">{{ area.name }}</option>
                </select>
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
                <input id="search-keyword" class="form-control me-2" type="search" placeholder="검색어" aria-label="검색어" />
                <button id="btn-search" class="btn btn-outline-success" type="button" @click="search">
                    검색
                </button>
            </form>
            <!-- kakao map start -->
            <div id="map" style="width: 100%; height: 400px"></div>
            <!-- kakao map end -->
            <div class="mt-5" style="background-color: #ffffffb0; width: 100%">



                <table class="table table-striped" v-show="showTable">
                    <thead>
                        <tr>
                            <th>대표이미지</th>
                            <th>관광지명</th>
                            <th>주소</th>
                            <th class="table_riches">위도</th>
                            <th class="table_riches">경도</th>
                        </tr>
                    </thead>
                    <tbody id="trip-list">
                        <tr v-for="area in trips" :key="area.title" @click="moveCenter(area.mapy, area.mapx)">
                            <td><img :src="area.firstimage" width="100px"></td>
                            <td>{{ area.title }}</td>
                            <td>{{ area.addr1 }} {{ area.addr2 }}</td>
                            <td class="table_riches">{{ area.mapy }}</td>
                            <td class="table_riches">{{ area.mapx }}</td>
                        </tr>
                    </tbody>

                </table>


            </div>
            <!-- [E] 관광지 검색 -->

            <div id="nav_blank" style="height: 77px"></div>
        </div>


    </div>
</template>

<script>
// import axios from "axios";
// import locationJS from "@/assets/js/location.js"
export default {
    name: "LocationView",
    data() {
        return {
            contentTypeId: 0,
            serviceKey: "lFRXRzaJlF6qZGI0Tx1HIXfLfwHwxUJs%2F5A375BAuh3QwK%2BWqFcxTjr4rIdc0o5uDqny0%2BPkTx6r3fetGeb3ig%3D%3D", // Set your service key here
            showTable: false,
            trips: [],
            positions: [],
            map: null,
            selectedArea: '0',
            selectedType: '0',
            searchKeyword: '',
            areas: []
        };
    },
    created() {

    },
    mounted() {
        this.map = new window.kakao.maps.Map(document.getElementById("map"), {
            center: new window.kakao.maps.LatLng(37.500613, 127.036431),
            level: 5
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

            fetch(searchUrl)
                .then((response) => response.json())
                .then((data) => 
                    this.makeList(data)
                    
                );
        },
        makeList(data) {
            this.showTable = true;
            this.trips = data.response.body.items.item;
            this.positions = [];

            this.trips.forEach((area) => {
                const markerInfo = {
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

                if (this.contentTypeId !== 0 && this.contentTypeId !== position.contenttypeid) {
                    continue;
                }

                const imageSrc = `/assets/img/marker_${position.contenttypeid}.png`;
                const imageSize = new window.kakao.maps.Size(24, 24);
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


        }

    }


    };

</script>

