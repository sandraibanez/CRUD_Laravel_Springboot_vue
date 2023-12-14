import { ref } from 'vue';
import ReservationService from '../../services/client/ReservationService';

export const useReservationCreate = (data) => {
    //IMPORT ALERTS 
    console.log('useReservationCreate',data);
    let mesa_id = localStorage.getItem('id_mesa');
    const date2 ={
        "fecha_reserva": data[0],
        "type_reservation":data[1],
        "accepted":false
    }
    // const jsonString = JSON.stringify(date2, null, 2);
    // console.log('useReservationCreate date2',jsonString);
    if (mesa_id != null && data != null){
        const reservation = ref([])
        ReservationService.CreateReservation(date2,mesa_id)
        .then(res => {  })
        .catch(error => console.error(error))
        return reservation;
    }
   
    
};

export const useShowUserReservation = () => {
    const reservationUser = ref([])
    ReservationService.ListReservationUser()
        .then(res => { reservationUser.value = res.data })
        .catch(error => console.error(error))
    return reservationUser;
};

export const useListReservationsOfMesa = (mesa_id) => {
    console.log('useListReservationsOfMesa',mesa_id); 
    const reservationOfMesa = ref([])
    ReservationService.ListReservationsOfMesa(mesa_id)
        .then(res => { reservationOfMesa.value = res.data })
        .catch(error => console.error(error))
    return reservationOfMesa;
};

export const UpdateReservation = (data) => {
    console.log('UpdateReservation',data);
    let data_ = {
        fecha_reserva: data.data_input,
        type_reservation: data.type_reservation
    }
    console.log('UpdateReservation fecha',data_.fecha_reserva);
    let id = data.mesa_id
    const UpdateReservation = ref([])
    ReservationService.UpdateReservation(data_, id)
    // ReservationService.UpdateReservation(id)    
        .then(res => { UpdateReservation.value = res.data })
        .catch(error => console.error('Error al actualizar la reserva:', error));
    return UpdateReservation;
};

export const deleteReservation = (mesa_id) => {
    const deleteReservation = ref([])
    ReservationService.DeleteReservation(mesa_id)
        .then(res => { deleteReservation.value = res.data })
        .catch(error => console.error(error))
    return deleteReservation;
};