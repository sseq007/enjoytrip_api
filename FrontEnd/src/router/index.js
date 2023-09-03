import Vue from "vue";
import VueRouter from "vue-router";

import HomeView from "../views/HomeView";
import realHomeView from "../views/realHomeView";

import noticeMain from "../views/noticeView";
import notice from "../components/notice/noticeList";
import noticeWrite from "../components/notice/noticeWrite";
import noticeModify from "../components/notice/noticeModify";
import noticeContent from "../components/notice/noticeContent";

import noteMain from "../views/noteView";
import note from "@/components/note/noteMain";
import noteContent from "@/components/note/noteContent";

import user from "../views/userView";
import userLogin from "@/components/user/userLogin";
import userRegister from "@/components/user/userRegister";
import userPage from "@/components/user/userPage";
import userDelete from "@/components/user/userDelete";

import partnerMain from "../views/partnerView";
import tripPartner from "../components/partner/partnerMain";
import partnerWrite from "../components/partner/partnerWrite";
import partnerContent from "../components/partner/partnerContent";
import partnerModify from "../components/partner/partnerModify";

import hotplaceMain from "../views/hotplaceView";
import hotplace from "../components/hotplace/hotplaceMain";
import hotplaceWrite from "../components/hotplace/hotplaceWrite";
import hotplaceContent from "../components/hotplace/hotplaceContent";
import hotplaceModify from "../components/hotplace/hotplaceModify";

import planMain from "../views/planView";
import travelplanWrite from "../components/travelplan/travelplanWrite";
import travelplanList from "../components/travelplan/travelplanMain";
import travelplanContent from "../components/travelplan/travelplanContent";
import travelplanModify from "../components/travelplan/travelplanModify";

import store from "@/store";

Vue.use(VueRouter);

// https://v3.router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다.");
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    console.log("로그인");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "main",
    component: HomeView,
  },
  {
    path: "/main",
    name: "home",
    component: realHomeView,
  },
  {
    path: "/user",
    name: "user",
    component: user,
    children: [
      {
        path: "userPage",
        name: "myPage",
        beforeEnter: onlyAuthUser,
        component: userPage,
      },
      {
        path: "userDelete",
        name: "userDelete",
        beforeEnter: onlyAuthUser,
        component: userDelete,
      },
      {
        path: "userLogin",
        name: "userLogin",
        component: userLogin,
      },
      {
        path: "userRegister",
        name: "userRegister",
        component: userRegister,
      }
    ]
  },
  {
    path: "/trippartner",
    name: "partnerMain",
    component: partnerMain,
    redirect: "/trippartner",
    children: [
      {
        path: "",
        name: "trippartner",
        component: tripPartner,
      },
      {
        path: "write",
        name: "partnerWrite",
        component: partnerWrite,
      },
      {
        path: "view/:articleNo",
        name: "partnerContent",
        component: partnerContent,
      },
      {
        path: "modfiy/:articleNo",
        name: "partnerModify",
        component: partnerModify,
      },
    ]
  },
  {
    path: "/hotplace",
    name: "hotplaceMain",
    component: hotplaceMain,
    redirect: "/hotplace",
    children: [
      {
        path: "",
        name: "hotplace",
        component: hotplace,
      },
      {
        path: "write",
        name: "hotplaceWrite",
        beforeEnter: onlyAuthUser,
        component: hotplaceWrite,
      },
      {
        path: "modify/:articleNo",
        name: "hotplaceModify",
        beforeEnter: onlyAuthUser,
        component: hotplaceModify,
      },
      {
        path: ":articleNo",
        name: "hotplaceContent",
        component: hotplaceContent,
      },
    ]
  },
  {
    path: "/notice",
    name: "notice",
    component: noticeMain,
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "noticeList",
        component: notice
      },
      {
        path: "write",
        name: "noticeWrite",
        component: noticeWrite,
      },
      {
        path: "view/:articleNo",
        name: "noticeview",
        component: noticeContent,
      },
      {
        path: "modify/:articleNo",
        name: "noticemodify",
        component: noticeModify,
      },
    ]
  },
  {
    path: "/note",
    name: "noteMain",
    component: noteMain,
    redirect: "/note/list",
    children: [
      {
        path: "list",
        name: "noteList",
        component: note,
      },
      {
        path: "user/:noteNo",
        name: "noteView",
        component: noteContent,
      },
    ]
  },
  {
    path: "/travelplan",
    name: "travelplanMain",
    component: planMain,
    redirect: "travelplan/list",
    children: [
      {
        path: "list",
        name: "travelplanList",
        component: travelplanList,
      },
      {
        path: "write",
        name: "travelplanWrite",
        component: travelplanWrite,
      },
      {
        path: "view/:articleNo",
        name: "travelplanview",
        component: travelplanContent, // 경로 수정
      },
      {
        path: "modify/:articleNo",
        name: "travelplanModify",
        component: travelplanModify, // 경로 수정
      },
    ]
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
