<script setup lang="ts">
import BaseButton from "@/components/BaseButton.vue";
import TimetableColumn from "@/components/TimetableColumn.vue";
import ActivityColumn from "@/components/ActivityColumn.vue";
import { ref, onMounted } from 'vue';
import mockBooking from "../assets/mockBooking.json"; // Mock data for now
import axios from 'axios';

const startHour = 10; // Adjust based on business hours
const endHour = 20;

const bookings = ref([]);
const uniqueActivities = ref([]);

const props = defineProps<{
  day: Date | null;
  isVisible: boolean;
}>();

const emit = defineEmits(['close-day-view']);

const activityColors = {
  "Go-Cart": "rgba(255, 182, 193, 0.7)",
  "Bowling": "rgba(173, 216, 230, 0.7)",
  "Laser Tag": "rgba(144, 238, 144, 0.7)",
};

//Fetch bookings from the backend when the component is mounted
/*onMounted(async () => {
  if (props.day !== null) {
    // Extract the year, month, and date from props.day
    const dayYear = props.day.getFullYear();
    const dayMonth = props.day.getMonth() + 1; // +1 to convert to 1-based month
    const dayDate = props.day.getDate();

    // Format date to YYYY-MM-DD for API request
    const formattedDate = `${dayYear}-${String(dayMonth).padStart(2, '0')}-${String(dayDate).padStart(2, '0')}`;

    // Fetch bookings from the backend
    const { data } = await axios.get(`/api/bookings/day`, {
      params: { date: formattedDate }
    });

    // Get unique activity names
    uniqueActivities.value = [...new Set(bookings.value.map(b => b.activityName))];

    // Store the retrieved bookings in the ref
    bookings.value = data;
  }
});*/


onMounted(() => {
  if (props.day) {
    const dayYear = props.day.getFullYear();
    const dayMonth = props.day.getMonth();
    const dayDate = props.day.getDate();

    bookings.value = mockBooking.filter(booking => {
      const bookingDate = new Date(booking.dateStartTime);
      return (
          bookingDate.getFullYear() === dayYear &&
          bookingDate.getMonth() === dayMonth &&
          bookingDate.getDate() === dayDate
      );
    });

    uniqueActivities.value = [...new Set(bookings.value.map(b => b.activityName))];
  }
});

function closeDayView() {
  emit('close-day-view');
}

function filteredBookings(activity) {
  return bookings.value.filter(booking => booking.activityName === activity);
}
</script>

<template>
  <transition name="slide">
    <div v-if="isVisible" class="day-view">
      <BaseButton text="✖" type="button" class="close-button" @click="closeDayView" />
      <h2 class="centered-date">{{ day?.toDateString() }}</h2>
      <div class="activity-header">
        <div
            v-for="activity in uniqueActivities"
            :key="activity"
            class="activity-name"
        >
          <h3>{{ activity }}</h3>
        </div>
        </div>
        <div class="day-grid">
          <!-- Time labels -->
          <TimetableColumn :startHour="startHour" :endHour="endHour" />
          <!-- Activity Columns -->
          <div class="activity-columns">
            <ActivityColumn
                v-for="activity in uniqueActivities"
                :key="activity"
                :activityName="activity"
                :bookings="filteredBookings(activity)"
                :activityColor="activityColors[activity]"
            />
          </div>
        </div>
    </div>
  </transition>
</template>

<style scoped>
.day-view {
  width: 60%;
  padding: 20px;
  background: #f0f0f0;
  border-left: 1px solid #ccc;
  position: absolute;
  right: 0;
  top: 0;
  height: 100%;
  z-index: 10;
  display: flex;
  flex-direction: column;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 20px;
  cursor: pointer;
}

.centered-date {
  text-align: center;
  font-size: 35px;
  margin-bottom: 20px;
  color: #333333;
}

.activity-header {
  display: flex;
  justify-content: space-around;
  margin-bottom: 10px;
}

.activity-name {
  font-weight: bold;
  color: #333;
}

.day-grid {
  display: grid;
  grid-template-columns: 50px 1fr;
  gap: 10px;
  flex-grow: 1;
}

.activity-columns {
  display: flex;
  flex-grow: 1;
  margin-right: 20px;
}
</style>
