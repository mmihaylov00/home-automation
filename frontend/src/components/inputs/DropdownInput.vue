<script setup lang="ts">
import { DropdownInputType } from '@/components/inputs/input.dto.js'
import { inputRules } from './input.dto.js'
import { computed } from 'vue'

const props = defineProps<{
  input: DropdownInputType
  modelValue: DropdownInputType['value']
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
  <v-select
    variant="outlined"
    :rules="inputRules(input, modelValue)"
    :label="input.label"
    :items="input.values"
    v-model="value" />
</template>
