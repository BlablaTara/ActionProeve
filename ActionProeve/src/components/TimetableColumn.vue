<script setup lang="ts">
const props = defineProps<{ startHour: number, endHour: number }>();

const hours = [];
for (let i = props.startHour; i <= props.endHour; i++) {
  hours.push(i);
}
</script>

<template>
  <div class="time-labels">
    <div v-for="hour in hours" :key="hour">{{ hour }}:00</div>
  </div>
</template>

<style scoped>
.time-labels {
  justify-content: space-between;
  text-align: right;
  padding-right: 10px;
  font-weight: bold;
  position: relative;
  height: 80%;
  display: flex;
  flex-direction: column;
}
</style>
