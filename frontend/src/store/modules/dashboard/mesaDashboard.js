import Constant from '../../../Constant';
import MesaServiceDashboard from "../../../services/dashboard/MesaServiceDashboard"

export const mesaDashboard = {
    namespaced: true,
    mutations: {
        [Constant.INITIALIZE_MESA]: (state, payload) => {
            if (payload) {
                state.mesa = payload;
                console.log(state.mesa);
            }
        },
        [Constant.DELETE_ONE_MESA]: (state, payload) => {
            if (payload) {
                state.mesa = state.mesa.filter((itemMesa) => itemMesa.id !== payload)
            }
        },
        [Constant.INITIALIZE_ONE_STATE_MESA]: (state, payload) => {
            if (payload) {
                state.OneMesa = payload;
                console.log(state.OneMesa);
            }
        },
        [Constant.UPDATE_ONE_MESA]: (state, payload) => {
            if (payload) {
                let index = (state.mesa || []).findIndex((item) => item.id == payload.id);
                if (index !== -1) {
                    state.mesa[index] = payload;
                }
            }
        },
        [Constant.CREATE_ONE_MESA]: (state, payload) => {
            state.mesa.push({ ...payload });
        },
    },//mutations
    actions: {
        [Constant.INITIALIZE_MESA]: async (store) => {
            try {
                const response = await MesaServiceDashboard.GetMesas();
                console.log(response);
                console.log(response.data);
                store.commit(Constant.INITIALIZE_MESA, response.data);
            } catch (error) {
                console.error(error)
            }
        },
        [Constant.DELETE_ONE_MESA]: async (store, payload) => {
            try {
                await MesaServiceDashboard.DeleteMesa(payload.id);
                store.commit(Constant.DELETE_ONE_MESA, payload.id)
            } catch (error) {
                console.error(error)
            }
        },
        [Constant.INITIALIZE_ONE_STATE_MESA]: async (store, payload) => {
            try {
                if (store.state.mesa != undefined) {
                    const index = store.state.mesa.findIndex(object => {
                        return object.id === parseInt(payload);
                    });
                    store.commit(Constant.INITIALIZE_ONE_STATE_MESA, store.state.mesa[index]);
                } else {
                    const response = await MesaServiceDashboard.GetOneMesa(payload);
                    store.commit(Constant.INITIALIZE_ONE_STATE_MESA, response.data.data);
                }
            } catch (error) {
                console.error(error)
            }
        },
        [Constant.UPDATE_ONE_MESA]: async (store, payload) => {
            try {
                console.log('hola');
                // console.log(payload);
                const update ={
                    id: payload.id,
                    name_mesa: payload.name_mesa,
                    capacity: payload.capacity,
                    is_active: payload.is_active,
                    photo: payload.photo,
                    categories: payload.categories
                }
                const response = await MesaServiceDashboard.UpdateOneMesa(update);
                if (response.status == 200) {
                    store.commit(Constant.UPDATE_ONE_MESA, payload);
                }
            } catch (error) {
                console.error(error)
            }
        },
        [Constant.CREATE_ONE_MESA]: async (store, payload) => {
            try {
                payload.is_active = true;
                const response = await MesaServiceDashboard.CreateOneMesa(payload);
                if (response.status == 201) {
                    store.commit(Constant.CREATE_ONE_MESA, response.data.data);
                }
            } catch (error) {
                console.error(error)
            }
        },
    },//actions
    getters: {
        getMesas(state) {
            return state.mesa;
        },
        getOneMesa(state) {
            return state.OneMesa;
        },
    }//getters
}//export