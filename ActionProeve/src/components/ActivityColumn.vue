<script setup lang="ts">

interface Booking {
  id: number;
  activityName: string;
  dateStartTime: string;
  endTime: string;
  custName: string;
  custPhoneNum: string;
  guestAmount: number;
}

const props = defineProps<{
  activityName: string;
  bookings: Booking[];
}>();

// Method to format the time display
function formatTime(dateTime: string): string {
  const date = new Date(dateTime);
  return date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
}

// Function to calculate styles for events (could be passed in from the parent)
function getEventStyle(booking: Booking) {
  // Example implementation; adjust this based on your requirements
  const startHour = new Date(booking.dateStartTime).getHours();
  const startMinutes = new Date(booking.dateStartTime).getMinutes();
  const endHour = new Date(booking.endTime).getHours();
  const endMinutes = new Date(booking.endTime).getMinutes();

  // Logic to calculate top and height based on start and end times
  const openingHour = 10; // Assuming opening hour is 10 AM
  const closingHour = 20; // Assuming closing hour is 8 PM
  const totalOpenHours = closingHour - openingHour;

  const top = ((startHour - openingHour) * 60 + startMinutes) * 100 / (totalOpenHours * 60);
  const height = ((endHour - startHour) * 60 + (endMinutes - startMinutes)) * 100 / (totalOpenHours * 60);

  return {
    top: `${top}%`,
    height: `${height}%`,
    backgroundColor: 'rgba(255, 182, 193, 0.7)', // Default color or based on activity
  };
}
</script>

<template>
  <div class="activity-column">
    <h3>{{ activityName }}</h3>
    <div class="event-grid">
      <div
          v-for="(booking, index) in bookings"
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
</template>

<style scoped>
.activity-column {
  flex: 1; /* Allow the column to grow equally */
  position: relative; /* Allow absolute positioning for bookings */
  margin-right: 10px; /* Space between columns */
}

.event-grid {
  position: relative;
  height: 100%; /* Ensure it fills the column height */
}

.event-block {
  position: absolute; /* Allow overlap of event blocks */
  border-radius: 5px;
  padding: 5px;
  color: white;
  font-size: 12px;
  font-weight: bold;
}
</style>