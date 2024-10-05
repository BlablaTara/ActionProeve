<script setup lang="ts">
import { CSSProperties } from 'vue';

interface Booking {
  id: number;
  activityName: string;
  dateStartTime: string;
  endTime: string;
  custName: string;
}

const props = defineProps<{
  activityName: string;
  bookings: Booking[];
  activityColor: string;
}>();

function isWeekend(date: Date): boolean {
  const dayOfWeek = date.getDay();
  return dayOfWeek === 0 || dayOfWeek === 6; // Sunday = 0, Saturday = 6
}

function getEventStyle(booking: Booking): CSSProperties {
  const startHour = new Date(booking.dateStartTime).getHours();
  const startMinutes = new Date(booking.dateStartTime).getMinutes();
  const endHour = new Date(booking.endTime).getHours();
  const endMinutes = new Date(booking.endTime).getMinutes();

  // Check if the booking date is a weekend
  const isBookingOnWeekend = isWeekend(new Date(booking.dateStartTime));
  const openingHour = isBookingOnWeekend ? 12 : 10;
  const closingHour = 20;  // Always closing at 20
  const totalHours = closingHour - openingHour;

  const top = ((startHour - openingHour) * 60 + startMinutes) * 100 / (totalHours * 60);
  const height = ((endHour - startHour) * 60 + (endMinutes - startMinutes)) * 100 / (totalHours * 60);

  return {
    top: `${top}%`,
    height: `${height}%`,
    backgroundColor: props.activityColor || 'rgba(173, 216, 230, 0.7)',
    position: 'absolute',
    width: '100%',
  };
}

function formatTime(time: string) {
  return new Date(time).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
}
</script>

<template>
  <div class="activity-column">
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
  flex: 1;
  position: relative;
  margin-right: 20px;
}

.event-grid {
  position: relative;
  height: 80%;
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
