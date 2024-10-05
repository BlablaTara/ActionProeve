<script setup lang="ts">
import { ref } from 'vue';
import MonthCalender from '@/components/MonthCalender.vue';
import DayCalender from '@/components/DayCalender.vue';

//Placeholder for bookings
interface Event {
  title: string;
  startTime: string;
  endTime: string;
}

//Define refs for the selected day and events
const selectedDay = ref<Date | null>(null);
const selectedDayEvents = ref<Event[]>([]);
const isDayViewVisible = ref(false);

//Methods to show and close the day view
function showDayView(day: Date) {
  selectedDay.value = day;
  selectedDayEvents.value = []; // Fetch events for the selected day if needed
  isDayViewVisible.value = true;
  (document.querySelector('.month-view') as HTMLElement).classList.add('blurred');
}

function closeDayView() {
  isDayViewVisible.value = false;
  (document.querySelector('.month-view') as HTMLElement).classList.remove('blurred');
}
</script>

<template>
  <div class="calender-container">
    <div :class="{ blurred: isDayViewVisible }">
      <MonthCalender @day-selected="showDayView" />
    </div>
    <DayCalender
        v-if="isDayViewVisible"
        :day="selectedDay"
        :events="selectedDayEvents"
        :isVisible="isDayViewVisible"
        @close-day-view="closeDayView"
    />
    <div v-if="isDayViewVisible" class="overlay" @click="closeDayView"></div>
  </div>
</template>


<style scoped>
.calender-container {
  display: flex;
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
  height: 100vh; /* Make sure the container takes up the full viewport height */
  position: relative;
  width: fit-content; /* Ensures the container takes only the space needed */
  margin: 0 auto; /* Center the container horizontally */
}

.blurred {
  filter: blur(5px);
}

.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  z-index: 9;
}
</style>