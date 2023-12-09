import { ref } from 'vue';
import MesaService from '../../services/client/MesaService';

export const useMesaFilters = (filters = {}) => {
    console.log(filters);
    const mesas = ref([])
    // filtro de only category, only order y category with order
    const filter1 ={
        categories:filters.categories,
        order: filters.order,
        name_mesa: filters.name_mesa,
        capacity: filters.capacity,
        page: filters.page,
        limit: filters.limit
    }
    MesaService.GetMesas(filter1)
        .then(res => { mesas.value = res.data })
        .catch(error => console.error(error))
    const filter3 ={
        capacity: filters.capacity,
        page: filters.page,
        limit: filters.limit
    }
    MesaService.GetMesascapacitycategory(filter3)
        .then(res => { mesas.value = res.data })
        .catch(error => console.error(error))
    // filtro de only capacity y category with capacity
    const filter2 ={
        capacity: filters.capacity,
        categories: filters.categories,
        page: filters.page,
        limit: filters.limit
    }
    MesaService.GetMesascapacity(filter2)
        .then(res => { mesas.value = res.data })
        .catch(error => console.error(error))
    if (filters.capacity == '' &&  filters.categories == '' && filters.name_mesa == '' && filters.order == ''){
    // console.log('hola');
        const filter4 ={
        page: filters.page,
        limit: filters.limit
    }
    MesaService.GetMesasall(filter4)
        .then(res => { mesas.value = res.data })
        .catch(error => console.error(error))
    
    }
    return mesas;
};



export const useMesaPaginate = (filters = {}) => {
    const totalPages = ref(0)
    MesaService.GetMesasPaginate(filters)
        .then(res => {
            const limit = filters.limit ?? 9;
            const total = res.data;
            const pages = Math.ceil(total / limit);
            totalPages.value = pages;
        })
        .catch(error => console.error(error))
    return totalPages;
};

export const useMesaInfinite = (page = 1, limit = 3) => {
    const mesas = ref([])
    MesaService.GetMesasInfinite(page, limit)
        .then(res => mesas.value = res.data)
        .catch(error => console.error(error))
    return mesas;
};
