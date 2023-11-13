<script setup lang="ts">
import { ButtonToggleInputType } from '@/components/inputs/input.dto'

defineProps<{
  input: ButtonToggleInputType
  form: any
}>()
</script>

<template>
  <v-btn-toggle group rounded color="primary" v-model="form[input.key]">
    <v-btn v-for="item in input.values" v-bind:value="item.value">
      {{ item.title }}
    </v-btn>
  </v-btn-toggle>
</template>
