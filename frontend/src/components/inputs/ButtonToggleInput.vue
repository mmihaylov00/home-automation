<script setup lang="ts">
import { ButtonToggleInputType } from '@/components/inputs/input.dto.js'
import { computed } from 'vue'

const props = defineProps<{
  input: ButtonToggleInputType
  modelValue: ButtonToggleInputType['value']
}>()

const emit = defineEmits(['update:modelValue'])

const value = computed({
  get() {
    return props.modelValue
  },
  set(value) {
    emit('update:modelValue', value)
  },
})
</script>
<template>
  <v-btn-toggle
    selected-class="selected"
    group
    rounded
    color="primary"
    :mandatory="true"
    v-model="value"
    class="d-flex">
    <v-btn
      v-for="(item, index) in input.values"
      v-bind:value="item.value"
      class="flex-grow-1"
      :key="index">
      {{ item.title }}
    </v-btn>
  </v-btn-toggle>
</template>

<style scoped lang="scss">
@use '@/styles/settings';
:not(.selected) {
  background-color: settings.$application-background !important;
}
</style>
