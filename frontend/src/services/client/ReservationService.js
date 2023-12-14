import Api from "../Api";
import secrets from "../../secrets";

export default {

    ListReservationUser() {
        return Api(secrets.URL_SPRING).get(`listReservation`);
    },//ListReservationUser

    ListReservationsOfMesa(mesa_id) {
        console.log('ListReservationsOfMesa',mesa_id);
        return Api(secrets.URL_SPRING).get(`listReservation/${mesa_id}`);
    },//ListReservationsOfMesa

    CreateReservation(data,mesa_id) {
        console.log('CreateReservation',data);
        console.log('CreateReservation',data[3]);
        return Api(secrets.URL_SPRING).post(`reservation/${mesa_id}`, data);
    },//CreateReservation

    UpdateReservation(data, id) {
        console.log('UpdateReservation',data);
        return Api(secrets.URL_SPRING).put(`reservation/${id}`, data);
    },//UpdateReservation

    DeleteReservation(mesa_id) {
        return Api(secrets.URL_SPRING).delete(`reservation/${mesa_id}`);
    },//DeleteReservation

}//export