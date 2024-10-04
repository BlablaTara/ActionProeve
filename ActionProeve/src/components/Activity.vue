<script setup lang="ts">

const props = defineProps<{
  activity: {
    activityName: string,
    durations: string[],
    information: string,
    image: string
  }
}>()

</script>

<template>
  <div>
    <h2>{{ props.activity.activityName }}</h2>
    <p>Available Durations: {{ props.activity.durations.join(', ') }}</p>
    <p>{{ props.activity.information }}</p>
    <img :src="props.activity.image" :alt="props.activity.activityName" style="max-width: 200px; height: auto;" />
  </div>
</template>

<style scoped>
.activity {
  margin-bottom: 20px;
}

</style>
