<template>
            <div class="container">
        <div>
            <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 50px" class="m-0 mt-5">핫플 자랑하기</h2>
            <hr class="mb-3 mt-0" align="left" style="border: solid 3px brown; width: 50%" />
        </div>
        <div style="margin: auto; margin-top: 100px; margin-bottom: 100px; width: 50%; height: auto; border: 3px solid #ffb5a7; border-radius: 10px; background-color: #ffe4e0; " class="text-center">
            <nav class="navbar navbar-expand-sm" style="background-color: #ffb5a7; font-family: 'Black Han Sans', sans-serif; opacity: 75%; text-align: center; font-size: 20px">
                <div class="collpase navbar-collapse justify-content-center">
                    <ul class="navbar-nav">
                        <li class="nav-item" style="margin-inline: 70px">
                            핫플레이스 글 수정
                        </li>
                    </ul>
                </div>
            </nav>

            <div id="modifyContents">
                <form action="" style="text-align: left" id="form-write" name="form-write" method="post" encType="multipart/form-data">
                    <div class="m-5">
                        <div class="mb-3 mt-3" align="center" style="position: relative">
                                <div class="hotpleImg mb-3 mt-3">
                                    <img :src="image" class="hotple_image" style="border-radius: 5%; width: 100%; height: 100%;">
                                </div>
                                <div>
                                    <label className="input-file-button" for="uploadHotplace" style="left: 500px; bottom: -30px; height: 60px; width: 60px; background-color: #FFE4E0; border-radius: 40%;
                                    border:3.5px solid #ffb5a7; text-align: center; padding-top: 2px; position: absolute;
                                    font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 30px;">
                                        <i class="fi fi-rr-camera" style="font-size: 35px; color: #ffb5a7;"></i>
                                    </label>
                                    <input ref="image" type="file" id="uploadHotplace" name="file" accept=".jpg, .png, .jpeg" value="" style="display: none" @change="imageChange()"/>
                                </div>                                
                        </div>
                        <div style="display: flex; flex-direction: column; margin-top: 50px; height: 80px;">
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 17px; font-weight: 600;">
                                * 제목
                            </label>
                            <input type="text" v-model="subject" style="margin-top: 4px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 80%; border-radius: 5px;
                            width:100%; height:45px; padding-left: 15px; background-color: transparent;
                            border:4px solid #ffb5a7; float:left; font-size: 20px;" placeholder="Subject"
                            required/>
                        </div>
                        <div style="display: flex; flex-direction: column; margin-top: 15px; height: 170px;">
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 17px; font-weight: 600;">
                                * 내용
                            </label>
                            <textarea v-model="content" style="margin-top: 4px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 80%; border-radius: 5px;
                            width:100%; height:135px; padding-left: 15px; background-color: transparent;
                            border:4px solid #ffb5a7; float:left; font-size: 20px" placeholder="Content"
                            required/>
                        </div>
                        <div style="display: flex; flex-direction: column; margin-top: 15px; height: 80px;">
                            <label style="font-family: 'Nixgon, sans-serif; opacity: 60%; font-size: 17px; font-weight: 600;">
                                * 지역
                            </label>
                            <b-form-select class="select" v-model="sido_code" :options="sidos" style="margin-top: 4px; font-family: 'Nixgon, sans-serif; font-weight:600; opacity: 80%; border-radius: 5px;
                            width:100%; height:45px; padding-left: 15px; background-color: transparent;
                            border:4px solid #ffb5a7; float:left; font-size: 20px; appearance: none; -moz-appearance: none;
                            -webkit-appearance: none;">
                            </b-form-select>
                        </div>
                        <div style="text-align: center; margin-top: 30px;">
                            <button type="button" style="margin-right: 30px; height: 55px; width: 145px; background-color: transparent; border-radius: 20px; 
                            opacity: 100%; border:4px solid #ffb5a7;" id="btn-write" @click="checkValue">
                                <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px;">수정</span>
                            </button>
                            <button type="button" style=" height: 55px; width: 145px; background-color: transparent; border-radius: 20px; 
                            opacity: 100%; border:4px solid #ffb5a7;">
                                <span style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px;">취소</span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { getHotplace, modifyHotplace } from "@/api/hotplace";

const memberStore = "memberStore";
const areaStore = "areaStore";
export default {
    name: "hotplaceWrite",
    components:{

    },
    data(){
        return{
            articleNo: this.$route.params.articleNo,
            subject: '',
            content: '',
            sido_code: null,
            image: '',
        }
    },
    created(){
        this.CLEAR_SIDO_LIST();
        this.getSido();

        getHotplace(
            this.$route.params.articleNo,
            ({data}) => {
                this.hotplace = data;
                this.image = "http://localhost:8081/upload/" + this.hotplace.imageName;
                this.sido_code = this.hotplace.sido_code;
                this.subject = this.hotplace.subject;
                this.content = this.hotplace.content;
            },
            (error) => {
                console.log(error);
            }
        )
    },
    computed: {
        ...mapState(areaStore, ["sidos"]),
        ...mapState(memberStore, ["userInfo"]),
    },
    methods: {
        ...mapActions(areaStore, ["getSido"]),
        ...mapMutations(areaStore, ["CLEAR_SIDO_LIST"]),
        imageChange(){
            var image = this.$refs['image'].files[0];
            const url = URL.createObjectURL(image);
            this.image = url;
            console.log(url);           
        },
        clickFileInput() {
    this.$refs.fileInput.click();
  },
        checkValue(){
            let err = true;
            let msg = "";

            err && !this.subject && ((msg = "제목을 입력해 주세요"), (err = false));
            err && !this.content && ((msg = "내용을 입력해 주세요"), (err = false));

            if(!err) alert(msg);
            else this.registerHotplace();
        },
        registerHotplace(){
            const formData = new FormData();
            formData.append('articleNo', this.articleNo);
            formData.append('userId', this.userInfo.userId);
            formData.append('subject', this.subject);
            formData.append('content', this.content);
            formData.append('sido_code', this.sido_code);
            formData.append('file', this.$refs.image.files[0]);

            for (let key of formData.keys()) {
                console.log(key, ":", formData.get(key));
            }
            
            modifyHotplace(
                formData,
                ({data}) => {
                    let msg = "글 수정을 실패했습니다.";
                    if(data === "success"){
                        msg = "글 수정이 완료되었습니다.";
                    }
                    alert(msg);

                    this.$router.push({ name: "hotplace" });
                },
                (error) => {
                    console.log(error);
                }
            )
        }
    }
}
</script>

<style scoped>
input:focus {outline: 2px solid #85c6d7;}
textarea:focus {outline: 2px solid #85c6d7;}
select:focus {outline: 2px solid #85c6d7;}
.select option{
    background-color: #FFE4E0;
    font-family: 'Nixgon, sans-serif';
    font-weight:600;
    color: #939393;
}
</style>