<template>
    <div ref="onepage" id="onepage">
		  <section ref="section1" class="s s1">
        <div>
          <div style="float: left; width: 92%; height: 92vmin; position: relative">
            <transition-group name="fade" tag="div" class="img-container">
              <div v-if="showImage1" class="imgDiv" style="position: absolute; margin-left: 15vmin; margin-top: 6vmin;" :key="'image1'">
                <transition name="fade">
                  <img src="/assets/img/sunrise.jpg" style="width: 100%; height: 100%; object-fit: cover; border-radius: 8%;" />
                </transition>
              </div>
              <div v-if="showImage2" class="imgDiv" style="position: absolute; margin-left: 67vmin; margin-top: 31vmin;" :key="'image2'">
                <transition name="fade">
                  <img src="/assets/img/pinkSea.jpg" style="width: 100%; height: 100%; object-fit: cover; border-radius: 8%;" />
                </transition>
              </div>
            </transition-group>
          </div>
          <div style="float: right; background-color: #e1cfc6; width: 8%; height: 92vmin; display: flex; justify-content: center;">
            <span style="writing-mode: vertical-lr; text-transform: uppercase; font-size: 35px;
            font-weight: bold;">Make Your Travel more Convenient</span>
          </div>
        </div>
      </section>
      <section ref="section2" class="s s2">
        <div>
          <div style="float: left; left: 0; background-color: #ede6db; width: 8%; height: 92vmin; display: flex; justify-content: center;">
            <span style="writing-mode: vertical-lr; text-transform: uppercase; font-size: 35px; color: #000000; transform: rotate(180deg);
            font-weight: bold;">Hello Stranger</span>
          </div>
          <div style="float: right; width: 92%; height: 92vmin; position: relative">
            <transition-group name="fade" tag="div" class="img-container">
              <div class="sec2Div" style="position: absolute; margin-left: 20vmin; margin-top: 5vmin; border-width: 4px 0px 0px 4px;" :key="'sec2image1'">
                <transition name="fade">
                  <img src="/assets/img/section_2_02.jpg" style="width: 100%; height: 100%; object-fit: cover; border-radius: 8%;" />
                </transition>
              </div>
              <div class="sec2Div" style="position: absolute; margin-left: 100vmin; margin-top: 30vmin; border-width: 4px 4px 4px 0px;" :key="'sec2image2'">
                <transition name="fade">
                  <img src="/assets/img/section_2_01.jpg" style="width: 100%; height: 100%; object-fit: cover; border-radius: 8%;" />
                </transition>
              </div>
              <div class="sec2Div" style="position: absolute; margin-left: 20vmin; margin-top: 52vmin; border-width: 0px 0px 4px 4px;" :key="'sec2image2'">
                <transition name="fade">
                  <img src="/assets/img/section_2_03.jpg" style="width: 100%; height: 100%; object-fit: cover; border-radius: 8%;" />
                </transition>
              </div>
            </transition-group>
          </div>
        </div>
      </section>
      <section ref="section3" class="s s3">
        <div>
          <div style="float: left; width: 92%; height: 92vmin; position: relative">
            <transition-group name="fade" tag="div" class="img-container">
              <div class="sec3Div" style="display: flex; flex-direction: column; justify-content: space-between; position: absolute; margin-left: 10vmin; margin-top: 15vmin;" :key="'sec2image1'">
                <div class="sec3-div">
                  <transition name="fade">
                    <img src="/assets/img/section_3_02.jpg" style="width: 100%; height: 100%; object-fit: cover; border-radius: 8%;" />
                  </transition>
                </div>
                <div>
                  <label style="font-family: 'Nixgon, sans-serif; color: #000000; opacity: 80%; font-size: 15px; font-weight: bold; width: 100%; word-wrap: break-word;">
                    당신이 해 왔던 여행은 어떤 여행인가요?
                    <br>지금까지의 추억을 저장해 주세요.
                  </label>
                  <label style="margin-top: 8vmin; font-family: 'Nixgon, sans-serif; color: #000000; opacity: 100%; font-size: 25px; font-weight: bold; width: 100%; word-wrap: break-word;">
                    여행 공유하러 가기
                  </label>
                </div>
                <div>
                  <button type="button" style=" height: 10vmin; width: 35vmin; background-color: transparent; border-radius: 20px; 
                  opacity: 60%; border:4px solid #000000;" @click="goHotplace">
                    <span style="font-family: 'Nixgon, sans-serif; color: #000000; font-weight:600; font-size: 25px;" >더 알아보기</span>
                  </button>
                </div>
              </div>
              <div class="sec3Div" style="display: flex; flex-direction: column; justify-content: space-between; position: absolute; margin-left: 61vmin; margin-top: 15vmin;" :key="'sec2image2'">
                <div class="sec3-div">
                  <transition name="fade">
                    <img src="/assets/img/section_3_01.jpg" style="width: 100%; height: 100%; object-fit: cover; border-radius: 8%;" />
                  </transition>
                </div>
                <div>
                  <label style="font-family: 'Nixgon, sans-serif; color: #000000; opacity: 80%; font-size: 15px; font-weight: bold; width: 100%; word-wrap: break-word;">
                    지금까지의 여행을 이을 새로운 여행을 만들어요.
                    <br>앞으로의 계획을 나눠 주세요.
                  </label>
                  <label style="margin-top: 8vmin; font-family: 'Nixgon, sans-serif; color: #000000; opacity: 100%; font-size: 25px; font-weight: bold; width: 100%; word-wrap: break-word;">
                    여행 계획하러 가기
                  </label>
                </div>
                <div>
                  <button type="button" style=" height: 10vmin; width: 35vmin; background-color: transparent; border-radius: 20px; 
                  opacity: 60%; border:4px solid #000000;" @click="goPlan">
                    <span style="font-family: 'Nixgon, sans-serif; color: #000000; font-weight:600; font-size: 25px;" >더 알아보기</span>
                  </button>
                </div>
              </div>
              <div class="sec3Div" style="display: flex; flex-direction: column; justify-content: space-between; position: absolute; margin-left: 112vmin; margin-top: 15vmin;" :key="'sec2image2'">
                <div class="sec3-div">
                  <transition name="fade">
                    <img src="/assets/img/section_3_03.jpg" style="width: 100%; height: 100%; object-fit: cover; border-radius: 8%;" />
                  </transition>
                </div>
                <div>
                  <label style="font-family: 'Nixgon, sans-serif; color: #000000; opacity: 80%; font-size: 15px; font-weight: bold; width: 100%; word-wrap: break-word;">
                    혼자보다는 여럿이 좋을 때도 있죠.
                    <br>그런 날을 위한 동행을 구하세요.
                  </label>
                  <label style="margin-top: 8vmin; font-family: 'Nixgon, sans-serif; color: #000000; opacity: 100%; font-size: 25px; font-weight: bold; width: 100%; word-wrap: break-word;">
                    동행 구하러 가기
                  </label>
                </div>
                <div>
                  <button type="button" style=" height: 10vmin; width: 35vmin; background-color: transparent; border-radius: 20px; 
                  opacity: 60%; border:4px solid #000000;" @click="goPartner">
                    <span style="font-family: 'Nixgon, sans-serif; color: #000000; font-weight:600; font-size: 25px;" >더 알아보기</span>
                  </button>
                </div>
              </div>
            </transition-group>
          </div>
          <div style="float: right; background-color: #e1cfc6; width: 8%; height: 92vmin; display: flex; justify-content: center;">
            <span style="writing-mode: vertical-lr; text-transform: uppercase; font-size: 35px;
            font-weight: bold;">Let's have fun together</span>
          </div>
        </div>
      </section>
	</div>
</template>

<script>
export default {
  data() {
    return {
      total_section: 0,
      current_idx: 0,
      screen_h: 0,
      page_h: 0,
      last_y: 0,
      onpage_on: true,
      isScroll: false,
      observer: null,
      showImage1: false,
      showImage2: false,
    };
  },
  mounted() {
    if (this.$refs.onepage) {
      this.init();

      window.addEventListener('resize', this.setHeight);
      document.body.addEventListener('scroll', this.handleScroll);
      document.body.addEventListener('touchmove', this.handleScroll);
      document.body.addEventListener('mousewheel', this.handleScroll);

      this.observer = new IntersectionObserver(this.handleIntersection, {
        threshold: 0.5, // 요소가 50% 이상 보이는 경우 콜백 호출
      });
      this.observer.observe(this.$refs.section1);
    }
    
  },
  methods: {
    init() {
      this.setHeight();

      this.total_section = this.$refs.onepage.querySelectorAll('section').length;
      this.last_y = this.page_h * this.total_section;
    },
    handleIntersection(entries) {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          // 섹션 1이 화면에 보이는 경우
          this.showImage1 = true;

          setTimeout(() => {
            this.showImage2 = true; // 1초 후에 두 번째 이미지 나타내기
          }, 1000);

          this.observer.unobserve(entry.target); // 감시 중지
        }
      });
    },
    handleScroll(event) {
      if (this.last_y > window.pageYOffset && !this.onpage_on) {
        console.log(':: 원페이지 시작 ::');
        this.onpage_on = true;
        this.isScroll = false;
      }

      if (!this.onpage_on) return;

      event.preventDefault();
      event.stopPropagation();
      if (this.isScroll) return;

      this.isScroll = true;
      const direction = event.deltaY > 0 ? 1 : -1;

      let y = 0;
      if (direction === -1) {
        this.current_idx--;
        if (this.current_idx < 0) {
          this.current_idx = -1;
        }
        y = this.current_idx * this.page_h;
      } else {
        this.current_idx++;
        if (this.current_idx > this.total_section) {
          this.current_idx = this.total_section;
          this.onpage_on = false;
          return;
        }

        y = this.current_idx * this.page_h;
      }

      window.scrollTo({ top: y, behavior: 'smooth' });
      setTimeout(() => {
        this.isScroll = false;
      }, 500);
    },
    setHeight() {
      const windowHeight = window.innerHeight; // 창의 높이
      this.screen_h = windowHeight - 76;
      this.page_h = this.screen_h;
      const sections = this.$refs.onepage.getElementsByClassName('s');
      for (let i = 0; i < sections.length; i++) {
        sections[i].style.height = this.page_h + 'px';
      }
    },
    goHotplace(){
      this.$router.push({name: "hotplaceMain"});
    },
    goPlan(){
      this.$router.push({name: "travelplanMain"});
    },
    goPartner(){
      this.$router.push({name: "partnerMain"});
    }
  },
};
</script>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}

html{
    padding:0;
    margin:0;
    }
section{box-sizing:border-box;color:#fff;font-size:24px;text-align:center;}
.s1{background-color:#fcd5ce;}
.s2{background-color:#e1cfc6;}
.s3{background-color:#ede6db;}

.imgDiv{
  width: 50% !important;
  height: 55vmin !important;
  align-items: center;
  overflow: hidden; 
  justify-content: center; 
  border: 4px solid #fff8ec; 
  border-radius: 10%;
  padding: 2vmin;
  display: flex;
}
.sec2Div{
  width: 30% !important;
  height: 35vmin !important;
  align-items: center;
  overflow: hidden; 
  justify-content: center;  
  border-radius: 10%;
  padding: 2vmin;
  display: flex;
  border-color: #fff8ec;
  border-style: dashed;
}

.sec3Div{
  width: 25% !important;
  height: 61vmin !important;
  align-items: center;
  background-color: #fff8ec;
  justify-content: center;  
  border-radius: 5%;
  padding: 2vmin;
  display: flex;
  border-width: 4px;
  border-color: #fff8ec;
  border-style: solid;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.3);
}

.sec3-div{
  width: 100% !important;
  height: 22vmin !important;
  overflow: hidden;
  /* margin-bottom: 24vmin; */
}

.line{
  position: absolute;
  top: 0;
  left: 50%;
  height: 100%;
  border-left: 2px dotted black;
  transform: translateX(-50%);
}
</style>