<template>
    <div class="container_gallery" v-if="state.mesas.length > 0">
        <div class="gallery">
            <card_mesa v-for="mesa in state.mesas" :key="mesa.id" :mesa="mesa" />
        </div>
    </div>
    <div v-else>
        <span>No tables</span>
    </div>
</template>

<script>
import { reactive, computed } from 'vue'
// import { useStore } from 'vuex'
import { useRoute } from 'vue-router'
// import Constant from '../Constant';
import { useMesaFilters } from '../composables/mesas/useMesa';
import card_mesa from '../components/card_mesa.vue';



console.log('hola');
export default {
    components: { card_mesa },
    setup() {
    //     // const store = useStore();
    //     const route = useRoute();
    //     // const router = useRouter();
    //     // store.dispatch(`mesa/${Constant.INITIALIZE_MESA}`)

    //     // const state = reactive({
    //     //     mesas: computed(() => store.getters["mesa/getMesas"])
    //     // })
    //     // console.log(state);

    const route = useRoute();
    

    let filters_URL = {
        categories: [],
    };
    console.log(filters_URL);
    try {
        if (route.params.filters !== '') {
            filters_URL = JSON.parse(atob(route.params.filters));
        }
    } catch (error) {}

    const state = reactive({
        mesas: useMesaFilters(filters_URL)
    });
    console.log(state.mesas);

    return { state, card_mesa }
    }
}
</script>

<style lang="scss">
// #AA6B39 #AA8239 #2D4671 #226764
body{
    background-color: #e6bf77;
}
.container_gallery {
    max-width: 93.5rem;
    margin: 2.43%;
    padding: 0 2rem;
}

.gallery {
    display: flex;
    flex-wrap: wrap;
    margin: -1rem -1rem;
    padding-bottom: 3rem;
}

@supports (display: grid) {
    .gallery {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(22rem, 1fr));
        grid-gap: 2rem;
    }

}
</style>