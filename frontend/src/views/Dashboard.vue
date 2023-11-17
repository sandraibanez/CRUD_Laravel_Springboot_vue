<template>
  <h1 class="tituloDashboard">~ Dashboard ~</h1>
  <div class="container">
    
    <router-link to="/dashboard/categories"><button class="pulse">List categories</button></router-link>
    <br>
    <router-link to="/dashboard/mesas"><button class="pulse">List mesas</button></router-link>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss">
body{
    background-color: #e6bf77;
}

.pulse:hover,
.pulse:focus {
  animation: pulse 1s;
  box-shadow: 0 0 0 2em transparent;
}

@keyframes pulse {
  0% {
    box-shadow: 0 0 0 0 var(--hover);
  }
}

button.pulse {
  color: var(--color);
  transition: 0.25s;

  &:hover,
  &:focus {
    border-color: var(--hover);
    color: #fff;
  }
}
.container {
  display: flex;
  justify-content: center;
  align-items: center;
}
 .tituloDashboard{
  margin: 2%;
  text-align: center;
  color: #226764;
  font-weight: bold;
  font-size: 3em;
}
button.pulse {
  background: none;
  border: 2px solid;
  font: inherit;
  height: auto;
  line-height: 1;
  margin: 5em;
  padding: 1em 2em;
  border-color: #4daea9;
  background-color: #4daea9;
  color:#2D4671;
  font-weight: bold;
  font-size: 1.5em;
  border-radius: 30px;
}
</style>
