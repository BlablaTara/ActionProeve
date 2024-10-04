<script setup lang="ts">
import { ref } from 'vue';
import MonthCalender from '@/components/MonthCalender.vue';
import DayCalender from '@/components/DayCalender.vue';

// Define a type for the event
interface Event {
  title: string;
  startTime: string; // Change to Date if needed
  endTime: string; // Change to Date if needed
}

// Define refs for the selected day and events
const selectedDay = ref<Date | null>(null);
const selectedDayEvents = ref<Event[]>([]);
const isDayViewVisible = ref(false);

// Methods to show and close the day view
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
    <MonthCalender ref="monthView" @day-clicked="showDayView" />
    <DayCalender
        v-if="isDayViewVisible"
        :day="selectedDay"
        :events="selectedDayEvents"
        :is-visible="isDayViewVisible"
        @close-day-view="closeDayView"
     />
    <div v-if="isDayViewVisible" class="overlay" @click="closeDayView"></div>
  </div>
</template>

<style scoped>
.calender-container {
  display: flex;
  flex-direction: row;
  position: relative; /* Make this container relative */
}

.blurred {
  filter: blur(5px); /* Apply blur effect when the day view is visible */
}

.overlay {
  position: fixed; /* Use fixed to cover the entire viewport */
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5); /* Dark semi-transparent background */
  z-index: 9; /* Below DayView but above the month view */
}
</style>