<script setup lang="ts">
import BaseButton from "@/components/BaseButton.vue";

// Define a type for the event
interface Event {
  title: string;
  startTime: string;
  endTime: string;
}

// Define props with appropriate types
const props = defineProps<{
  day: Date;
  events: Event[];
  isVisible: boolean;
}>();

const emit = defineEmits(['close-day-view']);

function closeDayView() {
  emit('close-day-view');
}
</script>

<template>
  <transition name="slide">
    <div v-if="isVisible" class="day-view" @click.stop="closeDayView">
      <BaseButton text="✖" type="button" class="close-button" @click.stop="closeDayView"/>
      <h2>{{ day.toDateString() }} </h2>
      <ul>
        <li v-for="(event, index) in events" :key="index">
          {{ event.title }} ({{ event.startTime }} - {{ event.endTime }})
        </li>
      </ul>

      <BaseButton @click="closeDayView" text="Close" type="button" />
    </div>
  </transition>
</template>

<style scoped>
.day-view {
  width: 300px;
  padding: 20px;
  background: #f0f0f0;
  border-left: 1px solid #ccc;
  position: absolute;
  right: 0;
  top: 0;
  height: 100%;
  z-index: 10; /* Ensure it's above other content */
}

.close-button {
}

.slide-enter-active, .slide-leave-active {
  transition: transform 0.5s ease;
}
.slide-enter {
  transform: translateX(100%);
}
.slide-leave-to {
  transform: translateX(100%);
}
</style>
