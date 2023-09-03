<template>
        <div class="container">
                <div>
                    <h2 style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 50px"
                        class="m-0 mt-5">
                        핫플 자랑하기
                    </h2>
                    <hr class="mb-3 mt-0" align="left" style="border: solid 3px brown; width: 50%" />
                </div>
                
                <div style="margin-bottom: 5%;">
                    <div style="display: flex; justify-content: space-between;">
                    <div><p style="font-family: 'Noto Sans KR', sans-serif; opacity: 75%">다녀왔던 여행지 중 좋았던 곳의 사진을 보여 주세요!</p></div>
                    <div v-if="userInfo != null" style="float: left;"><button type="button" class="btn-go mb-3" style="font-family: 'Nixgon, sans-serif; color: #ffb5a7; font-weight:600; font-size: 25px; text-decoration-line: none"  @click="moveWrite()">글쓰기</button></div>
                    </div>            
                    <div style="display: flex; flex-wrap: wrap;">
                        <hotplace-list-item v-for="hotplace in hotplaces" :key="hotplace.articleNo" :hotplace="hotplace">
                        </hotplace-list-item>
                    </div>
                </div>
        </div>
</template>

<script>
import HotplaceListItem from './hotplaceListItem.vue';
import { listHotplace } from '@/api/hotplace';
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
    name: "hotplaceList",
    components: {
        HotplaceListItem
    },
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    data(){
        return{
            hotplaces: [],
        }
    },
    created(){
        listHotplace(
            ({data}) => {
                this.hotplaces = data;
            },
            (error) => {
                console.log(error);
            }
        );
    },
    methods:{
        moveWrite(){
            this.$router.push({name: "hotplaceWrite"});
        },
    }
}
</script>

<style scoped>
.btn-go{
  height: 55px;
  width: 145px;
  background-color: transparent;
  border-radius: 20px; 
  border:4px solid #ffb5a7;
}
</style>