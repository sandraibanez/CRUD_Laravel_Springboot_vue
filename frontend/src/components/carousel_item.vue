<template>
    <div class="carusel_item" @click="emitAction()">
        <img :src="item.photo" />
        <p class="name_category_home">{{ item.name_category }}</p>
    </div>
</template>

<script>

import { category } from '../store/modules/client/category';
import { getCurrentInstance } from 'vue';
export default {
    props: {
        item: Object
    },
    emits: {
        emitAction: Object
    },

    setup(props) {
        const { emit } = getCurrentInstance();
        const emitAction = () => {
            emit('emitAction', props.item);
            console.log("carusel_item",props.item);
        }

        return { emitAction }
    }
}
</script>

<style lang="scss">
.carusel_item {
    img {
        width: 30%;
        height: auto;
    }
}
.name_category_home{
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: auto;
    margin: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    color: black;
    font-weight: bold;
    font-size: 3em;
    line-height: 2;
}
</style>