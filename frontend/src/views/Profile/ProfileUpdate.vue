<template>
  <h1>You'r Reservations</h1>
  <div v-if="stateOne.mesas">
    <form_details :reservations="stateOne.mesas" @send_data="reservation_emit" />
  </div>
  <div v-else>
    <span>Table not available</span>
  </div>


</template>

<script>
import form_details from '../../components/form_details.vue'; 
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRoute, useRouter } from 'vue-router'
import Constant from '../../Constant';
import { UpdateReservation } from '../../composables/reservation/useReservation'
export default {
  components: { form_details },
  setup() {
    const store = useStore();
    const route = useRoute();
    const router = useRouter();
    const id = route.params.id;
    localStorage.setItem('id_reserv', id);   
    console.log('profileupdate',id);
    store.dispatch(`mesa/${Constant.INITIALIZE_ONE_STATE_MESA}`, id)

    const stateOne = reactive({
      mesas: computed(() => store.getters["mesa/getOneMesa"])
    })
    console.log('stateOne.mesas.reservations',stateOne.mesas);
    const reservation_emit = (data) => {
      console.log('reservation_emit',data);
      data.mesa_id = id;
      console.log('reservation_emit',data.mesa_id);
      UpdateReservation(data)
      router.push({ name: "profile" })
    }
  
    return { stateOne, reservation_emit }
  }

};
</script>