<template>
  <div class="home">
    <body class="d-flex flex-column min-vh-100">
        <the-header></the-header>
        <!-- <index></index>
        <index2></index2> -->
        <index-practice></index-practice>
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
// import index from '../components/home/index';
// import index2 from '../components/home/index2';
import IndexPractice from '../components/home/indexPractice.vue';

export default {
  name: 'realHomeView',
  components: {
    TheHeader,
    TheFooter,
    // index,
    // index2,
    IndexPractice
  }
}
</script>