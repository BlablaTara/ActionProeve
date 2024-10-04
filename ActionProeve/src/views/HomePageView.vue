<script setup lang="ts">
import Activity from '../components/Activity.vue'
import AdventureHeader from "@/components/AdventureHeader.vue";
import {onMounted, ref} from "vue";
import axios from "axios";

// Definer en type for Activity
interface Activity {
  activityName: string;
  durations: string[];
  information: string;
  image: string;
}

const activities = ref([])

//Henter data fra backend, når komponentet er monteret.
onMounted(() => {
  axios.get('http://localhost:8080/activities')
      .then(response => {
        activities.value = response.data;
      })
      .catch(error =>{
        console.error('Fejl desværre: ', error)
      });
})

</script>

<template>
  <header>
    <AdventureHeader />
  </header>

  <div class="wrapper">
    <br><br><br><br><br><br><br><br><br><br><br><br><h1>Activities</h1>
    <Activity v-for="activity in activities" :key="Activity.activityName" :activity="activity" />
  </div>

  <!--<RouterView /> -->
</template>

<style scoped>
header {
  width: 100%;
  line-height: 1.5;
  max-height: 100vh;
}


nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}


nav a {
  display: inline-block;
  padding: 0 1rem;
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    width: 100%;
  }


  /*header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }

   */
}
</style>
