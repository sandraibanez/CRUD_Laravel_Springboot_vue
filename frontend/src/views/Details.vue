<template>
    <div class="container_mesa_details">
        <div class="gallery">
            <div class="gallery-item" tabindex="0">
                <img :src="'/src/assets/img/' + stateOne.mesa?.photo" class="gallery-image" alt=""> 
                <div class="name">
                    <span class="capacity">
                        Capacity: {{ stateOne.mesa?.capacity }}
                    </span>
                    <div class="cat_name" v-for="cat in stateOne.mesa?.categories">
                        <span>
                            {{ cat.name_category }}
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <form_details :reservations="stateOne.mesa" :key="stateOne.mesa" @send_data="reservation_emit" />
    </div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRoute, useRouter } from 'vue-router'
import card_mesa from '../components/card_mesa.vue';
import Constant from '../Constant';
import form_details from '../components/form_details.vue';
import { useReservationCreate } from '../composables/reservation/useReservation';
// import { useReservationCreate } from '../composables/reservation/useReservation'
export default {
    components: { card_mesa,form_details },
    
    setup() {
        
        const store = useStore();
        const route = useRoute();
        const router = useRouter();
        const id = route.params.id;
        
        let fecha = localStorage.getItem('mesa_fecha');
        let type = localStorage.getItem('mesa_type_reservation');
        console.log(fecha);
        console.log(type);
        store.dispatch(`mesa/${Constant.INITIALIZE_ONE_STATE_MESA}`, id)
        let data =[fecha,type];
        console.log(`Elemento 1: ${data[0]}`);
        console.log(data);
        const stateOne = reactive({
            mesa: computed(() => store.getters["mesa/getOneMesa"]),
            isLoged: store.getters['user/GetIsAuth'],
        })
        
        if (stateOne.isLoged) {
            useReservationCreate(data);
            localStorage.setItem('id_mesa',id);
        }
        console.log('details',id);
        console.log('details',stateOne.mesa);
        const reservation_emit = (data) => {
            console.log(data);
          
            if (stateOne.isLoged) {
                data.mesa_id = id;
                useReservationCreate(data)
                router.go(0)
            } else {
                toaster.info('Please login to make a reservation');
            }
        }
        
        return { stateOne,reservation_emit}
    }
}
</script>

<style lang="scss">
body{
    background-color: #e6bf77;
}
.container_mesa_details {
    max-width: 1em;
    margin-left: 37%;
    margin-top: 3%;

    .gallery {
        display: flex;
    }


    .name {
        color: black;
    }

    .capacity {
        float: left;
        font-weight: bold;
        font-size: 170%;
    }

    .cat_name {
        float: right;
        color: #226764;
        border: 1px solid rgba(233, 238, 239);
        background-color: rgba(233, 238, 239);
        border-radius: 10px;
        margin: 1%;
        font-weight: bold;
        font-size: 170%;
    }

    .visually-hidden {
        position: absolute !important;
        height: 1px;
        width: 1px;
        overflow: hidden;
        clip: rect(1px, 1px, 1px, 1px);
    }

    .gallery-item {
        position: relative;
        margin: 1rem;
        color: #fff;
        cursor: pointer;
    }

    .gallery-image {
        width: 20em;
        height: 20em;
        object-fit: cover;
    }
}
</style>