<template>
    <h1 class="header_title">
     ~  Categories  ~
    </h1>
    <carouselVue :data="state.categories"/>
</template> 

<script>
// #AA6B39 #AA8239 #2D4671 #226764
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../Constant';
import carouselVue from '../components/carousel.vue';

export default {
    components: { carouselVue },
    setup() {
        const store = useStore();

        store.dispatch(`category/${Constant.INITIALIZE_CATEGORY}`);
        
        const state = reactive({
            categories: computed(() => store.getters['category/GetCategories'])
        });
        console.log(state);

        return { state };
    }
}
</script>

<style lang="scss">
body{
    background-color: #e6bf77;
}
.header_title {
    text-align: center;
    color: #226764;
    font-weight: bold;
    font-size: 3em;
}
</style>