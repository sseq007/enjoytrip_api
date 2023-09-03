
<template>
    <b-dropdown-item style="  padding: 1vh;  margin-bottom: 1vh;">


        <div class="row mb-4" @click="navigateToPage">

            <div class="col-2">
                <img  :src="profileImage" class="profile_image"
                    style=" border-radius: 40%; width: 6vh; margin-right: 8px; ">

            </div>
            <div class="col-10">
                <div class="row">
                    <div class="d-flex" style="justify-content: space-between;">
                        <div class="mb-1" :class="{ 'text-blur': note.read }"><strong> {{ note.fromuserId }}</strong>
                        </div>
                        <div :class="{ 'text-blur': note.read }"><strong>{{ note.registerTime.substring(0, 10) }} {{
                            note.registerTime.substring(11, 16) }}</strong></div>

                    </div>
                    <div :class="{ 'text-blur': note.read }"><strong>{{ note.content.substring(0,28)}}....</strong></div>
                </div>

            </div>


        </div>

    </b-dropdown-item>
</template>
  
  
  
  
  
<script>
import axios from "axios";
// import router from "@/router";
export default {
    name: "noteListItem",
    props: {
        note: Object,
        
    },
    data() {
        return {
            user: [],
            profileImage:''
        }
    },
    created() {
        axios.get(`http://localhost:8080/api/trippartner/user/${this.note.fromuserId}`)
            .then(response => {
                this.user = response.data;
                console.log("user22 정보는" + this.user);
                if(this.user.profileImage == null){
                        this.profileImage = require('@/assets/img/none_profile_image.jpg');
                    }else{
                        this.profileImage = "http://localhost:8081/upload/" + this.user.profileImage;
                    }

            })
            .catch(error => {
                console.log(error);
            });
    },
    methods: {
        navigateToPage() {
            var formData = {
                noteNo: this.note.noteNo,
                fromuserId: this.note.touserId,
                touserId: this.note.fromuserId,
                content: this.note.content,
                isRead: this.note.isRead
            }
            console.log(this.note.noteId);
            axios.put('http://localhost:8080/api/note/updateisread', formData, {

                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    console.log('isReadupdate등록 결과:', response.data);
                    location.href = `/note/user/${this.note.noteNo}`;
                })
                .catch(error => {
                    console.log(error);
                    alert('isRead update 실패하였습니다.');
                });

            // router.push(`/user/note/${this.note.noteNo}`)

        }

    },
};
</script>
  
  
<style scoped>
.unread-note {

    border: 1px solid red;
    border-radius: 1vh;
}
.text-blur {
  opacity: 0.5; /* 텍스트를 흐리게 만들기 위한 opacity 값 설정 */
}
</style>
  
  
  
  
  
  
  
  
  
  