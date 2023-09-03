<template>
  <div class="home">
    <index-preview></index-preview>
  </div>
</template>

<script>
// @ is an alias to /src
import indexPreview from '../components/home/indexPreview.vue';

export default {
  name: 'HomeView',
  components: {
    indexPreview
  }
}
</script>
