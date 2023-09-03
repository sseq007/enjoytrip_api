<template>
  <div class="home">
    <body class="d-flex flex-column min-vh-100">
        <the-header></the-header>
        <router-view></router-view>
        <div style="height: 77px"></div>
    </body>
    <footer class="mt-auto footer" style="position: absolute; width: 100%;">
        <the-footer></the-footer>
    </footer>
  </div>
</template>

<script>
// @ is an alias to /src
import TheHeader from '@/components/TheHeader.vue';
import TheFooter from '@/components/TheFooter.vue';

export default {
  name: 'noticeMain',
  components: {
    TheHeader,
    TheFooter,
  }
}
</script>