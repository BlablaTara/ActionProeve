<script setup lang="ts">
import ActivitySection from '../components/ActivitySection.vue'
import activitiesData from '@/assets/activities1.json'
import AdventureHeader from '@/components/AdventureHeader.vue'
import { nextTick, onMounted, ref } from 'vue'
import axios from 'axios'
import '@/assets/IndexMain.css'

// Definerrer type for Activity
interface ActivityType {
  activityName: string
  durations: string[]
  information: string
  image: string
}

const activities = ref<ActivityType[]>([])

onMounted(async () => {
  activities.value = activitiesData as ActivityType[]

  await nextTick()

  const activityElements = document.querySelectorAll('.activity')

  const observer = new IntersectionObserver((entries) => {
    entries.forEach((entry) => {
      if (entry.isIntersecting) {
        entry.target.classList.add('slide-in')
      } else {
        entry.target.classList.remove('slide-in')
      }
    })
  })

  activityElements.forEach((activity) => {
    observer.observe(activity)
  })
})

//Henter data fra backend, når komponentet er monteret.
/*onMounted(() => {
  axios.get('http://localhost:8080/activities')
      .then(response => {
        activities.value = response.data;
      })
      .catch(error =>{
        console.error('Fejl desværre: ', error)
      });
})

 */
</script>

<template>
  <AdventureHeader />

  <div id="{{activityName.toLowerCase()}}" class="activities-container">
    <h1>💥 Activities 💥</h1>
    <div class="activities-list">
      <ActivitySection
        class="activity"
        v-for="(activity, index) in activities"
        :key="index"
        :activity="activity"
        :isReversed="index % 2 === 1"
        :class="{ activity: true, 'slide-in-right': index % 2 === 1 }"
      />
    </div>
  </div>
  <!--<RouterView /> -->
</template>

<style scoped>
@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    width: 100%;
  }
}

.activities-container {
  display: flex;
  flex-direction: column;
  gap: 40px;
}

.activity {
  opacity: 0;
  transform: translateX(-100px);
  transition: all 0.5s ease-in-out;
}

.activity.slide-in-right {
  opacity: 1;
  transform: translateX(100px); /* Slider fra højre */
}

.activity.slide-in {
  opacity: 1;
  transform: translateX(0);
}
</style>
