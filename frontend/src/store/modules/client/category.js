import Constant from '../../../Constant';
import CategoryService from '../../../services/client/CategoryService';

export const category = {
    namespaced: true,
    actions: {
        [Constant.INITIALIZE_CATEGORY]: async (store) => {
            try {
                const response = await CategoryService.GetCategories();
                // console.log(response);
                store.commit(Constant.INITIALIZE_CATEGORY, response.data);
            } catch (error) {
                console.error(error);
            }
        },

    },//actions
    mutations: {
        [Constant.INITIALIZE_CATEGORY]: (state, payload) => {
            if (payload) {
                // console.log(payload);
                state.categories = payload;
            }
        }
    },//mutations
    getters: {
        GetCategories(state) {
            console.log(state.categories);
            return state.categories;
        }
    }//getters
}//export