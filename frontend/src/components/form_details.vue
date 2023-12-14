<template>
    <input type="radio" :disabled="state.dinner_check" v-model="state.dinner" name="type" value="dinner"> Dinner
    <input type="radio" :disabled="state.launch_check" v-model="state.dinner" name="type" value="launch"> Launch
    <input type="data" v-model="state.data_input" name="type">
    <button @click="send_data()" :disabled="!state.dinner">Reservation</button>
</template>

<script>
import 'v-calendar/dist/style.css'; 
import { createToaster } from "@meforma/vue-toaster";
import { getCurrentInstance } from 'vue';
import { reactive } from 'vue';
import { useRoute } from 'vue-router';

export default {
    props: {
        reservations: Object
    },
    emits: {
        send_data: Object
    },
    setup(props) {

        let reserva_id = localStorage.getItem('id_reserv');
        let mesa_id = localStorage.getItem('id_mesa');
        console.log('reserva_id',reserva_id);
        console.log('mesa_id',mesa_id);
        if (reserva_id != null ){
        sessionStorage.removeItem("reservations")
        if (props.reservations.length > 0) {
            sessionStorage.setItem("reservations", JSON.stringify(props.reservations))
        } else {
            reserva_id = JSON.parse(sessionStorage.getItem("reservations"))
        }
        const { emit } = getCurrentInstance();
        const route = useRoute();
        const path = route.fullPath.split('/')
        const toaster = createToaster({ "position": "top-right", "duration": 1500 });
        let same_day = []
        let different_day = []
        if (!reserva_id && path[2] == 'details') {
            reserva_id = [];
        }

        const state = reactive({
            dinner: 0,
            launch: 0,
            dinner_check: 0,
            launch_check: 0,
            data_input: "",
        })
        console.log(state.data_input);

        const send_data = () => {
            let data = {
                data_input: state.data_input,
                type_reservation: state.dinner
            }
            localStorage.removeItem('date')
            emit('send_data', data);
        }
    
        return { state, send_data }
        }
        if (mesa_id != null){

        sessionStorage.removeItem("reservations")
        const { emit } = getCurrentInstance();
        const route = useRoute();
        const path = route.fullPath.split('/')
        const toaster = createToaster({ "position": "top-right", "duration": 1500 });
        let same_day = []
        let different_day = []
        if (!mesa_id && path[2] == 'details') {
            mesa_id = [];
        }

        const state = reactive({
            dinner: 0,
            launch: 0,
            dinner_check: 0,
            launch_check: 0,
            data_input: "",
        })
        console.log(state.data_input);

        const send_data = () => {
            let data = {
                data_input: state.data_input,
                type_reservation: state.dinner
            }
            localStorage.setItem('mesa_fecha',data.data_input)
            localStorage.setItem('mesa_type_reservation',data.type_reservation)
            console.log(data);
            localStorage.removeItem('date')
            emit('send_data', data);
        }
    
        return { state, send_data }
        }

    }

}
</script>