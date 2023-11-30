<template>
    <h1 class="header_title">
     ~  Categories  ~
    </h1>
    <carouselVue :data="state.categories" v-if="state.categories" @emitAction="redirectReservation" />
    <br>
    <h1 class="header_title">
            
            ~ Biggest Tables ~
           
    </h1>
    <br>
    <bigestTablesVue :data="state.mesasInfinite" @page="addInfinite" />

</template> 

<script>
// #AA6B39 #AA8239 #2D4671 #226764
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import Constant from '../Constant';
import carouselVue from '../components/carousel.vue';
import bigestTablesVue from '../components/bigestTables.vue';
import { useMesaInfinite } from '../composables/mesas/useMesa';
export default {
    components: { carouselVue, bigestTablesVue },
    setup() { 
        const store = useStore();
        const router = useRouter();

        store.dispatch(`category/${Constant.INITIALIZE_CATEGORY}`);
        
        const state = reactive({
            categories: computed(() => store.getters['category/GetCategories']),
            mesasInfinite: useMesaInfinite(1, 4),
        });
        
        const redirectReservation = (item) => {
            
            console.log(item);
            const filters = {
                categories: [item.name_category],
                page: 1,
                limit: 9,
            };
            const filters_ = btoa(JSON.stringify(filters));
            router.push({ name: "reservationFilters", params: { filters: filters_ } });
        }
        const addInfinite = (page) => {
            state.mesasInfinite = useMesaInfinite(page, 4);
        }

        return { state, redirectReservation, addInfinite };
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
    margin: 2%;
}
</style>