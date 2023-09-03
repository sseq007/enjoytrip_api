<template>
    <div class="carousel-item ">
        <div class="card mb-3 shadow bg-gray rounded">
            <div class="row">

                <img :src="article.firstimage" class="profile_image" style="border-radius: 1vh; width: 100%; height:45vh;">

                <div style="padding: 4vh;">
                <div style="margin-bottom: 2vh;"><strong style="font-family: 'Black Han Sans', sans-serif; opacity: 75%; font-size: 33px; ">{{ article.title }}</strong></div>

                <div style="font-size: 2vh; margin-bottom: 2vh; font-family: Nixgon, sans-serif; font-weight: bold;">({{ article.addr1 }})</div>
                <div style="font-family: Nixgon, sans-serif;">{{ article.tel }}</div>
            </div>

            </div>
        </div>
    </div>
</template>
  
  
  
  
  
<script>
export default {
    name: "PlanInfoItem",
    props: {
        article: Object
    }
};
</script>
  
<style></style>
  
  
  
  
  
  
  
  
  
  
  
  