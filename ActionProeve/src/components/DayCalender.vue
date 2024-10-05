<script setup lang="ts" xmlns="http://www.w3.org/1999/html">
import BaseButton from "@/components/BaseButton.vue";
import { ref, onMounted } from 'vue';
import mockBooking from "../assets/mockBooking.json"; // Import mock data
import axios from 'axios';

interface Booking {
  id: number;
  activityName: string;
  dateStartTime: string;
  endTime: string;
  custName: string;
  custPhoneNum: string;
  guestAmount: number;
}

const bookings = ref<Booking[]>([]);

const uniqueActivities = ref<string[]>([]);

// Color mapping for activities
const activityColors: { [key: string]: string } = {
  "Go-Cart": "rgba(255, 182, 193, 0.7)", // Light pink
  "Bowling": "rgba(173, 216, 230, 0.7)", // Light blue
  "Laser Tag": "rgba(144, 238, 144, 0.7)", // Light green
};

const props = defineProps<{
  day: Date | null;
  isVisible: boolean;
}>();

const emit = defineEmits(['close-day-view']);

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
  if (props.day !== null) {
    // Get the local date string
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

function isWeekend(day: Date | null): boolean {
  if (day === null) {
    return false;  // Return a default value or handle appropriately
  }
  const dayOfWeek = day.getDay();
  return dayOfWeek === 0 || dayOfWeek === 6; // Sunday = 0, Saturday = 6
}

function formatTime(dateTime: string): string {
  const date = new Date(dateTime);
  return date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
}

// Filter bookings for a specific activity
function filteredBookings(activity: string) {
  return bookings.value.filter(booking => booking.activityName === activity);
}

//Style calculation for booking position in the day grid ... Need to look at this
function getEventStyle(booking: Booking) {
  const bookingDate = new Date(booking.dateStartTime);
  const startHour = bookingDate.getHours();
  const startMinutes = bookingDate.getMinutes();
  const endHour = new Date(booking.endTime).getHours();
  const endMinutes = new Date(booking.endTime).getMinutes();

  // Check if the booking date is a weekend
  const isWeekend = bookingDate.getDay() === 0 || bookingDate.getDay() === 6;
  const openingHour = isWeekend ? 12 : 10;
  const closingHour = 20;  // Always closing at 20
  const totalOpenHours = closingHour - openingHour;

  // Adjust grid calculation based on opening hours
  const top = ((startHour - openingHour) * 60 + startMinutes) * 100 / (totalOpenHours * 60);
  const height = ((endHour - startHour) * 60 + (endMinutes - startMinutes)) * 100 / (totalOpenHours * 60);


  return {
    top: `${top}%`,
    height: `${height}%`,
    backgroundColor: activityColors[booking.activityName] || 'rgba(255, 255, 255, 0.7)'  // Default color
  };
}
</script>

<template>
  <transition name="slide">
    <div v-if="isVisible" class="day-view" @click.stop="closeDayView">
      <BaseButton text="✖" type="button" class="close-button" @click.stop="closeDayView"/>
      <h2>{{ day?.toDateString() }} </h2>
      <div class="day-grid">
        <div class="time-labels">
          <!-- Adjust the loop based on opening hours -->
          <div v-for="hour in (isWeekend(<Date>day) ? 8 : 10)" :key="hour">{{ hour + (isWeekend(day) ? 12 : 10) }}:00</div>
        </div>
        <div class="event-grid">
          <div
              v-for="activity in uniqueActivities"
              :key="activity"
              class="activity-column"
          >
            <h3>{{ activity }}</h3>
            <div class="event-grid">
              <div
                  v-for="booking in filteredBookings(activity)"
                  :key="booking.id"
                  :style="getEventStyle(booking)"
                  class="event-block"
              >
                {{ formatTime(booking.dateStartTime) }} -
                {{ formatTime(booking.endTime) }}
                <br />
                {{ booking.custName }}
              </div>
            </div>
        </div>
      </div>
    </div>
    </div>
  </transition>
</template>

<style scoped>
.day-view {
  width: 50%;
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

.day-grid {
  display: grid;
  grid-template-columns: 50px 1fr;
  grid-template-rows: repeat(10, 1fr);
  gap: 10px;
  flex-grow: 1;
}

.activity-names div {
  font-weight: bold;
  color: #333;
}

.time-labels div {
  text-align: right;
  padding-right: 10px;
  font-weight: bold;
}

.event-grid {
  position: relative;
}

.activity-columns {
  display: flex; /* Arrange activity columns horizontally */
  flex-grow: 1; /* Make activity columns take the remaining space */
}

.activity-column {
  flex: 1; /* Each column takes equal width */
  position: relative; /* Allow absolute positioning for bookings */
  margin-right: 10px; /* Space between columns */
}

.activity-column h3 {
  text-align: center;
}

.event-grid {
  position: relative;
  height: 100%; /* Ensure it fills the column height */
}


.event-block {
  background-color: rgba(255, 182, 193, 0.7);
  border-radius: 5px;
  padding: 5px;
  color: white;
  font-size: 12px;
  font-weight: bold;
}

</style>
