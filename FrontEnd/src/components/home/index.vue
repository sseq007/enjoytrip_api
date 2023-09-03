<template>
  <div class="text-center position-relative">
    <img src="/assets/img/main-trip-intro.png" alt="carousel-background" id="carousel-top" />
    <div class="container" id="carousel-content">
      <div
        id="carouselExampleFade"
        class="carousel slide carousel-fade position-absolute top-50 start-50 translate-middle w-75"
        data-bs-ride="carousel"
      >
        <img src="/assets/img/Transparence.png" class="w-100" alt="" />
        <div class="carousel-inner">
          <div class="carousel-item active" data-bs-interval="3000">
            <img src="/assets/img/carousel-0.png" class="d-block w-100" alt="..." />
          </div>
          <div class="carousel-item" data-bs-interval="3000">
            <img src="/assets/img/carousel-1.png" class="d-block w-100" alt="..." />
          </div>
          <div class="carousel-item" data-bs-interval="3000">
            <img src="/assets/img/carousel-2.png" class="d-block w-100" alt="..." />
          </div>
        </div>
        <!-- <button
                        class="carousel-control-prev"
                        type="button"
                        data-bs-target="#carouselExampleFade"
                        data-bs-slide="prev"
                    >
                        <span
                            class="carousel-control-prev-icon"
                            aria-hidden="true"
                        ></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button
                        class="carousel-control-next"
                        type="button"
                        data-bs-target="#carouselExampleFade"
                        data-bs-slide="next"
                    >
                        <span
                            class="carousel-control-next-icon"
                            aria-hidden="true"
                        ></span>
                        <span class="visually-hidden">Next</span>
                    </button> -->
      </div>
    </div>
    <div style="height: 77px"></div>
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  props: {
    msg: String,
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
