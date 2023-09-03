<template>
  <div style = "position: relative; justify-content: center;">
    <video ref="videoElement" src="/assets/video/lake.mp4" muted autoplay loop
    :style="{ width: videoWidth + 'px', height: videoHeight + 'px' }">
    </video>
    <div class="overlay">
        <div class="aniDiv" :class="{ 'show': showElement }" style="width: 70%; height: 70%; border: 4px solid #ffffff;">
            <h1 ref="title" class="title" style= "display: flex; justify-content: center; align-items: center; flex-direction: column;">
                <span style="color: #85c6d7; align-self: center; margin-top: 9vmin; margin-bottom: 7vmin;">SOLO</span>
                <span style="color: #ffb5a7; margin-bottom: 7vmin;">traveler's</span>
                <span style="color: #e1cfc6;">Guide</span>
                <span style="color: #e1cfc6; margin-top: 14vmin; font-size: 20px; line-height: 0px;
                transform: skew(-12.0002deg, 9deg); letter-spacing: 0px; margin-right: 6vmin" @click="goToMain">go to main</span>
            </h1>            
            
        </div>
    </div>
  </div>
</template>

<script>
import { gsap } from "gsap";

export default {
    data() {
        return {
        videoWidth: 0, // 비디오 가로 너비
        videoHeight: 0, // 비디오 세로 높이
        showElement: false // 초기에는 숨김 상태로 설정
        };
    },
    mounted() {
        this.setVideoSize(); // 초기 비디오 크기 설정
        setTimeout(() => {
          this.showElement = true;
          this.$nextTick(() => {
            this.animateTitle();
          });
        }, 500); // 500ms 후에 애니메이션 효과 시작

        window.addEventListener('resize', this.setVideoSize); // resize 이벤트 리스너 등록
    },
    beforeUnmount() {
        window.removeEventListener('resize', this.setVideoSize); // 컴포넌트 언마운트 시 resize 이벤트 리스너 제거
    },
    methods: {
        setVideoSize() {
        this.videoWidth = window.innerWidth; // 창의 가로 너비로 비디오 가로 너비 설정
        this.videoHeight = window.innerHeight; // 창의 세로 높이로 비디오 세로 높이 설정
        },

        animateTitle() {
            const title = this.$refs.title;
            gsap.from(title.querySelectorAll("span"), {
                duration: 2,
                ease: "back.out(1.7)",
                opacity: 0,
                y: -80,
                stagger: 0.05,
            });
        },
        goToMain(){
            this.$router.push({name: "home"});
        }

    },
};
</script>


<style scoped>

.aniDiv {
  opacity: 0; 
  transition: opacity 2s ease-out; 
}

.aniDiv.show {
  opacity: 1; 
}

h1{
  color: #fff;
  text-transform: uppercase;
  font-size: 120px;
  font-weight: bold;
  line-height: 47px;
  letter-spacing: 2px;
}

@media (max-width: 1200px) {
  h1 {
    font-size: 100px; /* 768px 이하에서의 폰트 사이즈 */
  }
}
@media (max-width: 900px) {
  h1 {
    font-size: 70px; /* 480px 이하에서의 폰트 사이즈 */
  }
}
@media (max-width: 600px) {
  h1 {
    font-size: 50px; /* 480px 이하에서의 폰트 사이즈 */
  }
}

.title{
  transform: translateX(-50%) rotate(-10deg);
  display: block;
  float: left;
  left: 50%;
  position: relative;
}
  
  span {
    transform: skew(-10deg);
    display: block;
    float: left;
    text-shadow: #533d4a 1px 1px, #533d4a 2px 2px, #533d4a 3px 3px, #533d4a 4px 4px, #533d4a 5px 5px, #533d4a 6px 6px;
    min-width: 10px;
    min-height: 10px;
    position: relative;
  }
  
span{
transform: skew(-10deg);
display: block;
float: left;
text-shadow: #533d4a 1px 1px, #533d4a 2px 2px, #533d4a 3px 3px, #533d4a 4px 4px;
}

video {
  object-fit: cover;
  z-index: 1; 
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  z-index: 2; /* overlay를 비디오 위로 배치 */
}
</style>