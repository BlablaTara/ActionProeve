<script setup lang="ts">
defineProps<{
  text: string
  //Ændret denne, da den brokkede sig. Sorry
  type?: 'reset' | 'submit' | 'button'
}>()
</script>

<template>
  <button :type="type">{{ text }}</button>
</template>
