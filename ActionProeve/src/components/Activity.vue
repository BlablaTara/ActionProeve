<!-- eslint-disable vue/multi-word-component-names -->
<script setup lang="ts">
import missingImage from '../assets/missingpicture.png'

const props = defineProps<{
  activity: {
    activityName: string,
    durations: string[],
    information: string,
    image: string
  }
  isReversed: boolean
}>();

//Tjekker om der er et image i jsonfilen, og ellers bruger den det lokale billede.
const imageMissing = props.activity.image || missingImage
</script>

<template>
  <div class="activity" :class="{ reverse: props.isReversed }">
    <div class="activity-details">
      <h2>{{ props.activity.activityName }}</h2>
      <p class="info-line">Choose between: </p>
      <p>{{ props.activity.durations.join(', ') }}</p>
      <p class="info-line">Info: </p>
      <p>{{ props.activity.information }}</p>
    </div>

    <div class="activity-image">
      <img class="image" :src="imageMissing" :alt="props.activity.activityName + ' Image'" />
    </div>
  </div>
</template>

<style scoped>
.activity {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 150px;
  margin-top: 40px;
  padding: 20px;

}

.activity.reverse {
  flex-direction: row-reverse;
}

.activity-details{
  flex: 1;
  margin-left: 120px;
  max-width: 600px;
}
.activity.reverse activity-details {
  margin-left: 0px;
  margin-right: 150px;
}

.activity-image {
  flex: 1;
  margin-left: 20px;
  text-align: center;
}
.activity.reverse .activity-image {
  margin-left: 50px;
  margin-right: 0; /* Juster margin for billedet, når rækkefølgen ændres */
}

.image {
  max-width: 400px;
  height: auto;
  transition: transform 0.3s ease;
}
.image:hover {
  transform: scale(1.1);
}

.info-line {
  font-weight: bold;
  font-size: 1.3rem;
  margin-bottom: 0;
  color: var(--action-yellow);
}

p {
  margin-top: 0;
  line-height: 1.6;
}


</style>
