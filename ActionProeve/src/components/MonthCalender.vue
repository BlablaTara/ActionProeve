<script setup lang="ts">
import { ref, computed } from 'vue';
import BaseButton from "@/components/BaseButton.vue";

//Define the emits for emitting the selected day
const emit = defineEmits(['day-selected']);

//Reactive variables for date management
const currentDate = ref(new Date());
const currentMonth = ref(currentDate.value.getMonth());
const currentYear = ref(currentDate.value.getFullYear());

//Computed property for the current month name
const currentMonthName = computed(() => {
  return new Date(currentYear.value, currentMonth.value).toLocaleString('default', { month: 'long' });
});

// Computed property for the names of the days of the week
const dayNames = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];

//Computed property to get the days in the current month
const daysInMonth = computed(() => {
  const firstDayOfMonth = new Date(currentYear.value, currentMonth.value, 1);
  const lastDayOfMonth = new Date(currentYear.value, currentMonth.value + 1, 0);
  const days = [];

  for (let i = new Date(firstDayOfMonth.getTime()); i <= lastDayOfMonth; i.setDate(i.getDate() + 1)) {
    days.push(new Date(i.getTime())); //Push a new date object to avoid mutation
  }
  return days;
});

// Computed property to calculate the correct week numbers in the year
const weekNumbers = computed(() => {
  const weeks = [];
  let currentWeekNumber = getWeekNumber(daysInMonth.value[daysInMonth.value.findIndex(day => day !== null)]);

  for (let i = 0; i < Math.ceil(daysInMonth.value.length / 7); i++) {
    weeks.push(currentWeekNumber++);
  }

  return weeks;
});

// Helper function to calculate the week number of the year for a given date
function getWeekNumber(date: Date | null): number {
  if (!date) return 0;
  const startDate = new Date(date.getFullYear(), 0, 1);
  const daysDifference = Math.floor((date.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000));
  return Math.ceil((daysDifference + startDate.getDay() + 1) / 7);
}

//Check if the given day is today
function isToday(day: Date) {
  const today = new Date();
  return (
      today.getDate() === day.getDate() &&
      today.getMonth() === day.getMonth() &&
      today.getFullYear() === day.getFullYear()
  );
}

//To previous month
function prevMonth() {
  currentMonth.value--;
  if (currentMonth.value < 0) {
    currentMonth.value = 11;
    currentYear.value--;
  }
}

//To next month
function nextMonth() {
  currentMonth.value++;
  if (currentMonth.value > 11) {
    currentMonth.value = 0;
    currentYear.value++;
  }
}

//Emit the selected day to the parent component
function selectDay(day: Date) {
  emit('day-selected', day);
}
</script>

<template>
  <div class="month-view">
    <div class="calender-header">
      <BaseButton text="<" type="button" @click="prevMonth" />
      <h2>{{currentMonthName}} {{currentYear}}</h2>
      <BaseButton text=">" type="button" @click="nextMonth" />
    </div>

    <div class="calender-day-names">
      <div v-for="(day, index) in dayNames" :key="index" class="calender-day-name">{{ day }}</div>
    </div>

    <!-- Calendar grid -->
    <div class="calender-grid">
      <div v-for="(weekNumber, weekIndex) in weekNumbers" :key="weekIndex" class="week-row">
        <!-- Week number column -->
        <div class="week-number">{{ weekNumber }}</div>

        <!-- Days in week -->
        <div v-for="(day, dayIndex) in daysInMonth.slice(weekIndex * 7, (weekIndex + 1) * 7)" :key="dayIndex" class="calender-day"
             :class="{'current-day': isToday(day), 'empty-day': day === null}" @click="selectDay(day)">
          {{ day ? day.getDate() : '' }}
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.month-view {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100vw;
  height: 100vh;
  padding: 20px;
  box-sizing: border-box;
}

.calender-header {
  display: flex;
  justify-content: space-between;
  width: 80%;
  margin-bottom: 20px;
}

.calender-day-names {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  gap: 10px;
  text-align: center;
  font-weight: bold;
  width: 80%;
}

.calender-grid {
  display: grid;
  grid-template-columns: auto repeat(7, 1fr); /* Week number + days of the week */
  gap: 10px;
  width: 80%;
}

.week-row {
  display: contents;
}

.week-number {
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
  width: 40px;
}

.calender-day {
  width: 80%;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid #ccc;
  cursor: pointer;
}

.empty-day {
  background-color: #f0f0f0;
}

.current-day {
  border: 2px solid black;
}

button {
  background: none;
  color: #1f2023;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: xx-large;
}

button:hover {
  color: #0056b3;
}
</style>
