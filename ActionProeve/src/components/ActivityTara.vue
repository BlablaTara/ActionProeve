<script setup lang="ts">
import { computed, onMounted, ref, type Ref } from 'vue'
import axios from 'axios'

const activityNameString = ref('')

//Henter data fra backend, når komponentet er monteret.
onMounted(() => {
  axios
    .get('http://localhost:8080/activity')
    .then((response) => {
      activityNameString.value = response.data.name
    })
    .catch((error) => {
      console.error('Fejl desværre: ', error)
    })
})

defineProps<{
  activityName: string
}>()
</script>

<template>
  <h1>{{ activityName }}</h1>
</template>

<style scoped></style>
