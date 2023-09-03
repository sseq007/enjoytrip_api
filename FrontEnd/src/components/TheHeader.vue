<template>
		<div>
			<!-- [S] navbar -->
			<nav class="navbar navbar-expand-lg fixed-top">
				<div class="container">
					<!-- main logo -->
					<a class="navbar-brand link-primary fw-bold fs-3" href="/main" style="width: 50px"> <img style="width: 100%"
							src="@/assets/img/logo.png" alt="" />
					</a>
					<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
						data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<!-- <li class="nav-item py-2" style="opacity: 80%"><i class="fi fi-rr-marker ps-3"></i>
								<router-link :to="'/triplocation'" class="nav-link active ps-0" aria-current="page"
									style="display: inline">
									<p style="font-family:'Noto Sans KR', sans-serif; opacity: 70%; display: inline">지역별여행지</p>
								</router-link>
							</li> -->
							<li class="nav-item py-2" style="opacity: 80%"><i class="fi fi-rr-calendar ps-3"></i> <router-link
									:to="'/travelplan/list'" class="nav-link active ps-0" aria-current="page"
									style="display: inline">
									<p style="font-family: 'Noto Sans KR', sans-serif; opacity: 70%; display: inline">나의여행계획</p>
								</router-link>
							</li>

							<li class="nav-item py-2" style="opacity: 80%"><i class="fi fi-rr-heart ps-3"></i> <router-link
									class="nav-link active ps-0" aria-current="page" :to="'/hotplace'" style="display: inline">
									<p style="font-family: 'Noto Sans KR', sans-serif; opacity: 70%; display: inline">핫플자랑하기</p>
								</router-link>
							</li>
							<li class="nav-item py-2" style="opacity: 80%"><i class="fi fi-rr-paper-plane ps-3"></i>
								<router-link class="nav-link active ps-0" aria-current="page" :to="'/trippartner'"
									style="display: inline">
									<p style="font-family:'Noto Sans KR', sans-serif; opacity: 70%; display: inline">동행찾기</p>
								</router-link>
							</li>
							<li class="nav-item py-2" style="opacity: 80%"><i class="fi fi-rr-megaphone ps-3"></i> <router-link
									:to="'/notice'" class="nav-link active ps-0" aria-current="page"
									style="display: inline">
									<p style="font-family: 'Noto Sans KR', sans-serif; opacity: 70%; display: inline">공지사항</p>
								</router-link>
							</li>
							<!-- <li class="nav-item py-2" style="opacity: 80%; margin-left: 50vh;" @click="openModal"><i class="fi fi-rr-envelope"></i>
						</li> -->
						</ul>
						<div class="notification-badge" style="margin-right: 43vh; margin-top: 1vh;" v-if="userInfo !== null&&isreadCount!==0">{{ isreadCount}}</div>
						<div style="margin-right: 3vh;" >
							<b-dropdown size="lg" right text="Right align" variant="link" toggle-class="text-decoration-none"
								no-caret scrollable>
								<template #button-content>
									<i class="fi fi-rr-envelope"></i>
								</template>
								<div class="container " >
									<div class="d-flex" style="justify-content: center; ">
										
										<h5 class="mb-4" style="text-align: center; margin-right: 1vh;">
											<strong>쪽지</strong>
										</h5>
										<div class="notification-badge2" v-if="userInfo !== null&&isreadCount!==0">{{ isreadCount}}</div>
									</div>
									<div style="height: 35vh; width: 50vh; overflow-y: auto;">
									<note-list-item v-for="note in notes" :key="note.noteNo" :note="note" ></note-list-item>
									</div>
								</div>
							</b-dropdown>
						</div>
						

						<!-- 로그인이 되어 있지 않다면 -->
						<div id="header_nav_confirm_off" style="display: contents" v-if="userInfo == null">
							<ul class="navbar-nav mb-2 mb-lg-0">
								<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button"
										data-bs-toggle="dropdown" aria-expanded="false"> <i class="fi fi-rr-lock ps-3 fs"></i>
									</a>
									<ul class="dropdown-menu">
										<li><router-link :to="'/user/userLogin'" class="dropdown-item">로그인</router-link></li>
										<li><router-link :to="'/user/userRegister'" class="dropdown-item">회원가입</router-link>
										</li>
									</ul>
								</li>
							</ul>
						</div>

						<!-- 로그인이 되어 있다면 -->
						<div id="header_nav_confirm_on" v-else>
							<ul class="navbar-nav mb-2 mb-lg-0">
								<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button"
										data-bs-toggle="dropdown" aria-expanded="false"> <i class="fi fi-rr-unlock"></i>
									</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="" @click.prevent="onClickLogout">로그아웃</a></li>
										<li><router-link :to="'/user/userPage'" class="dropdown-item">마이 페이지</router-link></li>
									</ul>
								</li>
							</ul>
						</div>
					</div>

				</div>

			</nav>

			<!-- [E] navbar -->
			<div style="height: 77px"></div>
		</div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
import noteListItem from '../components/note/noteListItem.vue';
import axios from "axios";
const memberStore = "memberStore";
export default {
	name: "TheHeader",
	components: {
		noteListItem
	},
	data() {
		return {
			user: {
				userId: null,
				userPw: null,
			},
			modalVisible: false,
			notes: [],
			isreadCount:''
		};
	},
	created() {
		if(this.userInfo != null){
			axios.get(`http://localhost:8080/api/note/list/${this.userInfo.userId}`)
				.then(response => {
					console.log(response.data);
					this.notes = response.data;
				})
				.catch(error => {
					console.log(error);
				});

				axios.get(`http://localhost:8080/api/note/countisread/${this.userInfo.userId}`)
				.then(response => {
					this.isreadCount = response.data;
					console.log("isReadcount"+response.data);
					
				})
				.catch(error => {
					console.log(error);
				});
		}

	},
	computed: {
		...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
		...mapGetters(["checkUserInfo"]),
	},
	methods: {
		openModal() {
			this.modalVisible = true;
		},
		closeModal() {
			this.modalVisible = false;
		},
		...mapActions(memberStore, ["userConfirm", "getUserInfo", "userLogout"]),
		onClickLogout() {
			console.log(this.userInfo.userId);
			this.userLogout(this.userInfo.userId);
			sessionStorage.removeItem("access-token");
			sessionStorage.removeItem("refresh-token");
			if (this.$route.path != "/main") this.$router.push({ name: "home" });
		},
	},
};
</script>

<style scoped>
.notification-badge {
  position: absolute;
  top: 0;
  right: 0;
  background-color: red;
  color: white;
  border-radius: 50%;
  width: 20px;
  height: 20px;
  font-size: 12px;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
}
.notification-badge2 {

  background-color: red;
  color: white;
  border-radius: 50%;
  width: 23px;
  height: 23px;
  font-size: 15px;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
}
.btn-go{
  /* height: 55px; */
  /* width: 145px; */
  background-color: transparent;
  border-radius: 20px; 
  border:4px solid #ffb5a7;
}
</style>

